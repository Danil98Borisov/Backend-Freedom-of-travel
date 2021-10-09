package nc.project.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nc.project.jpa.entity.Apartment;

import javax.persistence.*;

@Entity
@Table(name = "image_apartment")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ImageApartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name="apartment_type_id")
    private ApartmentType apartmentType;

    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Column(name = "flag")
    private int flag;

    public ImageApartment(Long id) {
        this.id = id;
    }

}
