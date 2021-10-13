package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.repository.ApartmentRepository;
import nc.project.jpa.repository.ReservationRepository;
import nc.project.jpa.repository.UserRepository;
import nc.project.models.ReservationRequest;
import nc.project.models.ReservationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public ReservationResponse makeReservation(ReservationRequest reservationRequest){
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setStatus(false);


        Integer bookedApartments = reservationRepository.findBookedApartments(reservationRequest.getStartDate(),
                reservationRequest.getEndDate(),
                reservationRequest.getApartmentId());

        if (bookedApartments == 0) {
            saveReservation(reservationRequest);
            reservationResponse.setStatus(true);
            reservationResponse.setMessage("Apartment is successfully booked");
            return reservationResponse;
        }

        reservationResponse.setMessage("Apartment is already booked");
        return reservationResponse;
    };

    public Reservation saveReservation(ReservationRequest reservationRequest){
        Reservation reservation = new Reservation();

        reservation.setStatus("BOOKED");
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setApartment(apartmentRepository.findApartmentById(reservationRequest.getApartmentId()));
        reservation.setUser(userRepository.findUserByEmail(reservationRequest.getBookingBy()));
        reservation.setReservationDate(LocalDateTime.now());

        return reservationRepository.save(reservation);
    };
    public Reservation cancelReservation(Long id, String cancelledBy){
        Reservation reservation = reservationRepository.findReservationById(id);
        reservation.setStatus("CANCELLED");
        reservation.setModifiedWhen(LocalDateTime.now());
        reservation.setModifiedBy(cancelledBy);

        return reservationRepository.save(reservation);
    };


}
