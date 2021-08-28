package nc.project.UploadBaseData;

import nc.project.models.Hotel;
import nc.project.models.Reservation;
import nc.project.models.Apartment;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface UploadBaseDataService {
    Map<String, List> createTables() throws IOException, ParseException;

    void uploadBaseData() throws IOException, ParseException;

    List<Hotel> getHotelList();

    List<Apartment> getApartmentList();

    List<Reservation> getReservationList();


}
