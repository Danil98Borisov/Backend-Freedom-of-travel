package nc.project.controller;

import nc.project.models.Reservation;
import nc.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservation")
    public Iterable<Reservation> findAll() {

        return reservationRepository.findAll();
    }

    @DeleteMapping("/reservation/delete/{id}")
    void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }
}