package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailReservationService {

    private final JavaMailSender javaMailSender;

    @Value("${freedomOfTravel.app.mail.from}")
    private String from;
    private final String SUBJECT = "You have successfully booked";
    private String contentReservation = "<h3>Dear, {{user-name}},</h3>"
            + "<h4>You have successfully booked an apartment in the {{hotel}}of the city {{city}}.</h4>"
            + "Apartment type: {{type}},<br>"
            + "Start date: {{start-date}},<br>"
            + "End date: {{end-date}},<br><br>"
            + "Have a great holiday!<h3><br>"
            + "With respect!<h3>"
            + "Team, Freedom of travel";

    public void sendReservationMail(Reservation reservation) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(from);
        helper.setTo(reservation.getUser().getEmail());
        helper.setSubject(SUBJECT);

        contentReservation = contentReservation.replace("{{user-name}}", reservation.getUser().getUsername());
        contentReservation = contentReservation.replace("{{start-date}}", reservation.getStart_date().toString());
        contentReservation = contentReservation.replace("{{end-date}}", reservation.getEnd_date().toString());
        contentReservation = contentReservation.replace("{{type}}", reservation.getApartment().getType().getName().toString());
        contentReservation = contentReservation.replace("{{hotel}}", reservation.getApartment().getHotel().getHotelName());
        contentReservation = contentReservation.replace("{{city}}", reservation.getApartment().getHotel().getCity());
        helper.setText(contentReservation, true);

        javaMailSender.send(message);
    }
}
