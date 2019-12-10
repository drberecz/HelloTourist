package hu.ps.ht.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "traveller")
public class TravellerEntity extends BaseEntity {

    @Column
    private String userName;

    @Column
    private String fullName;

    @Column
    private String country;

    @Column
    private String imageLink;

}
