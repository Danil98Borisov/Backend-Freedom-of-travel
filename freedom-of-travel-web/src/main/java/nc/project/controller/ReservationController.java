package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.models.Reservation;
import nc.project.repository.ReservationRepository;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {


    private final ReservationRepository reservationRepository;

    @GetMapping("/all")
    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }

    @PostMapping("/add")
    Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

}