package nc.project.jpa.repository;

import nc.project.jpa.entity.ImageApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageApartmentRepository extends JpaRepository<ImageApartment, Long> {

    @Query(value = "SELECT * FROM image_apartment ia, apartment apart, hotel h " +
            " WHERE h.id = apart.hotel_id " +
            " and h.id=:hotel_id " +
            " and apart.apartment_type_id=:apartment_type_id" +
            " and ia.hotel_id = h.id" +
            " and apart.apartment_type_id = ia.apartment_type_id", nativeQuery = true)
    List<ImageApartment> findImageApartmentsByApartmentTypeId(Long hotel_id,Long apartment_type_id);
}
