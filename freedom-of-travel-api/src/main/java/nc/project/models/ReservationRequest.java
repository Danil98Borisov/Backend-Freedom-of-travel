package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    public Long apartmentId;
    public String bookingBy;
    public LocalDate start_date;
    public LocalDate end_date;
}
