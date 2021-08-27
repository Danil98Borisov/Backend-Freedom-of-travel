package nc.project.controller;

import nc.project.models.Hotel;
import nc.project.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/hotels")
    public Iterable<Hotel> findAll() {

        return hotelRepository.findAll();
    }

    @PutMapping("/hotels/add")
    Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PutMapping("/hotels/edit")
    Hotel editHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/hotels/delete/{id}")
    void deleteHotel(@PathVariable Long id) {
        hotelRepository.deleteById(id);
    }
}