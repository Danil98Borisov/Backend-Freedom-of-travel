package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nc.project.jpa.entity.Hotel;
import nc.project.jpa.entity.ImageHotel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPreview {

    public Hotel hotel;
    public ImageHotel imageHotel;

}
