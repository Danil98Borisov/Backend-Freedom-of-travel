package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.models.HotelPreview;
import nc.project.service.HotelPreviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/hotelPreview")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HotelPreviewController {

    private final HotelPreviewService hotelPreviewService;

    @GetMapping("/find/preview")
    List<HotelPreview> findAvailableApartmentsPreview(@RequestParam(required = false, defaultValue = "0") int rating,
                                                      @RequestParam(required = false, defaultValue = "") String city) {
        return hotelPreviewService.getFilteredHotelPreviews(city, rating);
    }

}