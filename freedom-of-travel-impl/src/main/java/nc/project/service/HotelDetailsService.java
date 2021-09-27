package nc.project.service;

import lombok.RequiredArgsConstructor;

import nc.project.jpa.entity.Hotel;
import nc.project.models.HotelDetails;
import nc.project.jpa.entity.ImageHotel;
import nc.project.jpa.repository.HotelRepository;
import nc.project.jpa.repository.ImageHotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelDetailsService {

    private final HotelRepository hotelRepository;
    private final ImageHotelRepository imageHotelRepository;

    public HotelDetails getHotelDetails(Long id) {
        HotelDetails hotelDetails = new HotelDetails();
        hotelRepository.findById(id).ifPresent(hotel -> {
            List<ImageHotel> images = imageHotelRepository.findImageHotelsByHotelId(hotel.getId());

            hotelDetails.setHotel(hotel);
            hotelDetails.setHotelImages(images);
        });
        return hotelDetails;
    }

    public HotelDetails editHotelDetails(HotelDetails hotelDetails) {
        Hotel editedHotel = hotelRepository.save(hotelDetails.getHotel());
        hotelDetails.setHotel(editedHotel);
        return hotelDetails;
    }
}