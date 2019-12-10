package hu.ps.ht.service;

import hu.ps.ht.dao.GuideDAO;
import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.entity.GuideEntity;
import hu.ps.ht.enumerated.BookingStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GuideServiceTest {

    private static final String GUIDE_NAME = "Borisz";
    private static final String GUIDE_NAME2 = "Antal";
    private static final Long INITIAL_SCORE_SUM = 2L;
    private static final Long AWAITED_SCORE_SUM = 3L;
    private static final Integer INITIAL_NUM_OF_REVIEWS = 1;
    private static final Integer AWAITED_NUM_OF_REVIEWS = 2;
    private static final double INITIAL_AVG_SCORE = 2.0d;
    private static final double AWAITED_AVG_SCORE = 1.50D;
    private static final Integer SCORE_OF_EVALUATION = 1;
    
   
    private static final Long ID = 1L;

    public GuideServiceTest() {
    }
    private GuideService underTests;

    @Mock
    GuideDAO guideDAO;

    @Test
    public void testEvaluateGuide() throws Exception {
        underTests = new GuideService(guideDAO);
        GuideEntity guideEntity = new GuideEntity();
        guideEntity.setUserName(GUIDE_NAME);
        guideEntity.setId(ID);
        guideEntity.setScoreSum(INITIAL_SCORE_SUM);
        guideEntity.setNumOfReviews(INITIAL_NUM_OF_REVIEWS);
        guideEntity.setAverageScore(INITIAL_AVG_SCORE);

        GuideDTO dto = new GuideDTO();
        dto.setUserName(GUIDE_NAME);
        dto.setId(ID);
        dto.setScoreSum(INITIAL_SCORE_SUM);
        dto.setNumOfReviews(INITIAL_NUM_OF_REVIEWS);
        dto.setAverageScore(INITIAL_AVG_SCORE);

        when(guideDAO.find(GuideEntity.class, dto.getId())).thenReturn(guideEntity);
        underTests.evaluateGuide(dto, SCORE_OF_EVALUATION);
        assertEquals(AWAITED_AVG_SCORE, guideEntity.getAverageScore());
        assertEquals(AWAITED_SCORE_SUM, guideEntity.getScoreSum());
        assertEquals(AWAITED_NUM_OF_REVIEWS, guideEntity.getNumOfReviews());

    }

    @Test
    public void testGetGuideByUsername_Success() throws Exception {
        underTests = new GuideService(guideDAO);
        GuideDTO guide = new GuideDTO();
        GuideDTO guideOther = new GuideDTO();
        List<GuideDTO> mockList = new ArrayList<>();
        guide.setUserName(GUIDE_NAME);
        guideOther.setUserName(GUIDE_NAME2);
        mockList.add(guide);
        mockList.add(guideOther);
        GuideEntity guideEntity = new GuideEntity();
        guideEntity.setUserName(GUIDE_NAME);

        when(guideDAO.getGuideByUserName(GUIDE_NAME)).thenReturn(guideEntity);
        assertEquals(GUIDE_NAME, (underTests.getGuideByUsername(GUIDE_NAME).getUserName()));

    }

    @Test
    public void testGuideCanConfirm() throws Exception {
        underTests = new GuideService(guideDAO);
        assertEquals(false, underTests.guideCanConfirm(false, BookingStatus.AWAITING_CONFIRMATION, LocalDate.now().plusDays(10)));
        assertEquals(false, underTests.guideCanConfirm(true, BookingStatus.COMPLETED, LocalDate.now().plusDays(10)));
        assertEquals(false, underTests.guideCanConfirm(true, BookingStatus.EVALUATED, LocalDate.now().plusDays(10)));
    }

    @Test
    public void testGuideCanComplete_InputAwaitingAndGuide_InputAwaitingNonGuide_InputGuideAndCompleted_InputGuideAndEval_ReturnsFFFF() throws Exception {
        underTests = new GuideService(guideDAO);
        assertEquals(false, underTests.guideCanComplete(true, BookingStatus.AWAITING_CONFIRMATION, LocalDate.now().plusDays(10)));
        assertEquals(false, underTests.guideCanComplete(false, BookingStatus.AWAITING_CONFIRMATION, LocalDate.now().plusDays(10)));
        assertEquals(false, underTests.guideCanComplete(true, BookingStatus.COMPLETED, LocalDate.now().plusDays(10)));
        assertEquals(false, underTests.guideCanComplete(true, BookingStatus.EVALUATED, LocalDate.now().plusDays(10)));
    }
}
