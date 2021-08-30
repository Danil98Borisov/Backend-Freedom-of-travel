package nc.project.uploadBaseData;


import lombok.RequiredArgsConstructor;
import nc.project.const_enum.ReservationStatus;
import nc.project.const_enum.ApartmentType;
import nc.project.models.Hotel;
import nc.project.models.Reservation;
import nc.project.models.Apartment;
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
    private final ApartmentRepository roomRepository;
    private final ReservationRepository reservationRepository;


    public Map<String, List> createTables() throws ParseException {

        List<Hotel> hotelList = getHotelList();
        List<Apartment> roomList = getRoomList();
        List<Reservation> reservationList = getReservationList();


        Map<String, List> mapTable = new HashMap();
        mapTable.put("hotel", hotelList);
        mapTable.put("room", roomList);
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

    public List<Apartment> getRoomList() {
        List<Apartment> roomList = new ArrayList<>();
        ApartmentType roomTypeEnum = null;

        roomList.add(new Apartment(1L, new Hotel(4L), ApartmentType.SINGLE.name(), 100));
        roomList.add(new Apartment(2L, new Hotel(7L), ApartmentType.DOUBLE.name(), 80));
        roomList.add(new Apartment(3L, new Hotel(9L), ApartmentType.TRIPLE.name(), 45));
        roomList.add(new Apartment(4L, new Hotel(4L), ApartmentType.TRIPLE.name(), 123));
        roomList.add(new Apartment(5L, new Hotel(1L), ApartmentType.DOUBLE.name(), 21));
        roomList.add(new Apartment(6L, new Hotel(7L), ApartmentType.DOUBLE.name(), 37));
        roomList.add(new Apartment(7L, new Hotel(8L), ApartmentType.SINGLE.name(), 144));
        roomList.add(new Apartment(8L, new Hotel(2L), ApartmentType.SINGLE.name(), 34));
        roomList.add(new Apartment(9L, new Hotel(3L), ApartmentType.TRIPLE.name(), 111));
        roomList.add(new Apartment(10L, new Hotel(5L), ApartmentType.TRIPLE.name(), 19));
        roomList.add(new Apartment(11L, new Hotel(10L), ApartmentType.TRIPLE.name(), 140));
        return roomList;
    }



     public List<Reservation> getReservationList() {
        List<Reservation> reservationList = new ArrayList<>();
        ReservationStatus reservationStatusEnum = null;

       reservationList.add(new Reservation(1L, new Hotel(9L), new Apartment(3L), LocalDate.parse("2021-01-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
       reservationList.add(new Reservation(2L, new Hotel(4L), new Apartment(4L), LocalDate.parse("2021-03-06",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
       reservationList.add(new Reservation(3L, new Hotel(4L), new Apartment(1L), LocalDate.parse("2021-03-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
       reservationList.add(new Reservation(4L, new Hotel(1L), new Apartment(5L), LocalDate.parse("2021-04-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-04-19",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
       reservationList.add(new Reservation(5L, new Hotel(7L), new Apartment(2L), LocalDate.parse("2021-07-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatus.BOOKED.name()));
       return reservationList;

    }
        public void uploadBaseData () throws ParseException {
            Map<String, List> listTable = createTables();

            List<Hotel> hotelList = listTable.get("hotel");
            List<Apartment> roomList = listTable.get("room");
            List<Reservation> reservationList = listTable.get("reservation");


            hotelRepository.saveAll(hotelList);
            roomRepository.saveAll(roomList);
            reservationRepository.saveAll(reservationList);
        }
    }
