package nc.project.service;

import nc.project.auth.request.LoginRequest;

import java.util.Map;

public interface AuthService {

    Map<String, Object> authenticateUser(LoginRequest loginRequest);

}
