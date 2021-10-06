package nc.project.service;

import lombok.extern.slf4j.Slf4j;
import nc.project.const_enum.EType;
import nc.project.jpa.entity.*;
import nc.project.jpa.repository.*;
import nc.project.models.ApartmentPreview;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApartmentPreviewServiceTest {

    @Autowired
    private ApartmentPreviewService apartmentPreviewService;
    @Autowired
    private TestDataLoader testDataLoader;

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ImageApartmentRepository imageApartmentRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ImageHotelRepository imageHotelRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeAll
    public void prepareTestEnv() throws IOException, ParseException {
        testDataLoader.uploadTestData();

        List<Apartment> apartments = apartmentRepository.findAll();
        List<ImageApartment> apartmentImages = imageApartmentRepository.findAll();
        List<Hotel> hotels = hotelRepository.findAll();
        List<ImageHotel> hotelImages = imageHotelRepository.findAll();
        List<Reservation> reservations = reservationRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        assertEquals(60, apartments.size());
        assertEquals(179, apartmentImages.size());
        assertEquals(30, hotels.size());
        assertEquals(89, hotelImages.size());
        assertEquals(30, reservations.size());
        assertEquals(2, users.size());
        assertEquals(3, roles.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findSingleApartments(){
        List<ApartmentPreview> singleApartments = apartmentPreviewService.findAvailableApartmentsPreview(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "", 0, EType.SINGLE.toString(), Float.MAX_VALUE, PageRequest.of(0, 60));
        assertEquals(16, singleApartments.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findApartmentsByCity(){
        List<ApartmentPreview> apartmentsInNn = apartmentPreviewService.findAvailableApartmentsPreview(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "NN", 0, "", Float.MAX_VALUE, PageRequest.of(0, 60));
        assertEquals(7, apartmentsInNn.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findApartmentsByPrice(){
        List<ApartmentPreview> apartmentsWithPrice = apartmentPreviewService.findAvailableApartmentsPreview(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "", 0, "", 77, PageRequest.of(0, 60));
        assertEquals(35, apartmentsWithPrice.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findApartmentsByRating(){
        List<ApartmentPreview> apartmentsWithPrice = apartmentPreviewService.findAvailableApartmentsPreview(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "", 6, "", 77, PageRequest.of(0, 60));
        assertEquals(16, apartmentsWithPrice.size());
    }

}