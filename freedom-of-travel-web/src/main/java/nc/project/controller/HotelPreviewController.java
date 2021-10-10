package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.models.HotelPreview;
import nc.project.service.HotelPreviewService;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/hotelPreview")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HotelPreviewController {

    private final HotelPreviewService hotelPreviewService;

    @GetMapping("/details/preview")
    public List<HotelPreview> getApartmentPreview(){
        return this.hotelPreviewService.getHotelPreviews();
    }

    @GetMapping("/find/preview")
    List<HotelPreview> findAvailableHotelsPreview(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                          @RequestParam(required = false, defaultValue = "0") int rating,
                                                          @RequestParam(required = false, defaultValue = "") String city,
                                                          @RequestParam(required = false, defaultValue = "") String type,
                                                          @RequestParam(required = false, defaultValue = "10000000") float price,
                                                          @RequestParam Optional<Integer> page) {
        return hotelPreviewService.getFilteredHotelPreviews(startDate, endDate, city, rating, type, price,
                PageRequest.of(page.orElse(0), 5)
        );
    }
}