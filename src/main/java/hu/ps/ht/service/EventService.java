package hu.ps.ht.service;

import hu.ps.ht.dao.EventDAO;
import hu.ps.ht.dao.GuideDAO;
import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.entity.EventEntity;
import hu.ps.ht.enumerated.Region;
import hu.ps.ht.mapper.EventMapper;
import hu.ps.ht.util.GeolocationCache;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class EventService {

    @Inject
    EventDAO eventDAO;

    @Inject
    GuideDAO guideDAO;

    @Inject
    RegionService regionService;

    public EventDTO getEventDtoById(Long id) {

        try {
            EventEntity eventEntity = eventDAO.find(EventEntity.class, id);
            EventDTO eventDTO = EventMapper.mapEventEntityToDto(eventEntity);
            return eventDTO;
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<EventDTO> getEventDtoListByRegion(String input) {

        if (regionService.checkIfRegionExists(input)) {

            List<EventEntity> eventEntityList = eventDAO.getEventListByRegion(Region.valueOf(input));
            List<EventDTO> eventDtoList = EventMapper.mapEventEntityListToDtoList(eventEntityList);
            log.debug("EventDTO size: " + eventDtoList.size());

            return eventDtoList;

        } else {
            return null;
        }

    }

    public List<EventDTO> getEventDtoListByGuide(String guidename) {

        List<EventEntity> eventEntitys = eventDAO.getEventEntityListByGuide(guidename);

        return EventMapper.mapEventEntityListToDtoList(eventEntitys);

    }

    public void createNewEventFromDTO(EventDTO eventDTO) {

        EventEntity eventEntity = EventEntity.builder()
                .category(eventDTO.getCategory())
                .cost(eventDTO.getCost())
                .dayOfWeek(eventDTO.getDayOfWeek())
                .description(eventDTO.getDescription())
                .guide(guideDAO.getGuideByUserName(eventDTO.getGuideUserName()))
                .maxParticipants(eventDTO.getMaxParticipants())
                .region(eventDTO.getRegion())
                .xGeoLatitude(eventDTO.getXGeoLatitude())
                .yGeoLongitude(eventDTO.getYGeoLongitude())
                .build();
        eventDAO.createEntity(eventEntity);

    }

    public Double[] getEventGeolocation(EventDTO eventDTO) {

        Double xLat = eventDTO.getXGeoLatitude();
        Double yLon = eventDTO.getYGeoLongitude();

        if (xLat == null || yLon == null) {
            return GeolocationCache.getGeoPointArrayByRegion(eventDTO.getRegion());
        }

        return new Double[]{xLat, yLon};
    }

    public List<EventDTO> findAllEvents() {

        List<EventEntity> eventEntitys = eventDAO.getAllEvents();

        return EventMapper.mapEventEntityListToDtoList(eventEntitys);
    }

    public List<EventDTO> findEventsByCategory(String category) {

        List<EventEntity> eventEntitys = eventDAO.findEventsByCategory(category);
        return EventMapper.mapEventEntityListToDtoList(eventEntitys);
    }

}
