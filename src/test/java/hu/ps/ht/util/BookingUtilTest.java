//package hu.ps.ht.util;
//
//
//
//import hu.ps.ht.dto.BookingDTO;
//import hu.ps.ht.enumerated.BookingOperator;
//import hu.ps.ht.service.BookingService;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.junit.jupiter.api.Assertions;
//
//@ExtendWith(MockitoExtension.class)
//public class BookingUtilTest {
//    
//    public BookingUtilTest() {
//    }
//
//@InjectMocks
//BookingUtil underTest;
//
//@InjectMocks
//BookingService bookingService;
//    
//
//
//    @Test
//    public void TestOperationHelper() throws Exception {
//    
//        
//        BookingDTO bDTO = new BookingDTO();
//        String result = underTest.operationHelper(BookingOperator.CANCEL, bDTO);
//        Assertions.assertTrue(result instanceof String);
//        Assertions.assertTrue(!result.isEmpty());
//        
//        
//    }
//    
//}
//
//
//
//
//
//
//
//
//
//
