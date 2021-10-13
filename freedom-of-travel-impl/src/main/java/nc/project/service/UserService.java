package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.const_enum.ERole;
import nc.project.jpa.entity.Role;
import nc.project.jpa.entity.User;
import nc.project.jpa.repository.RoleRepository;
import nc.project.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User makeUserAdmin(Long userId){
        User user = userRepository.findUserById(userId);
        if (user != null) {
            Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
            if (adminRole.isPresent()) {
                user.setRoles(new HashSet<>(Arrays.asList(adminRole.get())));
                return userRepository.save(user);
            } else {
                throw new IllegalStateException(String.format("Can not update user %s, admin role is not found", userId));
            }
        } else {
            throw new IllegalArgumentException(String.format("Can not update user role, user with id = %s is not found", userId));
        }
    };
}
