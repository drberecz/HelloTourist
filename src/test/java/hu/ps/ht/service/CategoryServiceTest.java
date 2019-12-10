package hu.ps.ht.service;

import hu.ps.ht.dao.CategoryDAO;
import hu.ps.ht.dao.EventDAO;
import hu.ps.ht.dao.GuideDAO;
import hu.ps.ht.dto.CategoryDTO;
import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.entity.CategoryEntity;
import hu.ps.ht.entity.EventEntity;
import hu.ps.ht.entity.GuideEntity;
import hu.ps.ht.entity.ReservationEntity;
import hu.ps.ht.enumerated.Region;
import hu.ps.ht.enumerated.WeeklyPattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
   
    @Mock
    private CategoryDAO categoryDAO;
    
    @Mock
    private CategoryEntity ce;

    @Mock
    private CategoryDTO cd;

    @InjectMocks
    private CategoryService underTest;
    
      @Test
      public void testfindById() throws Exception {
       underTest = new CategoryService(categoryDAO);
       CategoryDTO dto = new CategoryDTO(1L, "World");
       List<CategoryEntity> entitylist = new ArrayList<>();
       CategoryEntity ce = new CategoryEntity("World");
       ce.setId(1L);
       entitylist.add(ce);
       when(categoryDAO.findById(1L)).thenReturn(entitylist);
       assertEquals(1, (underTest.findById(1L)).size());
   }
      
      
      @Test
    public void testCreateNewCategory_callCreate() throws Exception {
        underTest = new CategoryService(categoryDAO);
        underTest.createNewCategory(cd);
        verify(categoryDAO).createEntity(ArgumentMatchers.any());

    }
     @Test
    public void testDeleteCategory_calldelete() throws Exception {
        underTest = new CategoryService(categoryDAO);
        underTest.deleteCategoryById(1L);
        verify(categoryDAO).delete(CategoryEntity.class, 1L);

    }
}
      
     
