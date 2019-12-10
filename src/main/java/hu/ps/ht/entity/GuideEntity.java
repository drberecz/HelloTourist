package hu.ps.ht.entity;

import hu.ps.ht.enumerated.Region;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "guide")
public class GuideEntity extends BaseEntity implements Serializable {

    @Column(nullable = false, unique = true)
    private String userName;

    @ToString.Exclude
    @OneToMany(mappedBy = "guide")
    private Set<ReservationEntity> reservedDates;

    @Enumerated(EnumType.STRING)
    @Column
    private Region operatesInRegion;

    //@JoinColumn(name = "guide_id")
    @ToString.Exclude
    @OneToMany(mappedBy = "guide")
    private List<EventEntity> eventList;

    @Column(name = "num_of_reviews")
    private Integer numOfReviews;

    @Column(name = "score_sum")
    private Long scoreSum;

    @Column(name = "average_score")
    private Double averageScore;

    @Column(name = "imageLink")
    private String imageLink;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "email", nullable = true)
    private String email;

    public void addtoEventList(EventEntity eventEntity) {
        eventList.add(eventEntity);
    }

}
