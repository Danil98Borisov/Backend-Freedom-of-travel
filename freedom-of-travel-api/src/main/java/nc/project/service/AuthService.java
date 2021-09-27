package nc.project.service;

import nc.project.auth.request.LoginRequest;
import nc.project.auth.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface AuthService {

    Map<String, Object> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<Map<String, Object>> registerUser(SignupRequest signUpRequest, String siteURL) throws UnsupportedEncodingException;

}
