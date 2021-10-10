package nc.project.service;

import lombok.RequiredArgsConstructor;

import nc.project.jpa.entity.Hotel;
import nc.project.jwt.JwtUtils;
import nc.project.models.HotelDetails;
import nc.project.jpa.entity.ImageHotel;
import nc.project.jpa.repository.HotelRepository;
import nc.project.jpa.repository.ImageHotelRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelDetailsService {

    private final HotelRepository hotelRepository;
    private final ImageHotelRepository imageHotelRepository;
    private final JwtUtils jwtUtils;

    public HotelDetails getHotelDetails(Long hotelId, HttpServletRequest request) {
        HotelDetails hotelDetails = new HotelDetails();
        hotelRepository.findById(hotelId).ifPresent(hotel -> {
            List<ImageHotel> images = imageHotelRepository.findImageHotelsByHotelId(hotel.getId());
            Integer count = hotelRepository.findCountUserHotel(hotel.getId(),jwtUtils.getUserNameFromRequest(request));

            hotelDetails.setHotel(hotel);
            hotelDetails.setHotelImages(images);
            hotelDetails.setFlag(count==0 ? false: true);
        });
        return hotelDetails;
    }

    public HotelDetails editHotelDetails(HotelDetails hotelDetails) {
        Hotel editedHotel = hotelRepository.save(hotelDetails.getHotel());
        hotelDetails.setHotel(editedHotel);
        return hotelDetails;
    }
}