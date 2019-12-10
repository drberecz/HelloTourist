package hu.ps.ht.entity;

import hu.ps.ht.enumerated.BookingStatus;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {

    @Column
    private String description;

    @Column
    private Long cost;

    @Column(nullable = false)
    private LocalDate dateOfTour;

    @Column(name = "guide_name", nullable = false)
    private String guideName;

    @Column(name = "traveller_name", nullable = false)
    private String travellerName;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(length = 4096)
    private StringBuffer messageBoard;

}
