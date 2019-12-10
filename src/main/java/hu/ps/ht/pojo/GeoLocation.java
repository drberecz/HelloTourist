package hu.ps.ht.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation {

    String name;
    Double lat;
    Double lon;

}
