package nc.project.uploadBaseData;

import nc.project.models.Apartment;
import nc.project.models.Hotel;
import nc.project.models.Reservation;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface TestDataLoader {

    void uploadTestData() throws IOException, ParseException;

    List<Hotel> getHotelList();

    List<Apartment> getApartmentList();

    List<Reservation> getReservationList();
}