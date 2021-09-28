package nc.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.auth.UserRegistration;
import nc.project.auth.request.SignupRequest;
import nc.project.const_enum.ERole;
import nc.project.jpa.entity.Role;
import nc.project.jpa.entity.User;
import nc.project.jpa.repository.RoleRepository;
import nc.project.jpa.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserRegistration register(SignupRequest signUpRequest) {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setSuccessful(false);

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            userRegistration.setMessage("Error: username is already taken");
            return userRegistration;
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            userRegistration.setMessage("Error: email is already in use");
            return userRegistration;
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                resolveUserRoles(signUpRequest.getRole()));

        userRepository.save(user);
        log.info(String.format("User %s is registered successfully!", user.getUsername()));

        userRegistration.setSuccessful(true);
        userRegistration.setMessage(String.format("User %s is registered successfully!", user.getUsername()));
        return userRegistration;
    }

    private Set<Role> resolveUserRoles(Set<ERole> roleNames) {
        Set<Role> roles = new HashSet<>();
        if (roleNames == null) {
            roles.add(roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: default User Role %s is not found")));
        } else {
            roleNames.forEach(roleName ->
                    roleRepository.findByName(roleName).ifPresent(roles::add));
        }
        return roles;
    }
}
