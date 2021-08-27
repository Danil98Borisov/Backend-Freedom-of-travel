package nc.project.models;

import javax.persistence.*;


@Entity
@Table (name = "hotel")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Hotel(){}
    public Hotel(Long id) {
        this.id = id;
    }

    public Hotel(String hotelName, String city, int rating) {
        this.hotelName = hotelName;
        this.city = city;
        this.rating = rating;
    }

    public Hotel(Long id, String hotelName, String city, int rating) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.rating = rating;
    }
}
