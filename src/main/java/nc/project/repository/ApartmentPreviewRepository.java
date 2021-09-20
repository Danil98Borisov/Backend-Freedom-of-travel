package nc.project.repository;

import nc.project.models.ApartmentPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApartmentPreviewRepository extends JpaRepository<ApartmentPreview, Long> {

/*
    @Query(value = "select ia.* from image_apartment as ia, apartment as ap  " +
            " where ia.apartment_id = ap.id and ia.flag=1", nativeQuery = true)
    List<ApartmentPreview> getApartmentPreview();
*/

    @Query(value = "select ap.image, h.city, h.name, ap.type, ap.price "+
                        "from (select app.*,ia.image from image_apartment as ia, apartment as app where ia.apartment_id = app.id and ia.flag=1) ap, " +
                        "      hotel h " +
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
    List<ApartmentPreview> findAvailableApartmentsPreview(@Param("startDate") LocalDate startDate,
                                                          @Param("endDate") LocalDate endDate,
                                                          @Param("city") String city,
                                                          @Param("rating") Integer rating,
                                                          @Param("apartmentType") String apartmentType,
                                                          @Param("price") Float price);

}
