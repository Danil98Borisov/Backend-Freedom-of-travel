package nc.project.uploadBaseData;


import lombok.RequiredArgsConstructor;
import nc.project.const_enum.ReservationStatusEnum;
import nc.project.const_enum.ApartmentTypeEnum;
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


    public Map<String, List> createTables() throws ParseException {

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
        return hotelList;
    }

    public List<Apartment> getApartmentList() {
        List<Apartment> apartmentList = new ArrayList<>();
        ApartmentTypeEnum roomTypeEnum = null;

        apartmentList.add(new Apartment(1L, new Hotel(4L), ApartmentTypeEnum.SINGLE.name(), 100));
        apartmentList.add(new Apartment(2L, new Hotel(7L), ApartmentTypeEnum.DOUBLE.name(), 80));
        apartmentList.add(new Apartment(3L, new Hotel(9L), ApartmentTypeEnum.TRIPLE.name(), 45));
        apartmentList.add(new Apartment(4L, new Hotel(4L), ApartmentTypeEnum.TRIPLE.name(), 123));
        apartmentList.add(new Apartment(5L, new Hotel(1L), ApartmentTypeEnum.DOUBLE.name(), 21));
        apartmentList.add(new Apartment(6L, new Hotel(7L), ApartmentTypeEnum.DOUBLE.name(), 37));
        apartmentList.add(new Apartment(7L, new Hotel(8L), ApartmentTypeEnum.SINGLE.name(), 144));
        apartmentList.add(new Apartment(8L, new Hotel(2L), ApartmentTypeEnum.SINGLE.name(), 34));
        apartmentList.add(new Apartment(9L, new Hotel(3L), ApartmentTypeEnum.TRIPLE.name(), 111));
        apartmentList.add(new Apartment(10L, new Hotel(5L), ApartmentTypeEnum.TRIPLE.name(), 19));
        apartmentList.add(new Apartment(11L, new Hotel(10L), ApartmentTypeEnum.TRIPLE.name(), 140));
        return apartmentList;
    }



    public List<Reservation> getReservationList() {
        List<Reservation> reservationList = new ArrayList<>();
        ReservationStatusEnum reservationStatusEnum = null;

        reservationList.add(new Reservation(1L, new Hotel(9L), new Apartment(3L), LocalDate.parse("2021-01-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
        reservationList.add(new Reservation(2L, new Hotel(4L), new Apartment(4L), LocalDate.parse("2021-03-06",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
        reservationList.add(new Reservation(3L, new Hotel(4L), new Apartment(1L), LocalDate.parse("2021-03-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
        reservationList.add(new Reservation(4L, new Hotel(1L), new Apartment(5L), LocalDate.parse("2021-04-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-04-19",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
        reservationList.add(new Reservation(5L, new Hotel(7L), new Apartment(2L), LocalDate.parse("2021-07-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
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
