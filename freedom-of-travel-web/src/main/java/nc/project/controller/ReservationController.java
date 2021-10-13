package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.repository.ReservationRepository;
import nc.project.models.ReservationRequest;
import nc.project.models.ReservationResponse;
import nc.project.service.ReservationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {


    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @GetMapping("/all")
    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @GetMapping("/all/paginated")
    public List<Reservation> findAll(@RequestParam Optional<Integer> pageNumber,
                                     @RequestParam Optional<Integer> pageSize) {
        return reservationRepository.findAllPaginated(PageRequest.of(pageNumber.orElse(0), pageSize.orElse(10)));
    }

    @DeleteMapping("/delete/{id}")
    void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }

    @GetMapping("/booking/{email}")
    public List<Reservation> findReservationByUser(@PathVariable("email") String email) {
        return reservationRepository.findReservationByUser(email);
    }

    @PostMapping("/add")
    ReservationResponse addReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.makeReservation(reservationRequest);
    }

    @PostMapping("/cancel/{id}")
    Reservation editReservation(@PathVariable("id") Long id, @RequestBody String cancelledBy) {
        return reservationService.cancelReservation(id, cancelledBy);
    }

}