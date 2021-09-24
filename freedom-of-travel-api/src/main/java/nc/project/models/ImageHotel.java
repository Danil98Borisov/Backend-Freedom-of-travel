package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "image_hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ImageHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Column(name = "flag")
    private int flag;

    public ImageHotel(Long id) {
        this.id = id;
    }

}
