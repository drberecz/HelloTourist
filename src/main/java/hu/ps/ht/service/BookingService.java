package hu.ps.ht.service;

import hu.ps.ht.dao.BookingDAO;
import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.entity.BookingEntity;
import hu.ps.ht.enumerated.BookingStatus;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.mapper.BookingMapper;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Stateless
public class BookingService {

    @Inject
    BookingDAO bookingDAO;

    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public OutcomeMessage cancelBooking(BookingDTO bookingDTO) {
        log.info("CANCEL called");
        BookingEntity bookingEntity = null;
        try {
            bookingEntity = bookingDAO.find(BookingEntity.class, bookingDTO.getId());
        } catch (EJBException ex) {
            log.error("CANCEL failed at find!");
            return OutcomeMessage.error;
        }
        bookingEntity.setBookingStatus(BookingStatus.CANCELLED);
        try {
            bookingDAO.updateEntity(bookingEntity);
            return OutcomeMessage.success;
        } catch (EJBException ex) {
            log.error("CANCEL failed on update!");
            return OutcomeMessage.error;
        }

    }

    public OutcomeMessage confirmBooking(BookingDTO bookingDTO) {

        BookingEntity bookingEntity = null;
        log.info("CONFIRM called");
        try {
            bookingEntity = bookingDAO.find(BookingEntity.class, bookingDTO.getId());
        } catch (EJBException ex) {
            log.error("CONFIRM failed at find!");
            return OutcomeMessage.error;
        }

        bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);
        try {
            bookingDAO.updateEntity(bookingEntity);
            return OutcomeMessage.success;
        } catch (EJBException ex) {
            log.error("CONFIRM failed on update!");
            return OutcomeMessage.error;
        }

    }

    public OutcomeMessage completeBooking(BookingDTO bookingDTO) throws IllegalArgumentException {
        log.info("COMPLETE called");
        BookingEntity bookingEntity = null;
        try {
            bookingEntity = bookingDAO.find(BookingEntity.class, bookingDTO.getId());
        } catch (EJBException ex) {
            log.error("COMPLETE failed at find!");
            return OutcomeMessage.error;
        }
        bookingEntity.setBookingStatus(BookingStatus.COMPLETED);
        try {
            bookingDAO.updateEntity(bookingEntity);
            return OutcomeMessage.success;
        } catch (EJBException ex) {
            log.error("COMPLETE failed on update!");
            return OutcomeMessage.error;
        }

    }

    public OutcomeMessage evaluatedBooking(BookingDTO bookingDTO) throws IllegalArgumentException {
        log.info("EVALUATION called");
        BookingEntity bookingEntity = null;
        try {
            bookingEntity = bookingDAO.find(BookingEntity.class, bookingDTO.getId());
        } catch (EJBException ex) {
            log.error("EVALUATION failed on find!");
            return OutcomeMessage.error;
        }
        bookingEntity.setBookingStatus(BookingStatus.EVALUATED);
        try {
            bookingDAO.updateEntity(bookingEntity);
            return OutcomeMessage.success;
        } catch (EJBException ex) {
            log.error("EVALUATION failed on update!");
            return OutcomeMessage.error;
        }

    }

    public void createNewBooking(BookingDTO bookingDTO) {

        BookingEntity bookingEntity = null;

        try {
            bookingEntity = BookingMapper.mapBookingDtoToEntity(bookingDTO);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(BookingService.class.getName()).log(Level.SEVERE, null, ex);
            log.error("CREATE BOOKING failed on MAPPING!");
        }

        bookingDAO.createEntity(bookingEntity);

    }

    public BookingDTO findBookingById(Long id) {

        BookingEntity bookingEntity = bookingDAO.find(BookingEntity.class, id);
        BookingDTO bookingDTO = null;

        try {
            bookingDTO = BookingMapper.mapBookingEntityToDTO(bookingEntity);
        } catch (InvocationTargetException | IllegalAccessException ex) {
            Logger.getLogger(BookingService.class.getName()).log(Level.SEVERE, null, ex);
            log.error("FIND BOOKING failed on MAPPING!");
        }

        return bookingDTO;

    }

    public List<BookingDTO> getBookingListQueryGuideNameAndDate(String username, LocalDate date) {

        List<BookingDTO> bookingDtoList = null;

        List<BookingEntity> bookingEntityList = bookingDAO.getBookingListQueryGuideNameAndDate(username, date);
        bookingDtoList = BookingMapper.mapBookingEntityListToDtoList(bookingEntityList);

        return bookingDtoList;

    }

    public List<BookingDTO> getAllBookingsQueryGuideName(String guideUsername) {
        List<BookingDTO> bookingDtoList = null;

        List<BookingEntity> bookingEntityList = bookingDAO.getAllBookingsQueryGuideName(guideUsername);
        bookingDtoList = BookingMapper.mapBookingEntityListToDtoList(bookingEntityList);

        return bookingDtoList;
    }

    public void checkForDateCollision(BookingDTO bookingDTO) throws EJBException {

        String guideName = bookingDTO.getGuideName();
        LocalDate queryDate = bookingDTO.getDateOfTour();

        List<BookingEntity> bookingEntityList
                = bookingDAO.getBookingListQueryGuideNameAndDate(guideName, queryDate);

        if (bookingEntityList != null && !bookingEntityList.isEmpty()) {
            log.debug("Date is already booked.");
            throw new EJBException("Sorry, this Date is already booked.");
        }

    }

    public void updateBooking(BookingDTO bdto) {
        BookingEntity booking = null;
        try {
            booking = BookingMapper.mapBookingDTOToEntity(bdto);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(BookingService.class.getName()).log(Level.SEVERE, null, ex);
            log.debug("UPDATE failed on MAPPING!");
        }
        bookingDAO.updateEntity(booking);
    }

    public List<BookingDTO> getAllBookingsByTravelerName(String travelername) {
        List<BookingEntity> entity = bookingDAO.getAllBookingsByTravelerName(travelername);
        return BookingMapper.mapBookingEntityListToDtoList(entity);
    }

    public List<BookingDTO> getAllBookingsByGuideName(String guideName) {
        List<BookingEntity> entity = bookingDAO.getAllBookingsQueryGuideName(guideName);
        return BookingMapper.mapBookingEntityListToDtoList(entity);
    }

    public List<BookingDTO> getActiveBookingList(String username, String ROLE) {
        List<BookingDTO> allBookingsList = null;

        switch (ROLE) {
            case Role.GUIDE:
                allBookingsList = getAllBookingsByGuideName(username);
                break;
            case Role.TRAVELER:
                allBookingsList = getAllBookingsByTravelerName(username);
                break;
            default:
                log.info("invalid entry");
        }

        List<BookingDTO> bookingDTOsListActive = new ArrayList<>();
        for (BookingDTO bookingDTO : allBookingsList) {
            if (isActiveListedBooking(bookingDTO)) {

                bookingDTOsListActive.add(bookingDTO);
                log.debug("added to actives");
            }
        }
        return bookingDTOsListActive;
    }

    public boolean isActiveListedBooking(BookingDTO bookingDTO) {

        return (bookingDTO.getDateOfTour().isAfter(LocalDate.now()) && bookingDTO.getBookingStatus() == BookingStatus.AWAITING_CONFIRMATION)
                || (bookingDTO.getDateOfTour().isAfter(LocalDate.now()) && bookingDTO.getBookingStatus() == BookingStatus.CONFIRMED);

    }

    public List<BookingDTO> getInactiveBookingList(String username, final String ROLE) {

        List<BookingDTO> allBookingsList = null;

        switch (ROLE) {
            case Role.GUIDE:
                allBookingsList = getAllBookingsByGuideName(username);
                break;
            case Role.TRAVELER:
                allBookingsList = getAllBookingsByTravelerName(username);
                break;
            default:
                log.info("invalid entry");
        }

        List<BookingDTO> bookingDTOsListInactive = new ArrayList<>();
        for (BookingDTO bookingDTO : allBookingsList) {
            if (!isActiveListedBooking(bookingDTO)) {
                bookingDTOsListInactive.add(bookingDTO);
                log.debug("added to inactives" + bookingDTO.toString());
            }
        }
        return bookingDTOsListInactive;
    }

    public boolean bookingCanBeCancelled(BookingStatus status) {
        return (status != BookingStatus.COMPLETED
                && status != BookingStatus.EVALUATED
                && status != BookingStatus.CANCELLED);
    }

    public boolean bookingCanBeEvaluated(BookingStatus bookingStatus) {
        return (bookingStatus == BookingStatus.COMPLETED);
    }

    public void changeBookingStatusToEvaluated(Long bookingId) {
        BookingEntity bookingEntity = bookingDAO.find(BookingEntity.class, bookingId);
        bookingEntity.setBookingStatus(BookingStatus.EVALUATED);
        bookingEntity.setVersion(null);
        bookingDAO.updateEntity(bookingEntity);
    }

}
