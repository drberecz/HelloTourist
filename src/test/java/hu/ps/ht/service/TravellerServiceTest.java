package hu.ps.ht.service;

import hu.ps.ht.dao.TravellerDAO;
import hu.ps.ht.dto.TravellerDTO;
import hu.ps.ht.entity.TravellerEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TravellerServiceTest {

    
    private static final String COUNTRY = "HU";
    private static final Long ID = 1L;
    private static final String COUNTRY_OTHER = "AU";
    private static final String TRAVELER_NAME = "Hikari";
    private static final String HYPERLINK = "./something.hu";

    private TravellerService underTests;

    @Mock
    TravellerServiceTest underTest;

    @Mock
    TravellerDAO travellerDAO;

    @Mock
    TravellerEntity travellerEnity;

  
    @Test
    public void testSumCountry_Returns_Map_Success() throws Exception {
        underTests = new TravellerService(travellerDAO);
        List<TravellerEntity> list = new ArrayList<>();
        TravellerEntity travellerEntity1 = new TravellerEntity();
        TravellerEntity travellerEntity2 = new TravellerEntity();
        TravellerEntity travellerEntity3 = new TravellerEntity();
        travellerEntity1.setCountry(COUNTRY);
        travellerEntity2.setCountry(COUNTRY);
        travellerEntity3.setCountry(COUNTRY_OTHER);
        list.add(travellerEntity1);
        list.add(travellerEntity2);
        list.add(travellerEntity3);

        when(travellerDAO.findAll(TravellerEntity.class)).thenReturn(list);
        assertEquals(2, (underTests.sumCountry()).size());
    }

    @Test
    public void testFindAll_ReturnsListSuccess() throws Exception {

        underTests = new TravellerService(travellerDAO);

        List<TravellerEntity> allTravellersListEntity = new ArrayList<>();
        TravellerEntity entity3 = new TravellerEntity();
        TravellerEntity entity4 = new TravellerEntity();

        allTravellersListEntity.add(entity3);
        allTravellersListEntity.add(entity4);

        when(travellerDAO.findAll(TravellerEntity.class)).thenReturn(allTravellersListEntity);
        assertEquals(2, (underTests.findAll()).size());

    }

    @Test
    public void testGetTravellerByUsername_whenUsernameMatches_ReturnTravellerDTO() throws Exception {
        underTests = new TravellerService(travellerDAO);
        TravellerDTO dto = new TravellerDTO();
        dto.setUserName(TRAVELER_NAME);
        dto.setCountry(COUNTRY);
        dto.setId(ID);
        dto.setImageLink(HYPERLINK);
        TravellerEntity tr = new TravellerEntity();
        tr.setUserName(TRAVELER_NAME);

        when(travellerDAO.getTravellerByUsername(TRAVELER_NAME)).thenReturn(tr);
        assertEquals(TRAVELER_NAME, underTests.getTravellerByUsername(TRAVELER_NAME).getUserName());

    }
}
