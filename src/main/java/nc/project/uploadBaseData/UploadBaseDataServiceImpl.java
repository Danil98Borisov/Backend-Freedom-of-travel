package nc.project.uploadBaseData;

import lombok.RequiredArgsConstructor;
import nc.project.const_enum.ReservationStatus;
import nc.project.const_enum.ApartmentType;
import nc.project.models.Apartment;
import nc.project.models.Hotel;
import nc.project.models.Reservation;
import nc.project.repository.HotelRepository;
import nc.project.repository.ReservationRepository;
import nc.project.repository.ApartmentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UploadBaseDataServiceImpl implements UploadBaseDataService {

    private final JdbcTemplate jdbcTemplate;
    private final HotelRepository hotelRepository;
    private final ApartmentRepository apartmentRepository;
    private final ReservationRepository reservationRepository;


    public Map<String, List> createTables() {

        List<Hotel> hotelList = getHotelList();
        List<Apartment> apartmentList = getApartmentList();
        List<Reservation> reservationList = getReservationList();

        Map<String, List> mapTable = new HashMap();
        mapTable.put("hotel", hotelList);
        mapTable.put("apartment", apartmentList);
        mapTable.put("reservation", reservationList);

        return mapTable;
    }

    public List<Hotel> getHotelList() {
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel(1L, "Oka", "NN", 7));
        hotelList.add(new Hotel(2L, "Azimut", "Kazan", 8));
        hotelList.add(new Hotel(3L, "Redisson", "MSC", 9));
        hotelList.add(new Hotel(4L, "Metropol", "NN", 10));
        hotelList.add(new Hotel(5L, "Noverel", "Krasnodar", 5));
        hotelList.add(new Hotel(6L, "Mercure", "Tver", 3));
        hotelList.add(new Hotel(7L, "Petr1", "SPB", 5));
        hotelList.add(new Hotel(8L, "SkyPoint", "Kazan", 9));
        hotelList.add(new Hotel(9L, "Izmailovo", "MSC", 2));
        hotelList.add(new Hotel(10L, "Karely", "Karelia", 10));
        hotelList.add(new Hotel(11L, "Oka", "Brest", 7));
        hotelList.add(new Hotel(12L, "Azimut", "Warsaw", 8));
        hotelList.add(new Hotel(13L, "Redisson", "Vienna", 9));
        hotelList.add(new Hotel(14L, "Metropol", "Havana", 10));
        hotelList.add(new Hotel(15L, "Noverel", "Hamburg", 5));
        hotelList.add(new Hotel(16L, "Mercure", "Donetsk", 3));
        hotelList.add(new Hotel(17L, "Petr1", "Vienna", 5));
        hotelList.add(new Hotel(18L, "SkyPoint", "Kazan", 9));
        hotelList.add(new Hotel(19L, "Izmailovo", "MSC", 2));
        hotelList.add(new Hotel(20L, "Karely", "Vienna", 10));
        hotelList.add(new Hotel(21L, "Oka", "NN", 6));
        hotelList.add(new Hotel(22L, "Azimut", "Warsaw", 8));
        hotelList.add(new Hotel(23L, "Redisson", "SPB", 9));
        hotelList.add(new Hotel(24L, "Metropol", "MSC", 10));
        hotelList.add(new Hotel(25L, "Noverel", "Karelia", 5));
        hotelList.add(new Hotel(26L, "Mercure", "Tver", 3));
        hotelList.add(new Hotel(27L, "Petr1", "SPB", 5));
        hotelList.add(new Hotel(28L, "SkyPoint", "Kazan", 9));
        hotelList.add(new Hotel(29L, "Izmailovo", "MSC", 2));
        hotelList.add(new Hotel(30L, "Karely", "Karelia", 10));
        return hotelList;
    }

    public List<Apartment> getApartmentList() {
        List<Apartment> apartmentList = new ArrayList<>();

        apartmentList.add(new Apartment(1L, new Hotel(4L), ApartmentType.SINGLE.name(), 100));
        apartmentList.add(new Apartment(2L, new Hotel(7L), ApartmentType.DOUBLE.name(), 80));
        apartmentList.add(new Apartment(3L, new Hotel(9L), ApartmentType.TRIPLE.name(), 45));
        apartmentList.add(new Apartment(4L, new Hotel(4L), ApartmentType.TRIPLE.name(), 123));
        apartmentList.add(new Apartment(5L, new Hotel(1L), ApartmentType.DOUBLE.name(), 21));
        apartmentList.add(new Apartment(6L, new Hotel(7L), ApartmentType.DOUBLE.name(), 37));
        apartmentList.add(new Apartment(7L, new Hotel(8L), ApartmentType.SINGLE.name(), 144));
        apartmentList.add(new Apartment(8L, new Hotel(2L), ApartmentType.SINGLE.name(), 34));
        apartmentList.add(new Apartment(9L, new Hotel(3L), ApartmentType.TRIPLE.name(), 111));
        apartmentList.add(new Apartment(10L, new Hotel(5L), ApartmentType.TRIPLE.name(), 19));
        apartmentList.add(new Apartment(11L, new Hotel(10L), ApartmentType.TRIPLE.name(), 140));
        apartmentList.add(new Apartment(12L, new Hotel(14L), ApartmentType.DOUBLE.name(), 100));
        apartmentList.add(new Apartment(13L, new Hotel(17L), ApartmentType.TRIPLE.name(), 23));
        apartmentList.add(new Apartment(14L, new Hotel(19L), ApartmentType.TRIPLE.name(), 14));
        apartmentList.add(new Apartment(15L, new Hotel(21L), ApartmentType.DOUBLE.name(), 134));
        apartmentList.add(new Apartment(16L, new Hotel(23L), ApartmentType.DOUBLE.name(), 53));
        apartmentList.add(new Apartment(17L, new Hotel(23L), ApartmentType.SINGLE.name(), 78));
        apartmentList.add(new Apartment(18L, new Hotel(15L), ApartmentType.SINGLE.name(), 32));
        apartmentList.add(new Apartment(19L, new Hotel(3L), ApartmentType.TRIPLE.name(), 56));
        apartmentList.add(new Apartment(20L, new Hotel(5L), ApartmentType.TRIPLE.name(), 34));
        apartmentList.add(new Apartment(21L, new Hotel(10L), ApartmentType.TRIPLE.name(), 32));
        apartmentList.add(new Apartment(21L, new Hotel(6L), ApartmentType.SINGLE.name(), 67));
        apartmentList.add(new Apartment(22L, new Hotel(7L), ApartmentType.DOUBLE.name(), 126));
        apartmentList.add(new Apartment(23L, new Hotel(8L), ApartmentType.TRIPLE.name(), 234));
        apartmentList.add(new Apartment(24L, new Hotel(9L), ApartmentType.TRIPLE.name(), 213));
        apartmentList.add(new Apartment(25L, new Hotel(10L), ApartmentType.DOUBLE.name(), 43));
        apartmentList.add(new Apartment(26L, new Hotel(7L), ApartmentType.DOUBLE.name(), 89));
        apartmentList.add(new Apartment(27L, new Hotel(13L), ApartmentType.SINGLE.name(), 79));
        apartmentList.add(new Apartment(28L, new Hotel(12L), ApartmentType.SINGLE.name(), 84));
        apartmentList.add(new Apartment(29L, new Hotel(3L), ApartmentType.TRIPLE.name(), 34));
        apartmentList.add(new Apartment(30L, new Hotel(5L), ApartmentType.TRIPLE.name(), 66));
        apartmentList.add(new Apartment(31L, new Hotel(11L), ApartmentType.TRIPLE.name(), 33));
        apartmentList.add(new Apartment(32L, new Hotel(17L), ApartmentType.DOUBLE.name(), 23));
        apartmentList.add(new Apartment(33L, new Hotel(16L), ApartmentType.TRIPLE.name(), 45));
        apartmentList.add(new Apartment(34L, new Hotel(15L), ApartmentType.TRIPLE.name(), 87));
        apartmentList.add(new Apartment(35L, new Hotel(1L), ApartmentType.DOUBLE.name(), 34));
        apartmentList.add(new Apartment(36L, new Hotel(7L), ApartmentType.DOUBLE.name(), 23));
        apartmentList.add(new Apartment(37L, new Hotel(20L), ApartmentType.SINGLE.name(), 34));
        apartmentList.add(new Apartment(38L, new Hotel(19L), ApartmentType.SINGLE.name(), 22));
        apartmentList.add(new Apartment(39L, new Hotel(3L), ApartmentType.TRIPLE.name(), 56));
        apartmentList.add(new Apartment(40L, new Hotel(22L), ApartmentType.TRIPLE.name(), 45));
        apartmentList.add(new Apartment(41L, new Hotel(18L), ApartmentType.SINGLE.name(), 76));
        apartmentList.add(new Apartment(42L, new Hotel(21L), ApartmentType.DOUBLE.name(), 43));
        apartmentList.add(new Apartment(43L, new Hotel(23L), ApartmentType.TRIPLE.name(), 89));
        apartmentList.add(new Apartment(44L, new Hotel(4L), ApartmentType.TRIPLE.name(), 54));
        apartmentList.add(new Apartment(45L, new Hotel(24L), ApartmentType.DOUBLE.name(), 143));
        apartmentList.add(new Apartment(46L, new Hotel(26L), ApartmentType.DOUBLE.name(), 242));
        apartmentList.add(new Apartment(47L, new Hotel(25L), ApartmentType.SINGLE.name(), 31));
        apartmentList.add(new Apartment(48L, new Hotel(27L), ApartmentType.SINGLE.name(), 54));
        apartmentList.add(new Apartment(49L, new Hotel(28L), ApartmentType.TRIPLE.name(), 77));
        apartmentList.add(new Apartment(50L, new Hotel(29L), ApartmentType.TRIPLE.name(), 89));
        apartmentList.add(new Apartment(51L, new Hotel(30L), ApartmentType.SINGLE.name(), 90));
        apartmentList.add(new Apartment(52L, new Hotel(24L), ApartmentType.DOUBLE.name(), 34));
        apartmentList.add(new Apartment(53L, new Hotel(26L), ApartmentType.TRIPLE.name(), 80));
        apartmentList.add(new Apartment(54L, new Hotel(17L), ApartmentType.TRIPLE.name(), 67));
        apartmentList.add(new Apartment(55L, new Hotel(13L), ApartmentType.DOUBLE.name(), 54));
        apartmentList.add(new Apartment(56L, new Hotel(8L), ApartmentType.DOUBLE.name(), 34));
        apartmentList.add(new Apartment(57L, new Hotel(6L), ApartmentType.SINGLE.name(), 63));
        apartmentList.add(new Apartment(58L, new Hotel(9L), ApartmentType.SINGLE.name(), 71));
        apartmentList.add(new Apartment(59L, new Hotel(5L), ApartmentType.TRIPLE.name(), 112));
        apartmentList.add(new Apartment(60L, new Hotel(3L), ApartmentType.TRIPLE.name(), 324));
        return apartmentList;
    }

    public List<Reservation> getReservationList() {
        List<Reservation> reservationList = new ArrayList<>();
        ReservationStatus reservationStatus = null;

        reservationList.add(new Reservation(1L, new Hotel(9L), new Apartment(3L), LocalDate.parse("2021-01-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(2L, new Hotel(4L), new Apartment(4L), LocalDate.parse("2021-03-06",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(3L, new Hotel(4L), new Apartment(1L), LocalDate.parse("2021-03-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(4L, new Hotel(1L), new Apartment(5L), LocalDate.parse("2021-04-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-04-19",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(5L, new Hotel(7L), new Apartment(2L), LocalDate.parse("2021-07-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(6L, new Hotel(8L), new Apartment(23L), LocalDate.parse("2021-01-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-13",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(7L, new Hotel(10L), new Apartment(25L), LocalDate.parse("2021-01-15",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(8L, new Hotel(12L), new Apartment(28L), LocalDate.parse("2021-01-21",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-23",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(9L, new Hotel(13L), new Apartment(55L), LocalDate.parse("2021-02-01",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(10L, new Hotel(14L), new Apartment(12L), LocalDate.parse("2021-02-11",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-13",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(11L, new Hotel(15L), new Apartment(18L), LocalDate.parse("2021-02-14",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(12L, new Hotel(16L), new Apartment(33L), LocalDate.parse("2021-02-23",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-02-26",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(13L, new Hotel(17L), new Apartment(54L), LocalDate.parse("2021-03-02",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-07",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(14L, new Hotel(18L), new Apartment(41L), LocalDate.parse("2021-03-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-12",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(15L, new Hotel(19L), new Apartment(28L), LocalDate.parse("2021-03-13",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(16L, new Hotel(20L), new Apartment(37L), LocalDate.parse("2021-04-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-04-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(17L, new Hotel(20L), new Apartment(37L), LocalDate.parse("2021-05-06",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-05-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(18L, new Hotel(15L), new Apartment(34L), LocalDate.parse("2021-06-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-06-18",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(19L, new Hotel(21L), new Apartment(42L), LocalDate.parse("2021-07-02",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(20L, new Hotel(22L), new Apartment(40L), LocalDate.parse("2021-07-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-23",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(21L, new Hotel(23L), new Apartment(43L), LocalDate.parse("2021-08-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-08-24",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(22L, new Hotel(24L), new Apartment(45L), LocalDate.parse("2021-09-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-09-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(23L, new Hotel(25L), new Apartment(47L), LocalDate.parse("2021-09-15",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-09-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(24L, new Hotel(26L), new Apartment(46L), LocalDate.parse("2021-09-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-09-13",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(25L, new Hotel(27L), new Apartment(48L), LocalDate.parse("2021-10-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-10-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(26L, new Hotel(28L), new Apartment(49L), LocalDate.parse("2021-11-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-11-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(27L, new Hotel(29L), new Apartment(50L), LocalDate.parse("2021-11-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-11-13",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(28L, new Hotel(30L), new Apartment(51L), LocalDate.parse("2021-12-01",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-12-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(29L, new Hotel(11L), new Apartment(31L), LocalDate.parse("2021-12-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-12-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        reservationList.add(new Reservation(30L, new Hotel(10L), new Apartment(11L), LocalDate.parse("2021-12-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-12-25",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
        return reservationList;

    }

    public void uploadBaseData () throws ParseException {
        Map<String, List> listTable = createTables();

        List<Hotel> hotelList = listTable.get("hotel");
        List<Apartment> apartmentList = listTable.get("apartment");
        List<Reservation> reservationList = listTable.get("reservation");


        hotelRepository.saveAll(hotelList);
        apartmentRepository.saveAll(apartmentList);
        reservationRepository.saveAll(reservationList);
    }
}