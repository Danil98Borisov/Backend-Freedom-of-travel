package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.auth.response.EmailVerificationResponse;
import nc.project.jpa.entity.User;
import nc.project.jpa.repository.UserRepository;
import nc.project.service.EmailService;
import nc.project.service.VerificationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class VerificationController {

    private final VerificationService verificationService;
    private final EmailService emailService;
    private final UserRepository userRepository;

    private final String REDIRECT_LOGIN_UI = "http://localhost:4200/login/?verified=true";
    private final String REDIRECT_NOT_VERIFY_UI = "http://localhost:4200/verification-error/?email=";

    @GetMapping("/verify-user-email")
    public ResponseEntity<EmailVerificationResponse> sendVerificationEmail(@RequestParam("email") String email, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            sendVerificationEmail(user, getSiteURL(request));
            return ResponseEntity.ok(new EmailVerificationResponse("Email verification request has been sent!"));
        } else {
            return new ResponseEntity("Error: user is not registered!", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<Object> verifyUser(@RequestParam("code") String verificationCode,
                                             @RequestParam("email") String email) throws URISyntaxException {
        URI redirectLoginUi;
        if (verificationService.verify(verificationCode)) {
            User user = userRepository.findByVerificationCode(verificationCode);
            redirectLoginUi = new URI(REDIRECT_LOGIN_UI + "&username=" + user.getUsername());
        } else {
            redirectLoginUi = new URI(REDIRECT_NOT_VERIFY_UI + email);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectLoginUi);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        emailService.sendMail(user, siteURL);
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

}