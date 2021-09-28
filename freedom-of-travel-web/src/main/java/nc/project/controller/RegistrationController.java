package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.auth.UserRegistration;
import nc.project.auth.request.SignupRequest;
import nc.project.service.UserRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {

    private final UserRegistrationService userRegistrationService;

    @PostMapping("/signup")
    public ResponseEntity<UserRegistration> registerUser(@Valid @RequestBody SignupRequest signUpRequest)  {
        UserRegistration userRegistration = userRegistrationService.register(signUpRequest);
        return userRegistration.isSuccessful()
                ? ResponseEntity.ok(userRegistration)
                : ResponseEntity.badRequest().body(userRegistration);
    }

}
