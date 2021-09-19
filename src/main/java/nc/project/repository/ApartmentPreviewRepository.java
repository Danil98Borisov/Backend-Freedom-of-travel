package nc.project.repository;

import nc.project.models.Apartment;
import nc.project.models.ApartmentPreview;
import nc.project.models.ImageApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
@Repository
public interface ApartmentPreviewRepository{

/*
    @Query(value = "select ia.* from image_apartment as ia, apartment as ap  " +
            " where ia.apartment_id = ap.id and ia.flag=1", nativeQuery = true)
    List<ApartmentPreview> getApartmentPreview();
*/

}