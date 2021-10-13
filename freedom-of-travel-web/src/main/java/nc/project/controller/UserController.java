package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.entity.User;
import nc.project.jpa.repository.UserRepository;
import nc.project.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/all/paginated")
    public List<User> findAll(@RequestParam Optional<Integer> pageNumber,
                                     @RequestParam Optional<Integer> pageSize) {
        return userRepository.findAllPaginated(PageRequest.of(pageNumber.orElse(0), pageSize.orElse(10)));
    }

    /*@GetMapping("/all-non-admin")
    public List<User> findAllNonAdmins() {
        return userRepository.findNonAdmins();
    }*/

    @GetMapping("/make-admin/{id}")
    public User findReservationByUser(@PathVariable("id") Long id) {
        return userService.makeUserAdmin(id);
    }
}
