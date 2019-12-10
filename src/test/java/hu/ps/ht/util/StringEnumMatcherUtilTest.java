package hu.ps.ht.util;


import hu.ps.ht.enumerated.Region;
import hu.ps.ht.service.RegionService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class StringEnumMatcherUtilTest {

@InjectMocks
    StringEnumMatcherUtil underTest;

    @InjectMocks
    RegionService regionService;

    @Test
    public void CheckResult_GivenInvalidInput_ReturnNull() throws Exception {

        String input = "nowheretobefound";
        assertEquals(null, underTest.matchInputWithRegionEnums(input));
    }

    @Test
    public void CheckResult_GivenValidInputWithTypo_ReturnRegion() throws Exception {

        String input = "Balat";
        assertEquals(Region.BALATON, underTest.matchInputWithRegionEnums(input));
    }
    @Test
    public void CheckResult_GivenUnidentifiableInput_ReturnDefaultBudapest() throws Exception {
        String input = "ToKxxxaj";
        assertEquals(null, underTest.matchInputWithRegionEnums(input));
    }

    

    @Test

    public void CheckResult_GivenStubInput_ReturnProperRegion() throws Exception {
        String input = "hoL";//stub HOLLOKO

        assertEquals(Region.HOLLOKO, underTest.matchInputWithRegionEnums(input));
    }

    @Test
    public void CheckMethod_generateRegionList_ReturnStringTypeListOfValues() throws Exception {

        List<String> list= Stream.of(Region.values()).map(Region::toString).collect(Collectors.toList());        
        assertEquals(list, underTest.generateRegionList());
    }
}
