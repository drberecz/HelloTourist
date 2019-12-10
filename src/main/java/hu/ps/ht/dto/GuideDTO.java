package hu.ps.ht.dto;

import hu.ps.ht.enumerated.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class GuideDTO {

    private Long id;
    private String userName;
    private Region operatesInRegion;
    private Integer numOfReviews;
    private Long scoreSum;
    private Double averageScore;
    private String imageLink;
    private String phone;
    private String email;

}
