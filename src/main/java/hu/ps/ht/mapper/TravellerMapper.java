package hu.ps.ht.mapper;

import hu.ps.ht.dto.TravellerDTO;
import hu.ps.ht.entity.TravellerEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

public class TravellerMapper {

    public static TravellerDTO mapTravellerEntityToDTO(TravellerEntity travellerEntity) throws IllegalAccessException, InvocationTargetException {

        TravellerDTO travellerDTO = new TravellerDTO();
        BeanUtils.copyProperties(travellerDTO, travellerEntity);
        travellerDTO.setterImageLink(travellerEntity.getImageLink());

        return travellerDTO;
    }

    public static List<TravellerDTO> mapTravellerEntityListToDtoList(List<TravellerEntity> entityList) {

        List<TravellerDTO> dtoList = new ArrayList<>();

        for (TravellerEntity travellerEntity : entityList) {
            try {
                dtoList.add(mapTravellerEntityToDTO(travellerEntity));
            } catch (IllegalAccessException | InvocationTargetException ex) {
                Logger.getLogger(TravellerMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dtoList;
    }

}
