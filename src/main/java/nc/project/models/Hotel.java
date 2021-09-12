package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table (name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String hotelName;

    @Column(name = "city")
    private String city;

    @Column(name = "rating")
    private int rating;

    public Hotel(Long id) {
        this.id = id;
    }

    public Hotel(String hotelName, String city, int rating) {
        this.hotelName = hotelName;
        this.city = city;
        this.rating = rating;
    }
}