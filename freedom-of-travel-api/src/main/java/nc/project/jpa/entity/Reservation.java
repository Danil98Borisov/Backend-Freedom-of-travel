package nc.project.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private String status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "reservationDate")
    private LocalDateTime reservationDate;

    @Column(name = "modifiedWhen")
    private LocalDateTime modifiedWhen;

    @Column(name = "modifiedBy")
    private String modifiedBy;

    public Reservation(Long id, Apartment apartment, User user, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.apartment = apartment;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
}