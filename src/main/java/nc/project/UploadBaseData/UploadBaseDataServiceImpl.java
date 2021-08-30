package nc.project.UploadBaseData;


import lombok.RequiredArgsConstructor;
import nc.project.cont.ReservationStatusEnum;
import nc.project.cont.RoomTypeEnum;
import nc.project.models.Hotel;
import nc.project.models.Reservation;
import nc.project.models.Room;
import nc.project.repository.HotelRepository;
import nc.project.repository.ReservationRepository;
import nc.project.repository.RoomRepository;
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
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;


    public Map<String, List> createTables() throws ParseException {

        List<Hotel> hotelList = getHotelList();
        List<Room> roomList = getRoomList();
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

    public List<Room> getRoomList() {
        List<Room> roomList = new ArrayList<>();
        RoomTypeEnum roomTypeEnum = null;

        roomList.add(new Room(1L, new Hotel(4L), RoomTypeEnum.SINGLE.name(), 100));
        roomList.add(new Room(2L, new Hotel(7L), RoomTypeEnum.DOUBLE.name(), 80));
        roomList.add(new Room(3L, new Hotel(9L), RoomTypeEnum.TRIPLE.name(), 45));
        roomList.add(new Room(4L, new Hotel(4L), RoomTypeEnum.TRIPLE.name(), 123));
        roomList.add(new Room(5L, new Hotel(1L), RoomTypeEnum.DOUBLE.name(), 21));
        roomList.add(new Room(6L, new Hotel(7L), RoomTypeEnum.DOUBLE.name(), 37));
        roomList.add(new Room(7L, new Hotel(8L), RoomTypeEnum.SINGLE.name(), 144));
        roomList.add(new Room(8L, new Hotel(2L), RoomTypeEnum.SINGLE.name(), 34));
        roomList.add(new Room(9L, new Hotel(3L), RoomTypeEnum.TRIPLE.name(), 111));
        roomList.add(new Room(10L, new Hotel(5L), RoomTypeEnum.TRIPLE.name(), 19));
        roomList.add(new Room(11L, new Hotel(10L), RoomTypeEnum.TRIPLE.name(), 140));
        return roomList;
    }



     public List<Reservation> getReservationList() {
        List<Reservation> reservationList = new ArrayList<>();
        ReservationStatusEnum reservationStatusEnum = null;

       reservationList.add(new Reservation(1L, new Hotel(9L), new Room(3L), LocalDate.parse("2021-01-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-01-03",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
       reservationList.add(new Reservation(2L, new Hotel(4L), new Room(4L), LocalDate.parse("2021-03-06",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-20",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
       reservationList.add(new Reservation(3L, new Hotel(4L), new Room(1L), LocalDate.parse("2021-03-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-03-10",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
       reservationList.add(new Reservation(4L, new Hotel(1L), new Room(5L), LocalDate.parse("2021-04-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-04-19",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
       reservationList.add(new Reservation(5L, new Hotel(7L), new Room(2L), LocalDate.parse("2021-07-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse("2021-07-08",DateTimeFormatter.ofPattern("yyyy-MM-dd")), ReservationStatusEnum.BOOKED.name()));
       return reservationList;

    }
        public void uploadBaseData () throws ParseException {
            Map<String, List> listTable = createTables();

            List<Hotel> hotelList = listTable.get("hotel");
            List<Room> roomList = listTable.get("room");
            List<Reservation> reservationList = listTable.get("reservation");


            hotelRepository.saveAll(hotelList);
            roomRepository.saveAll(roomList);
            reservationRepository.saveAll(reservationList);
        }
    }
