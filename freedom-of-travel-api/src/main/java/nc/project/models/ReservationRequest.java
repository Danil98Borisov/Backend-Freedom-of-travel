package nc.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private Long apartmentId;
    private String bookingBy;
    private LocalDate startDate;
    private LocalDate endDate;
}

