package nc.project.repository;

import nc.project.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

    @Query(value = "SELECT * FROM hotel AS h, (SELECT * FROM room AS r LEFT JOIN (SELECT * FROM reservation WHERE end_date<?1 OR start_date>?2) AS res ON r.id=res.roomid) AS res_room WHERE res_room.hotel_id=h.id AND h.rating>?3 AND h.city=?4 AND price >?5", nativeQuery = true)
    List<Reservation> findByHotelsByDateAndRatingAndCityAndPrice(LocalDate start_date, LocalDate end_date, int rating, String city, float price);

    @Query(value = "SELECT * FROM hotel AS h, (SELECT * FROM room AS r LEFT JOIN (SELECT * FROM reservation WHERE end_date<?1 OR start_date>?2) AS res ON r.id=res.roomid) AS res_room WHERE res_room.hotel_id=h.id AND h.rating>?3 AND price >?4", nativeQuery = true)
    List<Reservation> findByHotelsByDateAndRatingAndPrice(LocalDate start_date, LocalDate end_date, int rating, float price);

}