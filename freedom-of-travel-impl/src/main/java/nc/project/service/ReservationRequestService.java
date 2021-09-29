package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.repository.ApartmentRepository;
import nc.project.jpa.repository.ReservationRepository;
import nc.project.jpa.repository.UserRepository;
import nc.project.models.ReservationRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationRequestService {

    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public Reservation saveReservation(ReservationRequest reservationRequest){
            Reservation reservation = new Reservation();

            reservation.setStatus("BOOKED");
            reservation.setEnd_date(reservationRequest.end_date);
            reservation.setStart_date(reservationRequest.start_date);
            reservation.setApartment(apartmentRepository.findApartmentById(reservationRequest.apartmentId));
            reservation.setUser(userRepository.findUserByEmail(reservationRequest.userEmail));

        return reservationRepository.save(reservation);
    };

}
