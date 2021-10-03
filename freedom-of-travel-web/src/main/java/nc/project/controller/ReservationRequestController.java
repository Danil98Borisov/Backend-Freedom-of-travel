package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.repository.ReservationRepository;
import nc.project.models.ReservationRequest;
import nc.project.service.ReservationRequestService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationRequestController {


    private final ReservationRequestService reservationRequestService;
    private final ReservationRepository reservationRepository;


    @PostMapping("/add")
    Reservation addReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationRequestService.saveReservation(reservationRequest);
    }

    @GetMapping("/find/reservation")
    List<Reservation> findAvailableApartmentsPreview(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                     @RequestParam Long apartmentId) {
        return reservationRepository.findBookedApartments(startDate, endDate, apartmentId);
    }

}