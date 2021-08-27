package nc.project.repository;

import nc.project.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

    @Query(value = "SELECT * FROM hotel WHERE rating>?", nativeQuery = true)
    List<Hotel> findHotelByRating(int rating);
}