package nc.project.repository;

import nc.project.models.ImageHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageHotelRepository extends JpaRepository<ImageHotel, Long>{

    ImageHotel findImageHotelByHotelIdAndFlag(Long hotelId, int flag);

    List<ImageHotel> findImageHotelsByHotelId(Long hotelId);


}





