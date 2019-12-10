package hu.ps.ht.mapper;

import hu.ps.ht.dto.CategoryDTO;
import hu.ps.ht.entity.CategoryEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;

public class CategoryMapper {

    public static CategoryDTO mapCategoryEntityToDTO(CategoryEntity categoryEntity) {

        return new CategoryDTO(categoryEntity.getId(), categoryEntity.getName());
    }

    public static CategoryEntity mapCategoryDTOToEntity(CategoryDTO catDto) throws IllegalAccessException, InvocationTargetException {

        CategoryEntity entity = new CategoryEntity();
        BeanUtils.copyProperties(entity, catDto);
        return entity;
    }

    public static List<CategoryDTO> mapCategoryEntityToDtoToList(List<CategoryEntity> entityList) {

        List<CategoryDTO> dtoList = new ArrayList<>();
        
        
        for (CategoryEntity categoryEntity : entityList) {
            dtoList.add(mapCategoryEntityToDTO(categoryEntity));
        }

        return dtoList;
    }
}
