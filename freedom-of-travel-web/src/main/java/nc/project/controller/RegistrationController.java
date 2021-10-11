package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.auth.UserRegistration;
import nc.project.auth.request.SignupRequest;
import nc.project.service.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping("/signup")
    public ResponseEntity<UserRegistration> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        UserRegistration userRegistration = userRegistrationService.register(signUpRequest, request);
        return userRegistration.isSuccessful()
                ? ResponseEntity.ok(userRegistration)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userRegistration);
    }

}
