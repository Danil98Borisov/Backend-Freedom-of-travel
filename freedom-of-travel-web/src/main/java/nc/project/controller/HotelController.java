package nc.project.controller;

import lombok.RequiredArgsConstructor;
import nc.project.models.Hotel;
import nc.project.models.HotelDetails;
import nc.project.repository.HotelRepository;
import nc.project.service.HotelDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hotel")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HotelController {

    private final HotelRepository hotelRepository;
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

    /*Details*/
    @GetMapping("/details/{id}")
    public HotelDetails detailsHotel(@PathVariable("id") Long id) {
        return hotelDetailsService.getHotelDetails(id);
    }

    @PostMapping("/details/edit")
    public HotelDetails detailsHotel(@RequestBody HotelDetails hotelDetails) {
        return hotelDetailsService.editHotelDetails(hotelDetails);
    }

}