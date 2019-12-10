package hu.ps.ht.mapper;

import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.entity.GuideEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

public class GuideMapper {

    public static GuideDTO mapGuideEntityToDTO(GuideEntity guideEntity) throws IllegalAccessException, InvocationTargetException {
        GuideDTO guideDTO = new GuideDTO();
        BeanUtils.copyProperties(guideDTO, guideEntity);

        return guideDTO;
    }

    public static List<GuideDTO> mapGuideEntityListToDtoList(List<GuideEntity> entityList) {

        List<GuideDTO> dtoList = new ArrayList<>();

        for (GuideEntity entity : entityList) {

            try {
                dtoList.add(mapGuideEntityToDTO(entity));
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        return dtoList;
    }

}
