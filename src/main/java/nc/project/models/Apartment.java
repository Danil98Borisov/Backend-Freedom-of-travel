package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

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

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private float price;

   /* @OneToMany(mappedBy = "apartment")
    //@JoinColumn(name = "id")
    private List<ImageApartment> apartmentImages;*/

    public Apartment(Long id) {
        this.id = id;
    }

}