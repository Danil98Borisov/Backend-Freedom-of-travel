package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.service.ApartmentPreviewService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/apartmentPreview")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ApartmentPreviewController {

    private final ApartmentPreviewService apartmentPreviewService;

}