package nc.project.jpa.repository;

import nc.project.jpa.entity.Reservation;
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
            "and r.status='BOOKED'" +
            "and u.email =:email", nativeQuery = true)
    List<Reservation> findReservationByUser(@Param("email") String email);

    @Query(value = "Select count(*) from reservation where " +
            "((:startDate BETWEEN start_date AND end_date) " +
            "OR (:endDate BETWEEN start_date AND end_date)) and apartment_id = :apartmentId", nativeQuery = true)
    Integer findBookedApartments(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate,
                                             @Param("apartmentId") Long apartmentId);
    @Query(value = "    select *" +
            "    from reservation r," +
            "    apartment ap," +
            "    users u" +
            "    where ap.id = r.apartment_id" +
            "    and u.id = r.user_id" +
            "    and r.id = :id", nativeQuery = true)
    Reservation findReservationById(@Param("id") Long id);

}