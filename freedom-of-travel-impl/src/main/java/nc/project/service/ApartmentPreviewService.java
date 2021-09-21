package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.models.Apartment;
import nc.project.models.ApartmentPreview;
import nc.project.models.ImageApartment;
import nc.project.repository.ApartmentRepository;
import nc.project.repository.ImageApartmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApartmentPreviewService {

    private final ApartmentRepository apartmentRepository;
    private final ImageApartmentRepository imageApartmentRepository;

    public List<ApartmentPreview> getApartmentPreviews() {
        List<Apartment> allApartments = apartmentRepository.findAll();
        List<ImageApartment> allApartmentImages = imageApartmentRepository.findAll();

        List<ApartmentPreview> apartmentPreviews = new ArrayList<>();
        for (Apartment apartment : allApartments) {
            ApartmentPreview apartmentPreview = new ApartmentPreview();
            apartmentPreview.setApartment(apartment);
            ImageApartment image = findApartmentImage(allApartmentImages, apartment);
            apartmentPreview.setImageApartment(image);
            apartmentPreviews.add(apartmentPreview);
        }

        return apartmentPreviews;
    }

    private ImageApartment findApartmentImage(List<ImageApartment> allApartmentImages, Apartment apartment) {
        ImageApartment image = null;
        for (ImageApartment img : allApartmentImages) {
            if (apartment == img.getApartment() && img.getFlag() == 1) {
                image = img;
            }
        }
        return image;
    }

    public List<ApartmentPreview> getFilteredApartmentPreviews(LocalDate startDate, LocalDate endDate,
                                                             String city,  int rating, String apartmentType, float price) {
        List<Apartment> availableApartments = apartmentRepository.findAvailableApartments(startDate, endDate, city, rating, apartmentType, price);

        List<ApartmentPreview> apartmentPreviews = new ArrayList<>();
        for (Apartment apartment : availableApartments) {
            ApartmentPreview apartmentPreview = new ApartmentPreview();
            apartmentPreview.setApartment(apartment);
            ImageApartment image = imageApartmentRepository.findImageApartmentByApartmentIdAndFlag(apartment.getId(), 1);
            apartmentPreview.setImageApartment(image);
            apartmentPreviews.add(apartmentPreview);
        }

        return apartmentPreviews;
    }
}