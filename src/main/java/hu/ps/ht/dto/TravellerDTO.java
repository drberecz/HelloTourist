package hu.ps.ht.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravellerDTO {

    private Long id;
    private String userName;
    private String fullName;
    private String country;
    private String imageLink;

    public String retrieveImageLink() {
        return imageLink;
    }

    public void setterImageLink(String link) {
        imageLink = link;
    }
}
