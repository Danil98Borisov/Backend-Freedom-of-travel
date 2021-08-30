package nc.project.controller;

import nc.project.const_enum.ApartmentType;
import nc.project.models.Apartment;
import nc.project.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentRepository apartmentRepository;


    @GetMapping("/all")
    public Iterable<Apartment> findAll() {

        return apartmentRepository.findAll();
    }

    @PutMapping("/edit")
    Apartment editApartment(@RequestBody Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @GetMapping("/find")
    List<Apartment> findAvailableAppartmentsByRatingAndDates(@RequestParam String startDate,
                                                             @RequestParam String endDate,
                                                             @RequestParam(required = false, defaultValue = "0") int rating,
                                                             @RequestParam(required = false, defaultValue = "") String city,
                                                             @RequestParam(required = false) ApartmentType apartmentType) {
        return apartmentRepository.findAvailableAppartmentsByRatingAndDates(
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                city,
                rating,
                apartmentType != null ? apartmentType.name() : "");
    }
}