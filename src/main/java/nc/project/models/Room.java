package nc.project.models;

import javax.persistence.*;

@Entity
@Table (name = "room")
public class Room {

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

    public Room(){}

    public Room(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room(Long id) {
        this.id = id;
    }

    public Room(Long id, Hotel hotel, String type, float price) {
        this.id = id;
        this.hotel = hotel;
        this.type = type;
        this.price = price;
    }
}
