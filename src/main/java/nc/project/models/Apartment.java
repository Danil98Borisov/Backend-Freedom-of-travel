package nc.project.models;

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

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private float price;

    @Column(name = "raster", length = Integer.MAX_VALUE, nullable = true)
    private byte[] raster;

    public Apartment(Long id) {
        this.id = id;
    }

}