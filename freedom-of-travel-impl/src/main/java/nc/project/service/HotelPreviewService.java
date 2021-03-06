
package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Hotel;

import nc.project.models.HotelPreview;
import nc.project.jpa.entity.ImageHotel;
import nc.project.jpa.repository.HotelRepository;
import nc.project.jpa.repository.ImageHotelRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelPreviewService {

    private final HotelRepository hotelRepository;
    private final ImageHotelRepository imageHotelRepository;

    public List<HotelPreview> getHotelPreviews() {
        List<Hotel> allHotels = hotelRepository.findAll();
        List<ImageHotel> allHotelImages = imageHotelRepository.findAll();

        List<HotelPreview> hotelPreviews = new ArrayList<>();
        for (Hotel hotel : allHotels) {
            HotelPreview hotelPreview = new HotelPreview();
            hotelPreview.setHotel(hotel);
            ImageHotel image = findHotelImage(allHotelImages, hotel);
            hotelPreview.setImageHotel(image);
            hotelPreviews.add(hotelPreview);
        }

        return hotelPreviews;
    }

    private ImageHotel findHotelImage(List<ImageHotel> allHotelImages, Hotel hotel) {
        ImageHotel image = null;
        for (ImageHotel img : allHotelImages) {
            if (hotel == img.getHotel() && img.getFlag() == 1) {
                image = img;
            }
        }
        return image;
    }
    public List<HotelPreview> getFilteredHotelPreviewsSorted(List<Hotel> availableHotels){
        List<HotelPreview> hotelPreviews = new ArrayList<>();
        for (Hotel hotel : availableHotels) {

            HotelPreview hotelPreview = new HotelPreview();
            hotelPreview.setHotel(hotel);
            ImageHotel image = imageHotelRepository.findImageHotelByHotelIdAndFlag(hotel.getId(), 1);
            hotelPreview.setImageHotel(image);

            hotelPreviews.add(hotelPreview);
        }

        return hotelPreviews;
    }
    public List<HotelPreview> getFilteredHotelPreviews(LocalDate startDate, LocalDate endDate,
                                                       String city, int rating, String type, float price, String sort, Pageable pageable) {
        if ("DESC".equals(sort)) {
            List<Hotel> availableHotels = hotelRepository.findAvailableHotelsDesc(startDate, endDate, city, rating, type, price, pageable);
            return getFilteredHotelPreviewsSorted(availableHotels);
        } else {
            List<Hotel> availableHotels = hotelRepository.findAvailableHotelsAsc(startDate, endDate, city, rating, type, price, pageable);
            return getFilteredHotelPreviewsSorted(availableHotels);
        }
    }

}
