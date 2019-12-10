package hu.ps.ht.dto;

import hu.ps.ht.enumerated.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Csaba
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {

    private String email;
    private String password;
    private String userName;
    private String role;
    private String fullName;
    private String country;
    private Region operatesInRegion;
    private String imageLink;
    private String phone;

}
