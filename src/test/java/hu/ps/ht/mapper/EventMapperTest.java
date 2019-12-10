package hu.ps.ht.mapper;

import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.entity.CategoryEntity;
import hu.ps.ht.entity.EventEntity;
import hu.ps.ht.entity.GuideEntity;
import hu.ps.ht.enumerated.Region;
import hu.ps.ht.enumerated.WeeklyPattern;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Barnabás
 */
@ExtendWith(MockitoExtension.class)
public class EventMapperTest {

    public EventMapperTest() {
    }

@Test
    public void testMapEventEntityToDto() throws Exception {


        EventEntity eventEntity = EventEntity.builder()
                .category(new CategoryEntity("adv"))
                .cost(1522L)
                .dayOfWeek(WeeklyPattern.ALWAYS_VACANT)
                .description("terrific tour")
                .guide(new GuideEntity("BEla", null, Region.BALATON, null, Integer.MIN_VALUE, Long.MIN_VALUE, Double.MAX_VALUE, "a", "b", "c"))
                .maxParticipants(10L)
                .region(Region.BALATON)
                .xGeoLatitude(47.01D)
                .yGeoLongitude(37.12D)
                .build();
        EventDTO eventDTO = null;
     eventDTO =   EventMapper.mapEventEntityToDto(eventEntity);
        assertEquals("BEla", eventDTO.getGuideUserName());
        assertEquals(1522L, (Object)eventDTO.getCost());
        assertEquals("terrific tour", (Object)eventDTO.getDescription());
        assertEquals(10L, (Object)eventDTO.getMaxParticipants());
        assertEquals("BALATON", (Object)String.valueOf(eventDTO.getRegion()));
        assertEquals(47.01D, (Object)eventDTO.getXGeoLatitude());
        assertEquals(37.12D, (Object)eventDTO.getYGeoLongitude());
        assertEquals(new CategoryEntity("adv"), (Object)eventDTO.getCategory());
        assertEquals(WeeklyPattern.ALWAYS_VACANT, (Object)eventDTO.getDayOfWeek());
        
        
        

    }

}
