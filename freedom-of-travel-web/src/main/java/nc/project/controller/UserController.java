package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.jpa.entity.User;
import nc.project.jpa.repository.UserRepository;
import nc.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/all")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/update-roleAdmin/{id}")
    public User findReservationByUser(@PathVariable("id") Long id) {
        return userService.makeUserAdmin(id);
    }
}
