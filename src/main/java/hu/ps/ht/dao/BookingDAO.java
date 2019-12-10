package hu.ps.ht.dao;

import hu.ps.ht.entity.BookingEntity;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Singleton;
import javax.transaction.Transactional;

@Singleton
public class BookingDAO extends BaseDAO<BookingEntity> {

    @Transactional
    public List<BookingEntity> getBookingListQueryGuideNameAndDate(String guideUsername, LocalDate date) {

        return em.createQuery("Select b from BookingEntity b where b.guideName =:pName AND b.dateOfTour=:pDate")
                .setParameter("pName", guideUsername)
                .setParameter("pDate", date)
                .getResultList();
    }

    @Transactional
    public List<BookingEntity> getAllBookingsQueryGuideName(String guideUsername) {

        return em.createQuery("SELECT b FROM BookingEntity b WHERE b.guideName =:pName")
                .setParameter("pName", guideUsername)
                .getResultList();

    }

    @Transactional
    public List<BookingEntity> getAllBookingsByTravelerName(String travelername) {
        return em.createQuery("SELECT b FROM BookingEntity b WHERE b.travellerName=:tName")
                .setParameter("tName", travelername)
                .getResultList();
    }
}
