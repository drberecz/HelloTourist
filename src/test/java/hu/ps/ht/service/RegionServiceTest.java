package hu.ps.ht.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

    @Mock
    public static RegionService underTest;

    @Test
    public void checkResult_GivenInvalidInput_ReturnFalse() throws Exception {
        System.out.println("checkIfRegionExists");
        String input = "nowhereland";
        when(underTest.checkIfRegionExists(input)).thenReturn(false);
        assertEquals(false, underTest.checkIfRegionExists(input));
    }

    @Test
    public void checkResult_GivenValidInput_ReturnTrue() throws Exception {

        String input = "BALATON";
        boolean expResult = true;
        when(underTest.checkIfRegionExists(input)).thenReturn(expResult);
        boolean result = underTest.checkIfRegionExists(input);
        assertEquals(expResult, result);

    }

    @Test
    public void checkResult_GivenNullInput_ReturnFalse() throws Exception {

        String input = null;
        boolean expResult = false;
        when(underTest.checkIfRegionExists(input)).thenReturn(expResult);
        boolean result = underTest.checkIfRegionExists(input);
        assertEquals(expResult, result);

    }

}
