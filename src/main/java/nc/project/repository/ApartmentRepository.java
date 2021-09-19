package nc.project.repository;

import nc.project.models.Apartment;
import nc.project.models.ImageApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

    @Query(value = "select * from apartment ap, " +
            "     hotel h " +
            "where h.id = ap.hotel_id " +
            "and (:rating is null or h.rating > :rating)" +
            "and (:city = '' or h.city = :city) " +
            "and (:apartmentType = '' or ap.type = :apartmentType) " +
            "and (:price is null or ap.price < :price) " +
            "and not exists  " +
            " (select 1 from reservation resrv " +
            "   where resrv.start_date >= :startDate " +
            "   and resrv.end_date <= :endDate " +
            "   and resrv.hotel_id = h.id " +
            "   and resrv.apartment_id = ap.id)", nativeQuery = true)
    List<Apartment> findAvailableApartments(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate,
                                            @Param("city") String city,
                                            @Param("rating") Integer rating,
                                            @Param("apartmentType") String apartmentType,
                                            @Param("price") Float price);

/*    @Query(value = "select * from image_apartment as ia, apartment as ap  " +
            " where ap.id = :id and ia.apartment_id=ap.id", nativeQuery = true)
    List<ImageApartment> detailsApartment(@Param("id") Long id);*/


}