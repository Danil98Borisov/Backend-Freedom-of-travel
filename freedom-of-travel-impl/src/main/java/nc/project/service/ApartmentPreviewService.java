package nc.project.service;

import lombok.RequiredArgsConstructor;
import nc.project.jpa.entity.Apartment;
import nc.project.models.ApartmentPreview;
import nc.project.jpa.entity.ImageApartment;
import nc.project.jpa.repository.ApartmentRepository;
import nc.project.jpa.repository.ImageApartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApartmentPreviewService {

    private final ApartmentRepository apartmentRepository;
    private final ImageApartmentRepository imageApartmentRepository;

    public List<ApartmentPreview> getApartmentPreviews(Pageable pageable) {
        Page<Apartment> allApartments = apartmentRepository.findAll(pageable);
        Page<ImageApartment> allApartmentImages = imageApartmentRepository.findAll(pageable);

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

    private ImageApartment findApartmentImage(Page<ImageApartment> allApartmentImages, Apartment apartment) {
        ImageApartment image = null;
        for (ImageApartment img : allApartmentImages) {
            if (apartment == img.getApartment() && img.getFlag() == 1) {
                image = img;
            }
        }
        return image;
    }

    public List<ApartmentPreview> getFilteredApartmentPreviews(LocalDate startDate, LocalDate endDate,
                                                               String city, int rating, String apartmentType, float price, Pageable pageable) {
        List<Apartment> availableApartments = apartmentRepository.findAvailableApartments(startDate, endDate, city, rating, apartmentType, price,pageable);

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