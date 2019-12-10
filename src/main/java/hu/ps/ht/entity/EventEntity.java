package hu.ps.ht.entity;

import hu.ps.ht.enumerated.Region;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "event")
public class EventEntity extends BaseEntity {

    @Column
    private String description;

    @Column
    private Long maxParticipants;

    @Column
    private Long cost;

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private GuideEntity guide;

    @Column(length = 64)
    String dayOfWeek;

    @ManyToOne
    private CategoryEntity category;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Column
    private Double xGeoLatitude;

    @Column
    private Double yGeoLongitude;

}
