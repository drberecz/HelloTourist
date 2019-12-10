package hu.ps.ht.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class ReservationEntity extends BaseEntity {

    @Column(name = "reserved_date")
    LocalDate reservedDate;

    String guideUsername;

    public ReservationEntity() {
    }

    public ReservationEntity(LocalDate reservedDate, String guideUsername, GuideEntity guide) {
        this.reservedDate = reservedDate;
        this.guideUsername = guideUsername;
        this.guide = guide;
    }

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private GuideEntity guide;

    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }

    public String getGuideUsername() {
        return guideUsername;
    }

    public void setGuideUsername(String guideUsername) {
        this.guideUsername = guideUsername;
    }

    public GuideEntity getGuide() {
        return guide;
    }

    public void setGuide(GuideEntity guide) {
        this.guide = guide;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.reservedDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationEntity other = (ReservationEntity) obj;
        if (!Objects.equals(this.reservedDate, other.reservedDate)) {
            return false;
        }
        return true;
    }

}
//equals
