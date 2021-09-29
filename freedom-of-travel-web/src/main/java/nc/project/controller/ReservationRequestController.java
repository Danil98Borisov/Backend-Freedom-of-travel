package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.jpa.entity.Reservation;
import nc.project.models.ReservationRequest;
import nc.project.service.ReservationRequestService;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationRequestController {


    private final ReservationRequestService reservationRequestService;

    @PostMapping("/add")
    Reservation addReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationRequestService.saveReservation(reservationRequest);
    }

}