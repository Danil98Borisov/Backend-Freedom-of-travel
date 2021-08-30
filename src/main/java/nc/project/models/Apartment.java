package nc.project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "apartment")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Apartment(){}

    public Apartment(Hotel hotel) {
        this.hotel = hotel;
    }

    public Apartment(Long id) {
        this.id = id;
    }

    public Apartment(Long id, Hotel hotel, String type, float price) {
        this.id = id;
        this.hotel = hotel;
        this.type = type;
        this.price = price;
    }
}