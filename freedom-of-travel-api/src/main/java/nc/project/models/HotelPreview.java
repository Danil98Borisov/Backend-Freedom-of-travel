package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPreview {

    public Hotel hotel;
    public ImageHotel imageHotel;

}
