package nc.project.repository;

import nc.project.models.Apartment;
import nc.project.models.ImageApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageApartmentRepository extends JpaRepository<ImageApartment, Long> {

    @Query(value = "select * from image_apartment as ia, apartment as ap  " +
            " where ap.id = :id and ia.apartment_id=ap.id", nativeQuery = true)
    List<ImageApartment> detailsApartment(@Param("id") Long id);

    @Query(value = "select * from image_apartment  " +
            " where apartment_id = :apartment_id and flag=1", nativeQuery = true)
    List<ImageApartment> detailsApartmentByFlag(@Param("apartment_id") Integer apartment_id);
}
