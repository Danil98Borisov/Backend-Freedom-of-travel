package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nc.project.jpa.entity.Hotel;
import nc.project.jpa.entity.ImageHotel;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDetails implements Serializable {

    public Hotel hotel;
    public List<ImageHotel> hotelImages;
    public boolean editAllowed;

}
