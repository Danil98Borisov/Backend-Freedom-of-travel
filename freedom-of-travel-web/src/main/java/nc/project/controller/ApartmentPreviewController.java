package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.const_enum.ApartmentType;
import nc.project.models.ApartmentPreview;
import nc.project.repository.ApartmentRepository;
import nc.project.repository.ImageApartmentRepository;
import nc.project.service.ApartmentPreviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/apartmentPreview")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ApartmentPreviewController {

    private final ApartmentPreviewService apartmentPreviewService;

    @GetMapping("/details/preview")
    public List<ApartmentPreview> getApartmentPreview(@RequestParam Optional<Integer> page) {
        return this.apartmentPreviewService.getApartmentPreviews(PageRequest.of(
                page.orElse(1),
                5
        ));
    }


    @GetMapping("/find/preview")
    List<ApartmentPreview> findAvailableApartmentsPreview(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                          @RequestParam(required = false, defaultValue = "0") int rating,
                                                          @RequestParam(required = false, defaultValue = "") String city,
                                                          @RequestParam(required = false) ApartmentType type,
                                                          @RequestParam(required = false, defaultValue = "10000000") float price,
                                                          @RequestParam Optional<Integer> page) {
        return apartmentPreviewService.getFilteredApartmentPreviews(startDate, endDate, city, rating, type != null ? type.name() : "", price,PageRequest.of(
                page.orElse(1),
                5
        ));
    }

}