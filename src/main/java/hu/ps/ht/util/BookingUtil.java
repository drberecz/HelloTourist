package hu.ps.ht.util;

import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.enumerated.BookingOperator;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.service.BookingService;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BookingUtil {

    @Inject
    BookingService bookingService;

    public String operationHelper(char select, BookingDTO bookingDTO) {
        OutcomeMessage result;
        String strResponse = "";
        switch (select) {
            case BookingOperator.CANCEL:

                result = bookingService.cancelBooking(bookingDTO);
                if (null != result) {
                    if (result == OutcomeMessage.success) {
                        strResponse = BookingOperator.CANCEL_SUCCESS;
                    } else {
                        strResponse = BookingOperator.CANCEL_FAIL;
                    }
                }

                ;
                break;
            case BookingOperator.CONFIRM:

                result = bookingService.confirmBooking(bookingDTO);
                if (null != result) {
                    if (result == OutcomeMessage.success) {
                        strResponse = BookingOperator.CONFIRM_SUCCESS;
                    } else {
                        strResponse = BookingOperator.CONFIRM_FAIL;
                    }
                }

                ;
                break;
            case BookingOperator.COMPLETE:

                result = bookingService.completeBooking(bookingDTO);
                if (null != result) {
                    if (result == OutcomeMessage.success) {
                        strResponse = BookingOperator.COMPLETE_SUCCESS;
                    } else {
                        strResponse = BookingOperator.COMPLETE_FAIL;
                    }
                }

        }
        return strResponse;

    }

}
