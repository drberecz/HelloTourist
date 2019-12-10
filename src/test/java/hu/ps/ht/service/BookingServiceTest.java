
package hu.ps.ht.service;

import hu.ps.ht.dao.BookingDAO;
import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.entity.BookingEntity;
import hu.ps.ht.enumerated.BookingStatus;
import hu.ps.ht.enumerated.Role;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    public BookingServiceTest() {
    }

    private BookingService underTests;
    private static final String GUIDE_NAME = "Borisz";
    private static final String TRAVELER_NAME = "Hikari";
    private static final String DESCRPITION = "this is a desc";
    private static final Long COST = 1000L;
    private static final Long ID = 1L;
    
    @Mock
    BookingDAO bookingDAO;
    @Mock
    BookingDTO bookingDTO;
    
    @Mock
    BookingEntity bookingEntity;

    @InjectMocks
    private BookingService myBookingService;

    @Test
    public void testCancelBooking_BookinStatusConfirmedOrAwaitingChangedToCancelled_Success() throws Exception {

        underTests = new BookingService(bookingDAO);

        BookingDTO bookingDTOConfirmed = new BookingDTO(ID, COST, DESCRPITION, LocalDate.now(), GUIDE_NAME, TRAVELER_NAME, BookingStatus.CONFIRMED, new StringBuffer());
        BookingDTO bookingDTOAwaitingConfirmation = new BookingDTO(ID, COST, DESCRPITION, LocalDate.now(), GUIDE_NAME, TRAVELER_NAME, BookingStatus.AWAITING_CONFIRMATION, new StringBuffer());
        BookingEntity bookingEntityComfirmed = new BookingEntity(DESCRPITION, COST, LocalDate.now(), GUIDE_NAME, TRAVELER_NAME, BookingStatus.CONFIRMED, new StringBuffer());
        BookingEntity bookingEntityAwaitingConfirmation = new BookingEntity(DESCRPITION, COST, LocalDate.now(), GUIDE_NAME, TRAVELER_NAME, BookingStatus.AWAITING_CONFIRMATION, new StringBuffer());

        when(bookingDAO.find(BookingEntity.class, ID)).thenReturn(bookingEntityComfirmed);
        when(bookingDAO.updateEntity(bookingEntityComfirmed)).thenReturn(bookingEntityComfirmed);
        underTests.cancelBooking(bookingDTOAwaitingConfirmation);

        assertEquals(BookingStatus.CANCELLED, bookingEntityComfirmed.getBookingStatus());

        when(bookingDAO.find(BookingEntity.class, ID)).thenReturn(bookingEntityAwaitingConfirmation);
        when(bookingDAO.updateEntity(bookingEntityAwaitingConfirmation)).thenReturn(bookingEntityAwaitingConfirmation);
        underTests.cancelBooking(bookingDTOConfirmed);

        assertEquals(BookingStatus.CANCELLED, bookingEntityAwaitingConfirmation.getBookingStatus());
    }

    @Test
    public void testCompleteBooking_ConfirmedChangedToCompleted_Success() throws Exception {
        underTests = new BookingService(bookingDAO);

        BookingDTO bookingDTOConfirmed = new BookingDTO(ID, COST, DESCRPITION, LocalDate.now(), GUIDE_NAME, TRAVELER_NAME, BookingStatus.CONFIRMED, new StringBuffer());
        BookingEntity bookingEntityComfirmed = new BookingEntity(DESCRPITION, COST, LocalDate.now(), GUIDE_NAME, TRAVELER_NAME, BookingStatus.CONFIRMED, new StringBuffer());

        when(bookingDAO.find(BookingEntity.class, ID)).thenReturn(bookingEntityComfirmed);
        when(bookingDAO.updateEntity(bookingEntityComfirmed)).thenReturn(bookingEntityComfirmed);
        underTests.completeBooking(bookingDTOConfirmed);

        assertEquals(BookingStatus.COMPLETED, bookingEntityComfirmed.getBookingStatus());

    }

    @Test
    public void testBookingCanBeCancelled_inputAWAITINGorCONFIRMED_ReturnTrue() throws Exception {

        underTests = new BookingService(bookingDAO);

        assertEquals(true, underTests.bookingCanBeCancelled((BookingStatus.AWAITING_CONFIRMATION)));
        assertEquals(true, underTests.bookingCanBeCancelled(BookingStatus.CONFIRMED));

    }

    @Test
    public void testBookingCanBeCancelled_input_COMPLETEDorEVALorCANCEL_ReturnsFalse() throws Exception {
        underTests = new BookingService(bookingDAO);

        assertEquals(false, underTests.bookingCanBeCancelled(BookingStatus.COMPLETED));
        assertEquals(false, underTests.bookingCanBeCancelled(BookingStatus.EVALUATED));
        assertEquals(false, underTests.bookingCanBeCancelled(BookingStatus.CANCELLED));

    }

    @Test
    public void testBookingCanBeEvaluated_inputEvaluatedCancelledAwaitingConfirmed_ReturnFalse_inputCompleted_ReturnTrue() throws Exception {
        underTests = new BookingService(bookingDAO);

        assertEquals(true, underTests.bookingCanBeEvaluated(BookingStatus.COMPLETED));
        assertEquals(false, underTests.bookingCanBeEvaluated(BookingStatus.EVALUATED));
        assertEquals(false, underTests.bookingCanBeEvaluated(BookingStatus.CANCELLED));
        assertEquals(false, underTests.bookingCanBeEvaluated(BookingStatus.AWAITING_CONFIRMATION));
        assertEquals(false, underTests.bookingCanBeEvaluated(BookingStatus.CONFIRMED));
    }

    @Test
    public void testCreateNewBooking_callCreate_success8() throws Exception {
        underTests = new BookingService(bookingDAO);
        underTests.createNewBooking(bookingDTO);
        verify(bookingDAO).createEntity(ArgumentMatchers.any());

    }

    @Test
    public void isActiveListedBooking_inputCONFIRMED_ReturnTrue_inputCANCELED_ReturnFalse() {
        underTests = new BookingService(bookingDAO);

        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingStatus(BookingStatus.CONFIRMED);
        bookingDTO.setDateOfTour(LocalDate.now().plusWeeks(1));
        BookingDTO bookingDTO2 = new BookingDTO();
        bookingDTO2.setBookingStatus(BookingStatus.CANCELLED);
        bookingDTO2.setDateOfTour(LocalDate.now());

        assertEquals(true, underTests.isActiveListedBooking(bookingDTO));
        assertEquals(false, underTests.isActiveListedBooking(bookingDTO2));

    }

    @Test
    public void testGetInactiveBookingList_Call_getInactiveBookingList_Success() {

        underTests = new BookingService(bookingDAO);
        List<BookingDTO> allBookingsList = new ArrayList<>();
        BookingDTO bookingDTO = new BookingDTO();
        BookingDTO bookingDTO2 = new BookingDTO();

        bookingDTO.setBookingStatus(BookingStatus.CONFIRMED);
        bookingDTO.setDateOfTour(LocalDate.now().plusDays(1));
        bookingDTO.setGuideName(GUIDE_NAME);

        bookingDTO2.setGuideName(GUIDE_NAME);
        bookingDTO2.setBookingStatus(BookingStatus.CONFIRMED);
        bookingDTO2.setDateOfTour(LocalDate.now().minusDays(1));

        allBookingsList.add(bookingDTO);
        allBookingsList.add(bookingDTO2);

        List<BookingEntity> allBookingsListEntity = new ArrayList<>();
        BookingEntity entity3 = new BookingEntity();
        BookingEntity entity4 = new BookingEntity();

        entity3.setBookingStatus(BookingStatus.CONFIRMED);
        entity3.setDateOfTour(LocalDate.now().plusDays(1));
        entity3.setGuideName(GUIDE_NAME);
        entity4.setGuideName(GUIDE_NAME);
        entity4.setBookingStatus(BookingStatus.CONFIRMED);
        entity4.setDateOfTour(LocalDate.now().minusDays(1));
        allBookingsListEntity.add(entity3);
        allBookingsListEntity.add(entity4);

        when(bookingDAO.getAllBookingsQueryGuideName(GUIDE_NAME)).thenReturn(allBookingsListEntity);
        assertEquals(1, (underTests.getInactiveBookingList(GUIDE_NAME, Role.GUIDE)).size());

    }

    @Test
    public void testGetActiveBookingList_Call_getActiveBookingList_Success() {

        underTests = new BookingService(bookingDAO);
        List<BookingDTO> allBookingsList = new ArrayList<>();
        BookingDTO bookingDTO = new BookingDTO();
        BookingDTO bookingDTO2 = new BookingDTO();

        bookingDTO.setBookingStatus(BookingStatus.CONFIRMED);
        bookingDTO.setDateOfTour(LocalDate.now().plusDays(1));
        bookingDTO.setGuideName(GUIDE_NAME);

        bookingDTO2.setGuideName(GUIDE_NAME);
        bookingDTO2.setBookingStatus(BookingStatus.CONFIRMED);
        bookingDTO2.setDateOfTour(LocalDate.now().minusDays(1));

        allBookingsList.add(bookingDTO);
        allBookingsList.add(bookingDTO2);

        List<BookingEntity> allBookingsListEntity = new ArrayList<>();
        BookingEntity entity3 = new BookingEntity();
        BookingEntity entity4 = new BookingEntity();

        entity3.setBookingStatus(BookingStatus.CONFIRMED);
        entity3.setDateOfTour(LocalDate.now().plusDays(1));
        entity3.setGuideName(GUIDE_NAME);
        entity4.setGuideName(GUIDE_NAME);
        entity4.setBookingStatus(BookingStatus.CONFIRMED);
        entity4.setDateOfTour(LocalDate.now().minusDays(1));
        allBookingsListEntity.add(entity3);
        allBookingsListEntity.add(entity4);

        when(bookingDAO.getAllBookingsQueryGuideName(GUIDE_NAME)).thenReturn(allBookingsListEntity);
        assertEquals(1, (underTests.getInactiveBookingList(GUIDE_NAME, Role.GUIDE)).size());

    }
}
