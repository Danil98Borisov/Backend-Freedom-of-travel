package nc.project.repository;

import nc.project.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    @Query(value = "SELECT * FROM hotel AS h, (SELECT * FROM apartment AS a LEFT JOIN (SELECT * FROM reservation WHERE end_date<?1 OR start_date>?2) AS res ON a.id=res.apartmentid) AS res_apartment WHERE res_apartment.hotel_id=h.id AND h.rating>?3 AND h.city=?4 AND a.price >?5", nativeQuery = true)
    List<Reservation> findByHotelsByDateAndRatingAndCityAndPrice(LocalDate start_date, LocalDate end_date, int rating, String city, float price);

    @Query(value = "SELECT * FROM hotel AS h, (SELECT * FROM apartment AS a LEFT JOIN (SELECT * FROM reservation WHERE end_date<?1 OR start_date>?2) AS res ON a.id=res.apartmentid) AS res_apartment WHERE res_apartment.hotel_id=h.id AND h.rating>?3 AND a.price >?4", nativeQuery = true)
    List<Reservation> findByHotelsByDateAndRatingAndPrice(LocalDate start_date, LocalDate end_date, int rating, float price);
}