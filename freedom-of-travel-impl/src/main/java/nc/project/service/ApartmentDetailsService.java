package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.models.Apartment;
import nc.project.models.ApartmentDetails;
import nc.project.models.ImageApartment;
import nc.project.repository.ApartmentRepository;
import nc.project.repository.ImageApartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentDetailsService {

    private final ApartmentRepository apartmentRepository;
    private final ImageApartmentRepository imageApartmentRepository;

    public ApartmentDetails getApartmentDetails(Long id) {
        ApartmentDetails apartmentDetails = new ApartmentDetails();
        apartmentRepository.findById(id).ifPresent(apartment -> {
            List<ImageApartment> images = imageApartmentRepository.findImageApartmentsByApartmentId(apartment.getId());

            apartmentDetails.setApartment(apartment);
            apartmentDetails.setApartmentImages(images);
        });
        return apartmentDetails;
    }

    public ApartmentDetails editApartmentDetails(ApartmentDetails apartmentDetails) {
        Apartment editedApartment = apartmentRepository.save(apartmentDetails.getApartment());
        apartmentDetails.setApartment(editedApartment);
        return apartmentDetails;
    }
}