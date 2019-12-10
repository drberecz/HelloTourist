package hu.ps.ht.dto;

import hu.ps.ht.entity.CategoryEntity;
import hu.ps.ht.enumerated.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EventDTO {

    private Long id;
    private String description;
    private Long maxParticipants;
    private Long cost;
    private String guideUserName;
    private String dayOfWeek;
    private CategoryEntity category;
    private Region region;
    private Double xGeoLatitude;
    private Double yGeoLongitude;

}
