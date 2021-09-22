package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDetails implements Serializable {

    public Apartment apartment;
    public List<ImageApartment> apartmentImages;

}
