package nc.project.service;

import lombok.extern.slf4j.Slf4j;
import nc.project.const_enum.EApartmentType;
import nc.project.jpa.entity.*;
import nc.project.jpa.repository.*;
import nc.project.models.HotelPreview;
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
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ApartmentTypeRepository apartmentTypeRepository;
    @Autowired
    private ImageApartmentRepository imageApartmentRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelPreviewService hotelPreviewService;
    @Autowired
    private HotelUserRepository hotelUserRepository;
    @Autowired
    private ImageHotelRepository imageHotelRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TestDataLoader testDataLoader;

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
        List<ApartmentType> apartmentTypes = apartmentTypeRepository.findAll();
        List<HotelUser> hotelUser = hotelUserRepository.findAll();

        assertEquals(60, apartments.size());
        assertEquals(179, apartmentImages.size());
        assertEquals(30, hotels.size());
        assertEquals(90, hotelImages.size());
        assertEquals(30, reservations.size());
        assertEquals(4, users.size());
        assertEquals(3, roles.size());
        assertEquals(3, apartmentTypes.size());
        assertEquals(30, hotelUser.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findSingleApartments(){
        List<HotelPreview> singleApartments = hotelPreviewService.getFilteredHotelPreviews(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "", 0, EApartmentType.SINGLE.toString(), Float.MAX_VALUE, "DESC", PageRequest.of(0, 60));
        assertEquals(9, singleApartments.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findApartmentsByCity(){
        List<HotelPreview> singleApartments = hotelPreviewService.getFilteredHotelPreviews(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "NN", 0, "", Float.MAX_VALUE, "DESC", PageRequest.of(0, 60));
        assertEquals(3, singleApartments.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findApartmentsByPrice(){
        List<HotelPreview> singleApartments = hotelPreviewService.getFilteredHotelPreviews(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "", 0, "", 77, "DESC", PageRequest.of(0, 60));
        assertEquals(11, singleApartments.size());
    }

    @Test
    public void testFindAvailableApartmentsPreview_findApartmentsByRating(){
        List<HotelPreview> singleApartments = hotelPreviewService.getFilteredHotelPreviews(
                LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse("2026-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                "", 6, "", Float.MAX_VALUE, "DESC", PageRequest.of(0, 60));
        assertEquals(6, singleApartments.size());
    }

}