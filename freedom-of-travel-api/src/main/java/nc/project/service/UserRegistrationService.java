package nc.project.service;

import nc.project.auth.UserRegistration;
import nc.project.auth.request.SignupRequest;
import nc.project.jpa.entity.User;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public interface UserRegistrationService {

    UserRegistration register(SignupRequest signUpRequest, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;

}
