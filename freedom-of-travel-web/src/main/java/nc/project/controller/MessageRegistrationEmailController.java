package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.auth.response.EmailReservationResponse;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.repository.ReservationRepository;
import nc.project.service.EmailReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MessageRegistrationEmailController {

    private final ReservationRepository reservationRepository;
    private final EmailReservationService emailReservationService;


    @GetMapping("/reservation-user-email")
    public ResponseEntity<EmailReservationResponse> sendReservationEmail(@RequestParam("email") String email) throws  MessagingException {
        Reservation reservation = reservationRepository.findEmail(email);
         sendReservationEmail(reservation);
        return null;

    }

    public void sendReservationEmail(Reservation reservation) throws MessagingException {
        emailReservationService.sendReservationMail(reservation);
    }

}
