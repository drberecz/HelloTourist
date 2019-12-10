package hu.ps.ht.mapper;

import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.entity.EventEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;


public class EventMapper {

    public static EventDTO mapEventEntityToDto(EventEntity eventEntity) throws IllegalAccessException, InvocationTargetException {

        EventDTO eventDTO = new EventDTO();
        BeanUtils.copyProperties(eventDTO, eventEntity);

        eventDTO.setGuideUserName(eventEntity.getGuide().getUserName());

        return eventDTO;
    }

    public static List<EventDTO> mapEventEntityListToDtoList(List<EventEntity> entityList) {

        List<EventDTO> eventDtoList = new ArrayList<>();

        try {
            for (EventEntity eventEntity : entityList) {
                eventDtoList.add(mapEventEntityToDto(eventEntity));
            }
        } catch (Exception e) {
            
        }

        return eventDtoList;
    }

}
