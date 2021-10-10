package nc.project.controller;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Apartment;
import nc.project.models.ApartmentDetails;
import nc.project.jpa.repository.ApartmentRepository;
import nc.project.service.ApartmentDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartment")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ApartmentController {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentDetailsService apartmentDetailsService;

        /*Apartment*/
    @GetMapping("/all")
    public Iterable<Apartment> findAll() {
        return apartmentRepository.findAll();
    }

    @GetMapping("/apart-in-hotel")
    public List<Apartment> getApartmentInHotel(@RequestParam Long id){
        return this.apartmentRepository.getApartmentInHotel(id);
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

    /* Details */

    @GetMapping("/details/{id}")
    public ApartmentDetails detailsApartment(@PathVariable("id") Long id) {
        return apartmentDetailsService.getApartmentDetails(id);
    }

    @PostMapping("/details/edit")
    public ApartmentDetails detailsApartment(@RequestBody ApartmentDetails apartmentDetails) {
        return apartmentDetailsService.editApartmentDetails(apartmentDetails);
    }

}