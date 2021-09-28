package nc.project.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nc.project.jpa.entity.Apartment;
import nc.project.jpa.entity.Hotel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table( name = "reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "status")
    private String status;

}