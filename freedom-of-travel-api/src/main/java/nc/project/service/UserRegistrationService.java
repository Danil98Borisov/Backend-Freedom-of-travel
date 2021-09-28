package nc.project.service;

import nc.project.auth.UserRegistration;
import nc.project.auth.request.SignupRequest;

public interface UserRegistrationService {

    UserRegistration register(SignupRequest signUpRequest);

}
