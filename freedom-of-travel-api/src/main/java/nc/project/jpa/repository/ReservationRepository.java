package nc.project.jpa.repository;

import nc.project.jpa.entity.Apartment;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{


    @Query(value = "select * from reservation r," +
            " users u " +
            "where u.id=r.user_id " +
            "and u.email =:email", nativeQuery = true)
    List<Reservation> findReservationByUser(@Param("email") String email);

    @Query(value = "Select * from reservation where " +
            "((:startDate BETWEEN start_date AND end_date) " +
            "OR (:endDate BETWEEN start_date AND end_date)) and apartment_id = :apartmentId", nativeQuery = true)
    List<Reservation> findBookedApartments(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate,
                                             @Param("apartmentId") Long apartmentId);
}