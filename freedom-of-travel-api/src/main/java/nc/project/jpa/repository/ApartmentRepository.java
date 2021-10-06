package nc.project.jpa.repository;

import nc.project.jpa.entity.Apartment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "select * from apartment apart, hotel h, apartment_type r " +
            "where h.id = apart.hotel_id " +
            "and r.id = apart.type and (:rating is null or h.rating > :rating)" +
            "and (:city = '' or h.city = :city) " +
            "and (:type = '' or r.name = :type) " +
            "and (:price is null or apart.price < :price) " +
            "and not exists  " +
            " (select 1 from reservation resrv " +
            "   where resrv.start_date >= :startDate " +
            "   and resrv.end_date <= :endDate " +
            "   and resrv.apartment_id = apart.id)", nativeQuery = true)
    List<Apartment> findAvailableApartments(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate,
                                            @Param("city") String city,
                                            @Param("rating") Integer rating,
                                            @Param("type") String type,
                                            @Param("price") Float price,
                                            Pageable pageble);

    @Query(value = "select * from apartment where id = :id ", nativeQuery = true)
    Apartment findApartmentById(@Param("id") Long id);
}