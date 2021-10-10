package nc.project.controller;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Hotel;
import nc.project.jpa.repository.HotelUserRepository;
import nc.project.models.HotelDetails;
import nc.project.jpa.repository.HotelRepository;
import nc.project.service.HotelDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api/hotel")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HotelController {

    private final HotelRepository hotelRepository;
    private final HotelUserRepository hotelUserRepository;
    private final HotelDetailsService hotelDetailsService;

    @GetMapping("/all")
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @PutMapping("/add")
    Hotel addHotel(@RequestBody Hotel hotel) {return hotelRepository.save(hotel);}

    @PostMapping("/edit")
    Hotel editHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/delete/{id}")
    void deleteHotel(@PathVariable Long id) {
        hotelRepository.deleteById(id);
    }

    @GetMapping("/manage-hotel/{email}")
    public List<Hotel> findHotelByUser(@PathVariable("email") String email) {
        return hotelRepository.findHotelManagerByUser(email);
    }

    @GetMapping("/manage-hotel/all")
    public List<Hotel> findAllHotelByUser() {
        return hotelRepository.findAllHotelManagerByUser();
    }

    /*Details*/
    @GetMapping("/details/{id}")
    public HotelDetails detailsHotel(@PathVariable("id") Long hotelId, HttpServletRequest request) {
        return hotelDetailsService.getHotelDetails(hotelId, request);
    }

    @PostMapping("/details/edit")
    public HotelDetails detailsHotel(@RequestBody HotelDetails hotelDetails) {
        return hotelDetailsService.editHotelDetails(hotelDetails);
    }

}