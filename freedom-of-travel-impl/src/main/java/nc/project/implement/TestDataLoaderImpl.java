package nc.project.implement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.const_enum.ApartmentType;
import nc.project.const_enum.ERole;
import nc.project.const_enum.ReservationStatus;
import nc.project.jpa.entity.*;
import nc.project.jpa.repository.*;
import nc.project.service.TestDataLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestDataLoaderImpl implements TestDataLoader {

    private final HotelRepository hotelRepository;
    private final ApartmentRepository apartmentRepository;
    private final ImageApartmentRepository imageApartmentRepository;
    private final ImageHotelRepository imageHotelRepository;
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final String APARTMENT_IMAGES_PATH = "apartment-images/";
    private final String HOTEL_IMAGES_PATH = "hotel-images/";

    public void uploadTestData() {
        uploadRolesAndUsers();
        hotelRepository.saveAll(getHotelList());
        apartmentRepository.saveAll(getApartmentList());
        imageApartmentRepository.saveAll(getImageApartmentList());
        imageHotelRepository.saveAll(getImageHotelList());
        reservationRepository.saveAll(getReservationList());
    }

    public List<Hotel> getHotelList() {
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel(1L, "Oka", "NN", 7, null));
        hotelList.add(new Hotel(2L, "Azimut", "Kazan", 8, null));
        hotelList.add(new Hotel(3L, "Redisson", "MSC", 9, null));
        hotelList.add(new Hotel(4L, "Metropol", "NN", 10, null));
        hotelList.add(new Hotel(5L, "Noverel", "Krasnodar", 5, null));
        hotelList.add(new Hotel(6L, "Mercure", "Tver", 3, null));
        hotelList.add(new Hotel(7L, "Petr1", "SPB", 5, null));
        hotelList.add(new Hotel(8L, "SkyPoint", "Kazan", 9, null));
        hotelList.add(new Hotel(9L, "Izmailovo", "MSC", 2, null));
        hotelList.add(new Hotel(10L, "Karely", "Karelia", 10, null));
        hotelList.add(new Hotel(11L, "Oka", "Brest", 7, null));
        hotelList.add(new Hotel(12L, "Azimut", "Warsaw", 8, null));
        hotelList.add(new Hotel(13L, "Redisson", "Vienna", 9, null));
        hotelList.add(new Hotel(14L, "Metropol", "Havana", 10, null));
        hotelList.add(new Hotel(15L, "Noverel", "Hamburg", 5, null));
        hotelList.add(new Hotel(16L, "Mercure", "Donetsk", 3, null));
        hotelList.add(new Hotel(17L, "Petr1", "Vienna", 5, null));
        hotelList.add(new Hotel(18L, "SkyPoint", "Kazan", 9, null));
        hotelList.add(new Hotel(19L, "Izmailovo", "MSC", 2, null));
        hotelList.add(new Hotel(20L, "Karely", "Vienna", 10, null));
        hotelList.add(new Hotel(21L, "Oka", "NN", 6, null));
        hotelList.add(new Hotel(22L, "Azimut", "Warsaw", 8, null));
        hotelList.add(new Hotel(23L, "Redisson", "SPB", 9, null));
        hotelList.add(new Hotel(24L, "Metropol", "MSC", 10, null));
        hotelList.add(new Hotel(25L, "Noverel", "Karelia", 5, null));
        hotelList.add(new Hotel(26L, "Mercure", "Tver", 3, null));
        hotelList.add(new Hotel(27L, "Petr1", "SPB", 5, null));
        hotelList.add(new Hotel(28L, "SkyPoint", "Kazan", 9, null));
        hotelList.add(new Hotel(29L, "Izmailovo", "MSC", 2, null));
        hotelList.add(new Hotel(30L, "Karely", "Karelia", 10, null));

        for (Hotel hotel : hotelList) {
            hotel.setDescription(hotel.getId() + " " + hotel.getCity() + " " + hotel.getRating() + " " + hotel.getHotelName());
        }

        return hotelList;
    }

    public List<Reservation> getReservationList() {
        List<Reservation> reservationList = new ArrayList<>();
        ReservationStatus reservationStatus = null;

        reservationList.add(new Reservation(1L, new Apartment(1L), new User(1L), LocalDate.parse("2021-01-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(2L, new Apartment(4L), new User(1L), LocalDate.parse("2021-03-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(3L, new Apartment(1L), new User(1L), LocalDate.parse("2021-03-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(4L, new Apartment(5L), new User(1L), LocalDate.parse("2021-04-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-04-19", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(5L, new Apartment(2L), new User(1L), LocalDate.parse("2021-07-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(6L, new Apartment(23L), new User(1L), LocalDate.parse("2021-01-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(7L, new Apartment(25L), new User(1L), LocalDate.parse("2021-01-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(8L, new Apartment(28L), new User(1L), LocalDate.parse("2021-01-21", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(9L, new Apartment(55L), new User(1L), LocalDate.parse("2021-02-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(10L, new Apartment(12L), new User(1L), LocalDate.parse("2021-02-11", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(11L, new Apartment(18L), new User(1L), LocalDate.parse("2021-02-14", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(12L, new Apartment(33L), new User(1L), LocalDate.parse("2021-02-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-26", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(13L, new Apartment(54L), new User(1L), LocalDate.parse("2021-03-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-07", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(14L, new Apartment(41L), new User(1L), LocalDate.parse("2021-03-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(15L, new Apartment(28L), new User(1L), LocalDate.parse("2021-03-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(16L, new Apartment(37L), new User(2L), LocalDate.parse("2021-04-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-04-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(17L, new Apartment(37L), new User(2L), LocalDate.parse("2021-05-06", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-05-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(18L, new Apartment(34L), new User(2L), LocalDate.parse("2021-06-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-06-18", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(19L, new Apartment(42L), new User(2L), LocalDate.parse("2021-07-02", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(20L, new Apartment(40L), new User(2L), LocalDate.parse("2021-07-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-23", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(21L, new Apartment(43L), new User(2L), LocalDate.parse("2021-08-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-08-24", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(22L, new Apartment(45L), new User(2L), LocalDate.parse("2021-09-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-09-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(23L, new Apartment(47L), new User(2L), LocalDate.parse("2021-09-15", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-09-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(24L, new Apartment(46L), new User(2L), LocalDate.parse("2021-09-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-09-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(25L, new Apartment(48L), new User(2L), LocalDate.parse("2021-10-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-10-10", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(26L, new Apartment(49L), new User(2L), LocalDate.parse("2021-11-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-11-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(27L, new Apartment(50L), new User(2L), LocalDate.parse("2021-11-08", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-11-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(28L, new Apartment(51L), new User(2L), LocalDate.parse("2021-12-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-12-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(29L, new Apartment(31L), new User(2L), LocalDate.parse("2021-12-03", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-12-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(30L, new Apartment(11L), new User(2L), LocalDate.parse("2021-12-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-12-25", DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));

        return reservationList;

    }

    public List<Apartment> getApartmentList() {
        List<Apartment> apartmentList = new ArrayList<>();
        apartmentList.add(new Apartment(1L, new Hotel(4L), ApartmentType.SINGLE.name(), 100, null));
        apartmentList.add(new Apartment(2L, new Hotel(7L), ApartmentType.DOUBLE.name(), 80, null));
        apartmentList.add(new Apartment(3L, new Hotel(9L), ApartmentType.TRIPLE.name(), 45, null));
        apartmentList.add(new Apartment(4L, new Hotel(4L), ApartmentType.TRIPLE.name(), 123, null));
        apartmentList.add(new Apartment(5L, new Hotel(1L), ApartmentType.DOUBLE.name(), 21, null));
        apartmentList.add(new Apartment(6L, new Hotel(7L), ApartmentType.DOUBLE.name(), 37, null));
        apartmentList.add(new Apartment(7L, new Hotel(8L), ApartmentType.SINGLE.name(), 144, null));
        apartmentList.add(new Apartment(8L, new Hotel(2L), ApartmentType.SINGLE.name(), 34, null));
        apartmentList.add(new Apartment(9L, new Hotel(3L), ApartmentType.TRIPLE.name(), 111, null));
        apartmentList.add(new Apartment(10L, new Hotel(5L), ApartmentType.TRIPLE.name(), 19, null));
        apartmentList.add(new Apartment(11L, new Hotel(10L), ApartmentType.TRIPLE.name(), 140, null));
        apartmentList.add(new Apartment(12L, new Hotel(14L), ApartmentType.DOUBLE.name(), 100, null));
        apartmentList.add(new Apartment(13L, new Hotel(17L), ApartmentType.TRIPLE.name(), 23, null));
        apartmentList.add(new Apartment(14L, new Hotel(19L), ApartmentType.TRIPLE.name(), 14, null));
        apartmentList.add(new Apartment(15L, new Hotel(21L), ApartmentType.DOUBLE.name(), 134, null));
        apartmentList.add(new Apartment(16L, new Hotel(23L), ApartmentType.DOUBLE.name(), 53, null));
        apartmentList.add(new Apartment(17L, new Hotel(23L), ApartmentType.SINGLE.name(), 78, null));
        apartmentList.add(new Apartment(18L, new Hotel(15L), ApartmentType.SINGLE.name(), 32, null));
        apartmentList.add(new Apartment(19L, new Hotel(3L), ApartmentType.TRIPLE.name(), 56, null));
        apartmentList.add(new Apartment(20L, new Hotel(5L), ApartmentType.TRIPLE.name(), 34, null));
        apartmentList.add(new Apartment(21L, new Hotel(10L), ApartmentType.TRIPLE.name(), 32, null));
        apartmentList.add(new Apartment(21L, new Hotel(6L), ApartmentType.SINGLE.name(), 67, null));
        apartmentList.add(new Apartment(22L, new Hotel(7L), ApartmentType.DOUBLE.name(), 126, null));
        apartmentList.add(new Apartment(23L, new Hotel(8L), ApartmentType.TRIPLE.name(), 234, null));
        apartmentList.add(new Apartment(24L, new Hotel(9L), ApartmentType.TRIPLE.name(), 213, null));
        apartmentList.add(new Apartment(25L, new Hotel(10L), ApartmentType.DOUBLE.name(), 43, null));
        apartmentList.add(new Apartment(26L, new Hotel(7L), ApartmentType.DOUBLE.name(), 89, null));
        apartmentList.add(new Apartment(27L, new Hotel(13L), ApartmentType.SINGLE.name(), 79, null));
        apartmentList.add(new Apartment(28L, new Hotel(12L), ApartmentType.SINGLE.name(), 84, null));
        apartmentList.add(new Apartment(29L, new Hotel(3L), ApartmentType.TRIPLE.name(), 34, null));
        apartmentList.add(new Apartment(30L, new Hotel(5L), ApartmentType.TRIPLE.name(), 66, null));
        apartmentList.add(new Apartment(31L, new Hotel(11L), ApartmentType.TRIPLE.name(), 33, null));
        apartmentList.add(new Apartment(32L, new Hotel(17L), ApartmentType.DOUBLE.name(), 23, null));
        apartmentList.add(new Apartment(33L, new Hotel(16L), ApartmentType.TRIPLE.name(), 45, null));
        apartmentList.add(new Apartment(34L, new Hotel(15L), ApartmentType.TRIPLE.name(), 87, null));
        apartmentList.add(new Apartment(35L, new Hotel(1L), ApartmentType.DOUBLE.name(), 34, null));
        apartmentList.add(new Apartment(36L, new Hotel(7L), ApartmentType.DOUBLE.name(), 23, null));
        apartmentList.add(new Apartment(37L, new Hotel(20L), ApartmentType.SINGLE.name(), 34, null));
        apartmentList.add(new Apartment(38L, new Hotel(19L), ApartmentType.SINGLE.name(), 22, null));
        apartmentList.add(new Apartment(39L, new Hotel(3L), ApartmentType.TRIPLE.name(), 56, null));
        apartmentList.add(new Apartment(40L, new Hotel(22L), ApartmentType.TRIPLE.name(), 45, null));
        apartmentList.add(new Apartment(41L, new Hotel(18L), ApartmentType.SINGLE.name(), 76, null));
        apartmentList.add(new Apartment(42L, new Hotel(21L), ApartmentType.DOUBLE.name(), 43, null));
        apartmentList.add(new Apartment(43L, new Hotel(23L), ApartmentType.TRIPLE.name(), 89, null));
        apartmentList.add(new Apartment(44L, new Hotel(4L), ApartmentType.TRIPLE.name(), 54, null));
        apartmentList.add(new Apartment(45L, new Hotel(24L), ApartmentType.DOUBLE.name(), 143, null));
        apartmentList.add(new Apartment(46L, new Hotel(26L), ApartmentType.DOUBLE.name(), 242, null));
        apartmentList.add(new Apartment(47L, new Hotel(25L), ApartmentType.SINGLE.name(), 31, null));
        apartmentList.add(new Apartment(48L, new Hotel(27L), ApartmentType.SINGLE.name(), 54, null));
        apartmentList.add(new Apartment(49L, new Hotel(28L), ApartmentType.TRIPLE.name(), 77, null));
        apartmentList.add(new Apartment(50L, new Hotel(29L), ApartmentType.TRIPLE.name(), 89, null));
        apartmentList.add(new Apartment(51L, new Hotel(30L), ApartmentType.SINGLE.name(), 90, null));
        apartmentList.add(new Apartment(52L, new Hotel(24L), ApartmentType.DOUBLE.name(), 34, null));
        apartmentList.add(new Apartment(53L, new Hotel(26L), ApartmentType.TRIPLE.name(), 80, null));
        apartmentList.add(new Apartment(54L, new Hotel(17L), ApartmentType.TRIPLE.name(), 67, null));
        apartmentList.add(new Apartment(55L, new Hotel(13L), ApartmentType.DOUBLE.name(), 54, null));
        apartmentList.add(new Apartment(56L, new Hotel(8L), ApartmentType.DOUBLE.name(), 34, null));
        apartmentList.add(new Apartment(57L, new Hotel(6L), ApartmentType.SINGLE.name(), 63, null));
        apartmentList.add(new Apartment(58L, new Hotel(9L), ApartmentType.SINGLE.name(), 71, null));
        apartmentList.add(new Apartment(59L, new Hotel(5L), ApartmentType.TRIPLE.name(), 112, null));
        apartmentList.add(new Apartment(60L, new Hotel(3L), ApartmentType.TRIPLE.name(), 324, null));

        for (Apartment apartment : apartmentList) {
            apartment.setDescription(apartment.getId() + " " + apartment.getType() + " " + apartment.getPrice() + " " + apartment.getHotel().getHotelName());
        }

        return apartmentList;
    }

    public List<ImageApartment> getImageApartmentList() {
        List<ImageApartment> imageApartmentList = new ArrayList<>();

        imageApartmentList.add(new ImageApartment(1L, new Apartment(1L), convertApartmentImage("apartment_1_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(2L, new Apartment(1L), convertApartmentImage("apartment_1_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(3L, new Apartment(1L), convertApartmentImage("apartment_1_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(4L, new Apartment(2L), convertApartmentImage("apartment_2_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(5L, new Apartment(2L), convertApartmentImage("apartment_2_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(6L, new Apartment(2L), convertApartmentImage("apartment_2_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(7L, new Apartment(3L), convertApartmentImage("apartment_3_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(8L, new Apartment(3L), convertApartmentImage("apartment_3_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(9L, new Apartment(3L), convertApartmentImage("apartment_3_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(10L, new Apartment(4L), convertApartmentImage("apartment_4_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(11L, new Apartment(4L), convertApartmentImage("apartment_4_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(12L, new Apartment(4L), convertApartmentImage("apartment_4_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(13L, new Apartment(5L), convertApartmentImage("apartment_5_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(14L, new Apartment(5L), convertApartmentImage("apartment_5_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(15L, new Apartment(5L), convertApartmentImage("apartment_5_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(16L, new Apartment(6L), convertApartmentImage("apartment_6_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(17L, new Apartment(6L), convertApartmentImage("apartment_6_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(18L, new Apartment(6L), convertApartmentImage("apartment_6_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(19L, new Apartment(7L), convertApartmentImage("apartment_7_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(20L, new Apartment(7L), convertApartmentImage("apartment_7_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(21L, new Apartment(7L), convertApartmentImage("apartment_7_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(22L, new Apartment(8L), convertApartmentImage("apartment_8_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(23L, new Apartment(8L), convertApartmentImage("apartment_8_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(24L, new Apartment(8L), convertApartmentImage("apartment_8_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(25L, new Apartment(9L), convertApartmentImage("apartment_9_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(26L, new Apartment(9L), convertApartmentImage("apartment_9_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(27L, new Apartment(9L), convertApartmentImage("apartment_9_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(28L, new Apartment(10L), convertApartmentImage("apartment_10_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(29L, new Apartment(10L), convertApartmentImage("apartment_10_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(30L, new Apartment(10L), convertApartmentImage("apartment_10_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(31L, new Apartment(11L), convertApartmentImage("apartment_11_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(32L, new Apartment(11L), convertApartmentImage("apartment_11_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(33L, new Apartment(11L), convertApartmentImage("apartment_11_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(34L, new Apartment(12L), convertApartmentImage("apartment_12_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(35L, new Apartment(12L), convertApartmentImage("apartment_12_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(36L, new Apartment(12L), convertApartmentImage("apartment_12_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(37L, new Apartment(13L), convertApartmentImage("apartment_13_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(38L, new Apartment(13L), convertApartmentImage("apartment_13_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(39L, new Apartment(13L), convertApartmentImage("apartment_13_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(40L, new Apartment(14L), convertApartmentImage("apartment_14_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(41L, new Apartment(14L), convertApartmentImage("apartment_14_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(42L, new Apartment(14L), convertApartmentImage("apartment_14_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(43L, new Apartment(15L), convertApartmentImage("apartment_15_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(44L, new Apartment(15L), convertApartmentImage("apartment_15_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(45L, new Apartment(15L), convertApartmentImage("apartment_15_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(46L, new Apartment(16L), convertApartmentImage("apartment_16_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(47L, new Apartment(16L), convertApartmentImage("apartment_16_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(48L, new Apartment(16L), convertApartmentImage("apartment_16_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(49L, new Apartment(17L), convertApartmentImage("apartment_17_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(50L, new Apartment(17L), convertApartmentImage("apartment_17_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(51L, new Apartment(17L), convertApartmentImage("apartment_17_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(52L, new Apartment(18L), convertApartmentImage("apartment_18_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(53L, new Apartment(18L), convertApartmentImage("apartment_18_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(54L, new Apartment(18L), convertApartmentImage("apartment_18_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(55L, new Apartment(19L), convertApartmentImage("apartment_19_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(56L, new Apartment(19L), convertApartmentImage("apartment_19_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(57L, new Apartment(19L), convertApartmentImage("apartment_19_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(58L, new Apartment(20L), convertApartmentImage("apartment_20_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(59L, new Apartment(20L), convertApartmentImage("apartment_20_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(60L, new Apartment(20L), convertApartmentImage("apartment_20_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(61L, new Apartment(21L), convertApartmentImage("apartment_21_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(62L, new Apartment(21L), convertApartmentImage("apartment_21_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(63L, new Apartment(21L), convertApartmentImage("apartment_21_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(64L, new Apartment(22L), convertApartmentImage("apartment_22_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(65L, new Apartment(22L), convertApartmentImage("apartment_22_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(66L, new Apartment(22L), convertApartmentImage("apartment_22_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(67L, new Apartment(23L), convertApartmentImage("apartment_23_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(68L, new Apartment(23L), convertApartmentImage("apartment_23_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(69L, new Apartment(23L), convertApartmentImage("apartment_23_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(70L, new Apartment(24L), convertApartmentImage("apartment_24_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(71L, new Apartment(24L), convertApartmentImage("apartment_24_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(72L, new Apartment(24L), convertApartmentImage("apartment_24_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(72L, new Apartment(25L), convertApartmentImage("apartment_25_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(74L, new Apartment(25L), convertApartmentImage("apartment_25_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(75L, new Apartment(25L), convertApartmentImage("apartment_25_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(76L, new Apartment(26L), convertApartmentImage("apartment_26_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(77L, new Apartment(26L), convertApartmentImage("apartment_26_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(78L, new Apartment(26L), convertApartmentImage("apartment_26_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(79L, new Apartment(27L), convertApartmentImage("apartment_27_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(80L, new Apartment(27L), convertApartmentImage("apartment_27_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(81L, new Apartment(27L), convertApartmentImage("apartment_27_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(82L, new Apartment(28L), convertApartmentImage("apartment_28_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(83L, new Apartment(28L), convertApartmentImage("apartment_28_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(84L, new Apartment(28L), convertApartmentImage("apartment_28_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(85L, new Apartment(29L), convertApartmentImage("apartment_29_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(86L, new Apartment(29L), convertApartmentImage("apartment_29_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(87L, new Apartment(29L), convertApartmentImage("apartment_29_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(88L, new Apartment(30L), convertApartmentImage("apartment_30_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(89L, new Apartment(30L), convertApartmentImage("apartment_30_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(90L, new Apartment(30L), convertApartmentImage("apartment_30_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(91L, new Apartment(31L), convertApartmentImage("apartment_31_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(92L, new Apartment(31L), convertApartmentImage("apartment_31_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(93L, new Apartment(31L), convertApartmentImage("apartment_31_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(94L, new Apartment(32L), convertApartmentImage("apartment_32_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(95L, new Apartment(32L), convertApartmentImage("apartment_32_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(96L, new Apartment(32L), convertApartmentImage("apartment_32_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(97L, new Apartment(33L), convertApartmentImage("apartment_33_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(98L, new Apartment(33L), convertApartmentImage("apartment_33_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(99L, new Apartment(33L), convertApartmentImage("apartment_33_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(100L, new Apartment(34L), convertApartmentImage("apartment_34_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(101L, new Apartment(34L), convertApartmentImage("apartment_34_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(102L, new Apartment(34L), convertApartmentImage("apartment_34_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(103L, new Apartment(35L), convertApartmentImage("apartment_35_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(104L, new Apartment(35L), convertApartmentImage("apartment_35_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(105L, new Apartment(35L), convertApartmentImage("apartment_35_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(106L, new Apartment(36L), convertApartmentImage("apartment_36_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(107L, new Apartment(36L), convertApartmentImage("apartment_36_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(108L, new Apartment(36L), convertApartmentImage("apartment_36_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(109L, new Apartment(37L), convertApartmentImage("apartment_37_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(110L, new Apartment(37L), convertApartmentImage("apartment_37_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(111L, new Apartment(37L), convertApartmentImage("apartment_37_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(112L, new Apartment(38L), convertApartmentImage("apartment_38_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(113L, new Apartment(38L), convertApartmentImage("apartment_38_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(114L, new Apartment(38L), convertApartmentImage("apartment_38_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(115L, new Apartment(39L), convertApartmentImage("apartment_39_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(116L, new Apartment(39L), convertApartmentImage("apartment_39_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(117L, new Apartment(39L), convertApartmentImage("apartment_39_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(118L, new Apartment(40L), convertApartmentImage("apartment_40_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(119L, new Apartment(40L), convertApartmentImage("apartment_40_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(120L, new Apartment(40L), convertApartmentImage("apartment_40_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(121L, new Apartment(41L), convertApartmentImage("apartment_41_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(122L, new Apartment(41L), convertApartmentImage("apartment_41_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(123L, new Apartment(41L), convertApartmentImage("apartment_41_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(124L, new Apartment(42L), convertApartmentImage("apartment_42_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(125L, new Apartment(42L), convertApartmentImage("apartment_42_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(126L, new Apartment(42L), convertApartmentImage("apartment_42_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(127L, new Apartment(43L), convertApartmentImage("apartment_43_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(128L, new Apartment(43L), convertApartmentImage("apartment_43_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(129L, new Apartment(43L), convertApartmentImage("apartment_43_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(130L, new Apartment(44L), convertApartmentImage("apartment_44_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(131L, new Apartment(44L), convertApartmentImage("apartment_44_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(132L, new Apartment(44L), convertApartmentImage("apartment_44_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(133L, new Apartment(45L), convertApartmentImage("apartment_45_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(134L, new Apartment(45L), convertApartmentImage("apartment_45_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(135L, new Apartment(45L), convertApartmentImage("apartment_45_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(136L, new Apartment(46L), convertApartmentImage("apartment_46_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(137L, new Apartment(46L), convertApartmentImage("apartment_46_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(138L, new Apartment(46L), convertApartmentImage("apartment_46_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(139L, new Apartment(47L), convertApartmentImage("apartment_47_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(140L, new Apartment(47L), convertApartmentImage("apartment_47_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(141L, new Apartment(47L), convertApartmentImage("apartment_47_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(142L, new Apartment(48L), convertApartmentImage("apartment_48_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(143L, new Apartment(48L), convertApartmentImage("apartment_48_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(144L, new Apartment(48L), convertApartmentImage("apartment_48_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(145L, new Apartment(49L), convertApartmentImage("apartment_49_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(146L, new Apartment(49L), convertApartmentImage("apartment_49_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(147L, new Apartment(49L), convertApartmentImage("apartment_49_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(148L, new Apartment(50L), convertApartmentImage("apartment_50_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(149L, new Apartment(50L), convertApartmentImage("apartment_50_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(150L, new Apartment(50L), convertApartmentImage("apartment_50_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(151L, new Apartment(51L), convertApartmentImage("apartment_51_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(152L, new Apartment(51L), convertApartmentImage("apartment_51_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(153L, new Apartment(51L), convertApartmentImage("apartment_51_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(154L, new Apartment(52L), convertApartmentImage("apartment_52_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(155L, new Apartment(52L), convertApartmentImage("apartment_52_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(156L, new Apartment(52L), convertApartmentImage("apartment_52_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(157L, new Apartment(53L), convertApartmentImage("apartment_53_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(158L, new Apartment(53L), convertApartmentImage("apartment_53_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(159L, new Apartment(53L), convertApartmentImage("apartment_53_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(160L, new Apartment(54L), convertApartmentImage("apartment_54_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(161L, new Apartment(54L), convertApartmentImage("apartment_54_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(162L, new Apartment(54L), convertApartmentImage("apartment_54_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(163L, new Apartment(55L), convertApartmentImage("apartment_55_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(164L, new Apartment(55L), convertApartmentImage("apartment_55_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(165L, new Apartment(55L), convertApartmentImage("apartment_55_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(166L, new Apartment(56L), convertApartmentImage("apartment_56_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(167L, new Apartment(56L), convertApartmentImage("apartment_56_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(168L, new Apartment(56L), convertApartmentImage("apartment_56_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(169L, new Apartment(57L), convertApartmentImage("apartment_57_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(170L, new Apartment(57L), convertApartmentImage("apartment_57_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(171L, new Apartment(57L), convertApartmentImage("apartment_57_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(172L, new Apartment(58L), convertApartmentImage("apartment_58_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(173L, new Apartment(58L), convertApartmentImage("apartment_58_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(174L, new Apartment(58L), convertApartmentImage("apartment_58_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(175L, new Apartment(59L), convertApartmentImage("apartment_59_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(176L, new Apartment(59L), convertApartmentImage("apartment_59_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(177L, new Apartment(59L), convertApartmentImage("apartment_59_3.jpg"), 0));
        imageApartmentList.add(new ImageApartment(178L, new Apartment(60L), convertApartmentImage("apartment_60_1.jpg"), 1));
        imageApartmentList.add(new ImageApartment(179L, new Apartment(60L), convertApartmentImage("apartment_60_2.jpg"), 0));
        imageApartmentList.add(new ImageApartment(180L, new Apartment(60L), convertApartmentImage("apartment_60_3.jpg"), 0));

        return imageApartmentList;
    }

    public List<ImageHotel> getImageHotelList() {
        List<ImageHotel> imageHotelList = new ArrayList<>();

        imageHotelList.add(new ImageHotel(1L, new Hotel(1L), convertHotelImage("hotel_1_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(2L, new Hotel(1L), convertHotelImage("hotel_1_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(3L, new Hotel(1L), convertHotelImage("hotel_1_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(4L, new Hotel(2L), convertHotelImage("hotel_2_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(5L, new Hotel(2L), convertHotelImage("hotel_2_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(6L, new Hotel(2L), convertHotelImage("hotel_2_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(7L, new Hotel(3L), convertHotelImage("hotel_3_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(8L, new Hotel(3L), convertHotelImage("hotel_3_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(9L, new Hotel(3L), convertHotelImage("hotel_3_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(10L, new Hotel(4L), convertHotelImage("hotel_4_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(11L, new Hotel(4L), convertHotelImage("hotel_4_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(12L, new Hotel(4L), convertHotelImage("hotel_4_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(13L, new Hotel(5L), convertHotelImage("hotel_5_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(14L, new Hotel(5L), convertHotelImage("hotel_5_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(15L, new Hotel(5L), convertHotelImage("hotel_5_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(16L, new Hotel(6L), convertHotelImage("hotel_6_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(17L, new Hotel(6L), convertHotelImage("hotel_6_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(18L, new Hotel(6L), convertHotelImage("hotel_6_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(19L, new Hotel(7L), convertHotelImage("hotel_7_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(20L, new Hotel(7L), convertHotelImage("hotel_7_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(21L, new Hotel(7L), convertHotelImage("hotel_7_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(22L, new Hotel(8L), convertHotelImage("hotel_8_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(23L, new Hotel(8L), convertHotelImage("hotel_8_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(24L, new Hotel(8L), convertHotelImage("hotel_8_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(25L, new Hotel(9L), convertHotelImage("hotel_9_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(26L, new Hotel(9L), convertHotelImage("hotel_9_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(27L, new Hotel(9L), convertHotelImage("hotel_9_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(28L, new Hotel(10L), convertHotelImage("hotel_10_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(29L, new Hotel(10L), convertHotelImage("hotel_10_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(30L, new Hotel(10L), convertHotelImage("hotel_10_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(31L, new Hotel(11L), convertHotelImage("hotel_11_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(32L, new Hotel(11L), convertHotelImage("hotel_11_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(33L, new Hotel(11L), convertHotelImage("hotel_11_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(34L, new Hotel(12L), convertHotelImage("hotel_12_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(35L, new Hotel(12L), convertHotelImage("hotel_12_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(36L, new Hotel(12L), convertHotelImage("hotel_12_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(37L, new Hotel(13L), convertHotelImage("hotel_13_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(38L, new Hotel(13L), convertHotelImage("hotel_13_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(39L, new Hotel(13L), convertHotelImage("hotel_13_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(40L, new Hotel(14L), convertHotelImage("hotel_14_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(41L, new Hotel(14L), convertHotelImage("hotel_14_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(42L, new Hotel(14L), convertHotelImage("hotel_14_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(43L, new Hotel(15L), convertHotelImage("hotel_15_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(44L, new Hotel(15L), convertHotelImage("hotel_15_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(45L, new Hotel(15L), convertHotelImage("hotel_15_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(46L, new Hotel(16L), convertHotelImage("hotel_16_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(47L, new Hotel(16L), convertHotelImage("hotel_16_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(48L, new Hotel(16L), convertHotelImage("hotel_16_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(49L, new Hotel(17L), convertHotelImage("hotel_17_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(50L, new Hotel(17L), convertHotelImage("hotel_17_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(51L, new Hotel(17L), convertHotelImage("hotel_17_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(52L, new Hotel(18L), convertHotelImage("hotel_18_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(53L, new Hotel(18L), convertHotelImage("hotel_18_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(54L, new Hotel(18L), convertHotelImage("hotel_18_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(55L, new Hotel(19L), convertHotelImage("hotel_19_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(56L, new Hotel(19L), convertHotelImage("hotel_19_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(57L, new Hotel(19L), convertHotelImage("hotel_19_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(58L, new Hotel(20L), convertHotelImage("hotel_20_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(59L, new Hotel(20L), convertHotelImage("hotel_20_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(60L, new Hotel(20L), convertHotelImage("hotel_20_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(61L, new Hotel(21L), convertHotelImage("hotel_21_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(62L, new Hotel(21L), convertHotelImage("hotel_21_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(63L, new Hotel(21L), convertHotelImage("hotel_21_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(64L, new Hotel(22L), convertHotelImage("hotel_22_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(65L, new Hotel(22L), convertHotelImage("hotel_22_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(66L, new Hotel(22L), convertHotelImage("hotel_22_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(67L, new Hotel(23L), convertHotelImage("hotel_23_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(68L, new Hotel(23L), convertHotelImage("hotel_23_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(69L, new Hotel(23L), convertHotelImage("hotel_23_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(70L, new Hotel(24L), convertHotelImage("hotel_24_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(71L, new Hotel(24L), convertHotelImage("hotel_24_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(72L, new Hotel(24L), convertHotelImage("hotel_24_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(72L, new Hotel(25L), convertHotelImage("hotel_25_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(74L, new Hotel(25L), convertHotelImage("hotel_25_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(75L, new Hotel(25L), convertHotelImage("hotel_25_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(76L, new Hotel(26L), convertHotelImage("hotel_26_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(77L, new Hotel(26L), convertHotelImage("hotel_26_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(78L, new Hotel(26L), convertHotelImage("hotel_26_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(79L, new Hotel(27L), convertHotelImage("hotel_27_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(80L, new Hotel(27L), convertHotelImage("hotel_27_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(81L, new Hotel(27L), convertHotelImage("hotel_27_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(82L, new Hotel(28L), convertHotelImage("hotel_28_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(83L, new Hotel(28L), convertHotelImage("hotel_28_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(84L, new Hotel(28L), convertHotelImage("hotel_28_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(85L, new Hotel(29L), convertHotelImage("hotel_29_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(86L, new Hotel(29L), convertHotelImage("hotel_29_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(87L, new Hotel(29L), convertHotelImage("hotel_29_3.jpg"), 0));
        imageHotelList.add(new ImageHotel(88L, new Hotel(30L), convertHotelImage("hotel_30_1.jpg"), 1));
        imageHotelList.add(new ImageHotel(89L, new Hotel(30L), convertHotelImage("hotel_30_2.jpg"), 0));
        imageHotelList.add(new ImageHotel(90L, new Hotel(30L), convertHotelImage("hotel_30_3.jpg"), 0));

        return imageHotelList;
    }

    public byte[] convertApartmentImage(String fileName) {
       return readFileByName(APARTMENT_IMAGES_PATH + fileName.toLowerCase());
    }

    public byte[] convertHotelImage(String fileName) {
        return readFileByName(HOTEL_IMAGES_PATH + fileName.toLowerCase());
    }

    private byte[] readFileByName(String name) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(name);
        try {
            if (resource != null) {
                Path path = Paths.get(resource.toURI());
                byte[] bytes = Files.readAllBytes(path);
                return bytes;
            } else {
                log.warn("Resource with name '{}' is not found", name);
                return new byte[0];
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void uploadRolesAndUsers() {
        Role userRole = new Role(1L, ERole.ROLE_USER);
        Role advertiserRole = new Role(2L, ERole.ROLE_ADVERTISER);
        Role adminRole = new Role(3L, ERole.ROLE_ADMIN);
        roleRepository.saveAll(Arrays.asList(userRole, advertiserRole, adminRole));

        User admin = new User(1L, "Admin", "admin@mail.ru", encoder.encode("adminpass"),
                Collections.singleton(adminRole));
        User advertiser = new User(2L, "Advertiser", "advertiser@mail.ru", encoder.encode("advpass"),
                Collections.singleton(advertiserRole));
        userRepository.saveAll(Arrays.asList(admin, advertiser));
    }

}