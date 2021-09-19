package nc.project.controller;

import lombok.extern.slf4j.Slf4j;
import nc.project.models.ApartmentPreview;
import nc.project.service.ApartmentPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/apartmentPreview")
@CrossOrigin(origins = "http://localhost:4200")
public class ApartmentPreviewController {

/*    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ImageApartmentRepository imageApartmentRepository;*/

    @Autowired
    private ApartmentPreviewService apartmentPreviewService;

    @GetMapping("/details/preview")
    public List<ApartmentPreview> getApartmentPreview(){
        return this.apartmentPreviewService.getApartmentPreviews();
    }

}