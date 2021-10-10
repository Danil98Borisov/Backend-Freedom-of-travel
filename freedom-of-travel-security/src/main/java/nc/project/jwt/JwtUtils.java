package nc.project.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import nc.project.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class JwtUtils {

    @Value("${freedomOfTravel.app.jwtSecret}")
    private String jwtSecret;

    @Value("${freedomOfTravel.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    private Map<UUID, OffsetDateTime> sessionToExpiredWhen = new ConcurrentHashMap<>();

    private final  String AUTHORIZATION_HEADER = "Authorization";
    private final  String PREFIX = "Bearer ";

    public String getJwt(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader == null || !authorizationHeader.startsWith(PREFIX)) {
            return null;
        }
        return authorizationHeader.replace(PREFIX, "");
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .addClaims(Collections.singletonMap("session-id", UUID.randomUUID()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromRequest(HttpServletRequest request) {
        return getUserNameFromJwtToken(getJwt(request));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        if (authToken == null) {
            return false;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken).getBody();
            String sessionId = (String) claims.get("session-id");
            if (sessionId != null && sessionToExpiredWhen.containsKey(UUID.fromString(sessionId))) {
                return false;
            }
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public boolean deactivateToken(HttpServletRequest request) {

        String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
            return false;
        }
        String jwtToken = authenticationHeader.replace(PREFIX, "");
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();

        String sessionId = (String) claims.get("session-id");
        if (sessionId != null) {
            log.info("Expiring session with id = {}", sessionId);
            sessionToExpiredWhen.put(UUID.fromString(sessionId), claims.getExpiration().toInstant().atOffset(
                    ZoneId.systemDefault().getRules().getOffset(Instant.now())));
            return true;
        }
        return false;
    }


    // This job removes all sessions for which token lifespan is passed
    @Scheduled(cron = "*/30 * * * * *")
    public void cleanExpiredSessions() {
        OffsetDateTime now = OffsetDateTime.now();
        long nowMillis = now.toInstant().toEpochMilli();

        log.info("Session cleaning job started at {}", now);
        log.info("Now in epoch millis: {}", nowMillis);

        for (Map.Entry<UUID, OffsetDateTime>  entry: sessionToExpiredWhen.entrySet()) {
            log.info("Session with id = {} expires at {}", entry.getKey(), entry.getValue().toInstant().toEpochMilli());
            log.info("Diff is {}", nowMillis - entry.getValue().toInstant().toEpochMilli());
            if (nowMillis > entry.getValue().toInstant().toEpochMilli()) {
                log.info("Removing session with id = {}", entry.getKey());
                sessionToExpiredWhen.remove(entry.getKey());
            }
        }
    }

}
