package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.models.Reservation;
import nc.project.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/hotels/filter")
    List<Reservation> dateHotel(@RequestParam String start_date,
                                @RequestParam String end_date,
                                @RequestParam (required = false, defaultValue = "0")  int rating,
                                @RequestParam (required = false) String city,
                                @RequestParam (required = false, defaultValue = "0") float price) {

        if (city == null) {
            return reservationRepository.findByHotelsByDateAndRatingAndPrice(LocalDate.parse(start_date, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse(end_date, DateTimeFormatter.ofPattern("yyyy-MM-dd")), rating, price);
        } else {
            return reservationRepository.findByHotelsByDateAndRatingAndCityAndPrice(LocalDate.parse(start_date, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.parse(end_date, DateTimeFormatter.ofPattern("yyyy-MM-dd")), rating, city, price);
        }
    }
}