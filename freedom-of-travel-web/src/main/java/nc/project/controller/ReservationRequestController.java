package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.jpa.repository.ReservationRepository;
import nc.project.models.ReservationRequest;
import nc.project.models.ReservationResponse;
import nc.project.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationRequestController {


    private final ReservationService reservationService;
    private final ReservationRepository reservationRepository;


    @PostMapping("/add")
    ReservationResponse addReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.makeReservation(reservationRequest);
    }

}