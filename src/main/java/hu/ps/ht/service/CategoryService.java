package hu.ps.ht.service;

import hu.ps.ht.dao.CategoryDAO;
import hu.ps.ht.dto.CategoryDTO;
import hu.ps.ht.entity.CategoryEntity;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.mapper.CategoryMapper;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Local
@Slf4j
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CategoryService {

    @Inject
    CategoryDAO categoryDAO;

    public CategoryService() {
    }

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<CategoryDTO> findAll() {

        List<CategoryEntity> categoryEntityList = categoryDAO.findAll(CategoryEntity.class);

        return CategoryMapper.mapCategoryEntityToDtoToList(categoryEntityList);

    }

    public List<CategoryDTO> findById(Long upperLimit) {

        List<CategoryEntity> categoryEntityList = categoryDAO.findById(upperLimit);
        return CategoryMapper.mapCategoryEntityToDtoToList(categoryEntityList);

    }

    public void createNewCategory(CategoryDTO categoryDTO) {

        CategoryEntity categoryEntity = null;

        try {
            categoryEntity = CategoryMapper.mapCategoryDTOToEntity(categoryDTO);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
            log.error("CREATE Category failed on MAPPING!");
        }

        categoryDAO.createEntity(categoryEntity);

    }

    public OutcomeMessage deleteCategoryById(Long id) {
        try {
            categoryDAO.delete(CategoryEntity.class, id);
            return OutcomeMessage.success;
        } catch (EJBException ex) {
            log.error("DELETE failed!" + ex.getMessage());

        }
        return OutcomeMessage.error;

    }

}
