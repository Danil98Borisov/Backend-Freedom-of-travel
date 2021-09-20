package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name="apartment_id")
    private Apartment apartment;

    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Column(name = "flag")
    private int flag;

    public ImageApartment(Long id) {
        this.id = id;
    }

}
