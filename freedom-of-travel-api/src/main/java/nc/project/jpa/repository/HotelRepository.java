package nc.project.jpa.repository;

import nc.project.jpa.entity.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query(value = "select * from hotel h " +
            "    where (:rating is null or rating > :rating)" +
            "    and (:city = '' or city = :city)", nativeQuery = true)
    List<Hotel> findAvailableHotels(@Param("city") String city,
                                    @Param("rating") Integer rating,
                                    Pageable pageable);
}