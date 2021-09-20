package nc.project.controller;

import lombok.RequiredArgsConstructor;
import nc.project.const_enum.ApartmentType;
import nc.project.models.Apartment;
import nc.project.models.ImageApartment;
import nc.project.repository.ApartmentRepository;
import nc.project.repository.ImageApartmentRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/apartment")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentRepository apartmentRepository;
    private final ImageApartmentRepository imageApartmentRepository;

    @GetMapping("/all")
    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
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

    @GetMapping("/details/{id}")
    List<ImageApartment> detailsApartment(@PathVariable("id") Long id) {
        return imageApartmentRepository.detailsApartment(id);
    }

    @GetMapping("/details/all")
    public Iterable<ImageApartment> findAllDetails() {
        return imageApartmentRepository.findAll();
    }

    @GetMapping("/details/flag/{apartment_id}")
    List<ImageApartment> detailsApartmentByFlag(@PathVariable("apartment_id") int apartment_id) {
        return imageApartmentRepository.detailsApartmentByFlag(apartment_id);
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