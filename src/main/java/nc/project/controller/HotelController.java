package nc.project.controller;

import nc.project.models.Hotel;
import nc.project.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;


    @GetMapping("/hotels/filter")
    List<Hotel> dateHotel(@RequestParam (required = false, defaultValue = "0") int rating){

        return hotelRepository.findHotelByRating(rating);
    }
}