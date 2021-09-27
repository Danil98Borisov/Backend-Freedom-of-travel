package nc.project.service;

import nc.project.jpa.entity.Apartment;
import nc.project.jpa.entity.Hotel;
import nc.project.jpa.entity.Reservation;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface TestDataLoader {

    void uploadTestData() throws IOException, ParseException;

    List<Hotel> getHotelList();

    List<Apartment> getApartmentList();

    List<Reservation> getReservationList();
}