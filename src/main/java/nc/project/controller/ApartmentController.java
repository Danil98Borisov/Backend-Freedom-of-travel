package nc.project.controller;

import nc.project.const_enum.ApartmentType;
import nc.project.models.Apartment;
import nc.project.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/apartment")
@CrossOrigin(origins = "http://localhost:4200")
public class ApartmentController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/all")
    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @GetMapping("/details/{id}")
    List<Apartment> detailsApartment(@PathVariable("id") Long id) {
        return apartmentRepository.detailsApartment(id);
    }

    @PutMapping("/add")
    Apartment addApartment(@RequestBody Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @DeleteMapping("/delete/{id}")
    void deleteApartment(@PathVariable Long id) {
        apartmentRepository.deleteById(id);
    }

    @PostMapping("/edit")
    Apartment editApartment(@RequestBody Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @GetMapping("/find")
    List<Apartment> findAvailableApartments(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                            @RequestParam(required = false, defaultValue = "0") int rating,
                                            @RequestParam(required = false, defaultValue = "") String city,
                                            @RequestParam(required = false) ApartmentType type,
                                            @RequestParam(required = false, defaultValue = "10000000") float price) {
        return apartmentRepository.findAvailableApartments(startDate, endDate, city, rating, type != null ? type.name() : "", price);
    }

}