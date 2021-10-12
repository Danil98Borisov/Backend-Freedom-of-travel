package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.const_enum.ERole;
import nc.project.jpa.entity.Role;
import nc.project.jpa.entity.User;
import nc.project.jpa.repository.RoleRepository;
import nc.project.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User makeUserAdmin(Long user_id){
        Role adminRole = new Role(3L, ERole.ROLE_ADMIN);

        User role = userRepository.findUserById(user_id);
        role.setRoles(Collections.singleton(adminRole));

        return userRepository.save(role);
    };
}
