package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nc.project.jpa.entity.Apartment;
import nc.project.jpa.entity.ImageApartment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentPreview {

    public Apartment apartment;
    public ImageApartment imageApartment;

}
