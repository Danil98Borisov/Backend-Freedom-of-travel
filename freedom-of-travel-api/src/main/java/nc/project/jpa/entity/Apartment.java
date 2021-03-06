package nc.project.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "apartment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "apartment_type_id")
    private ApartmentType type;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    public Apartment(Long id) {
        this.id = id;
    }

}