package hu.ps.ht.dto;

import hu.ps.ht.enumerated.BookingStatus;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class BookingDTO {

    private Long id;
    private Long cost;
    private String description;
    private LocalDate dateOfTour;
    private String guideName;
    private String travellerName;
    private BookingStatus bookingStatus;

    private StringBuffer messageBoard;

}
