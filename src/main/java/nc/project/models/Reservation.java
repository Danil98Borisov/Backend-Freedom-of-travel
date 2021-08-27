package nc.project.models;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table( name = "reservation")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="hotelid")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomid")
    private Room room;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return start_date;
    }

    public void setStartDate(LocalDate startDate) {
        this.start_date = start_date;
    }

    public LocalDate getEndDate() {
        return end_date;
    }

    public void setEndDate(LocalDate endDate) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Reservation(Long id, Hotel hotel, Room room, LocalDate start_date, LocalDate end_date, String status) {
        this.id = id;
        this.hotel = hotel;
        this.room = room;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
    }

    public Reservation(){}


}



