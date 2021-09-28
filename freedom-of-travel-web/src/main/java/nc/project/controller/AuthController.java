package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.auth.request.LoginRequest;
import nc.project.auth.response.JwtResponse;
import nc.project.service.AuthService;
import nc.project.services.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Map<String, Object> outDataAuthenticateUser = authService.authenticateUser(loginRequest);
        String jwt = outDataAuthenticateUser.get("jwt").toString();
        UserDetailsImpl userDetails = (UserDetailsImpl) outDataAuthenticateUser.get("userDetails");
        List<String> roles = (List<String>) outDataAuthenticateUser.get("roles");

        return ResponseEntity.ok(new JwtResponse(jwt,
                                 userDetails.getId(),
                                 userDetails.getUsername(),
                                 userDetails.getEmail(),
                                 roles));
    }

}
