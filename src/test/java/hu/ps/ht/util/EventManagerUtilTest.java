package hu.ps.ht.util;

import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.enumerated.WeeklyPattern;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class EventManagerUtilTest {

    public EventManagerUtilTest() {
    }

    @Test
    public void TestStringConversion_GivenNullparam_ReturnNull() {

        List<EventDTO> dtoList = new ArrayList<>();
        List<EventDTO> expected = new ArrayList<>();

        List<EventDTO> underTest = EventManagerUtil.convertDTOsWeeklyPatternAttributesTo3CharNames(dtoList);

        Assertions.assertEquals(expected, underTest);

    }

    @Test
    public void TestStringConversion_GivenValidparam_ReturnModifiedValue() {

        List<EventDTO> dtoList = new ArrayList<>();
        EventDTO testEvent = new EventDTO();
        testEvent.setDayOfWeek(WeeklyPattern.WEEKENDS_ONLY);
        dtoList.add(testEvent);
        List<EventDTO> expectedList = new ArrayList<>();
        EventDTO expectedDTO = new EventDTO();
        expectedDTO.setDayOfWeek(" Sat Sun ");
        expectedList.add(expectedDTO);

        List<EventDTO> underTest = EventManagerUtil.convertDTOsWeeklyPatternAttributesTo3CharNames(dtoList);

        Assertions.assertEquals(expectedList.get(0).getDayOfWeek(), underTest.get(0).getDayOfWeek());

    }


    @Test
    public void TestStringToDoubleArrayConversion_GivenNull_ReturnNull(){
    
    Double[] result = EventManagerUtil.convertStringInputGeolocationToDouble(null);
    Assertions.assertEquals(null, result);
        
}

    @Test
    public void TestStringToDoubleArrayConversion_Given_ReturnNull(){
    
    Double[] result = EventManagerUtil.convertStringInputGeolocationToDouble(null);
    Assertions.assertEquals(null, result);
        
}

    @Test
    public void TestPatternToConstant_GivenNullInput_ReturnNull(){
    
    Object result = Assertions.assertThrows(AssertionError.class, ()->{EventManagerUtil.switchWeeklyPatternContants("apple");});
    Assertions.assertTrue(result.toString().contains("invalid"));
        
}
    @Test
    public void TestPatternToConstant_GivenValidInput_ReturnProperConstant(){
    
    String actual = EventManagerUtil.switchWeeklyPatternContants("2");
    Assertions.assertEquals(WeeklyPattern.VACANT_EXPECT_MONDAYS, actual);
        
}

    @Test
    public void TestStringtoGpsPointConversion_GivenValidInput_ReturnGPSarray(){
    
    Double[] actual= EventManagerUtil.convertStringInputGeolocationToDouble("LatLng(47.16894, 19.829426)");
    Double[] expected = new Double[]{47.16894, 19.829426};
    
    Assertions.assertEquals(expected[0], actual[0]);
    Assertions.assertEquals(expected[1], actual[1]);
        
}    

    @Test
    public void TestStringtoGpsPointConversion_GivenFaultyInput_ReturnGPSarray(){
    
    Double[] actual= EventManagerUtil.convertStringInputGeolocationToDouble("blablabalbal7.1689829426)");   
    Assertions.assertEquals(null, actual);

        
}    
    
    
}











