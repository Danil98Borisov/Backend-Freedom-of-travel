package nc.project.controller;

import nc.project.models.Apartment;
import nc.project.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApartmentController {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/apartments")
    public Iterable<Apartment> findAll() {

        return apartmentRepository.findAll();
    }

    @PutMapping("/apartments/edit")
    Apartment editApartment(@RequestBody Apartment apartment) {
        return apartmentRepository.save(apartment);
    }
}