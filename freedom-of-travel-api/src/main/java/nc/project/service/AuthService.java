package nc.project.service;

import nc.project.auth.request.LoginRequest;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AuthService {

    Map<String, Object> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> signOut(HttpServletRequest request);

}
