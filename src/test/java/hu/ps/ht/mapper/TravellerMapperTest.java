/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.mapper;

import hu.ps.ht.dto.TravellerDTO;
import hu.ps.ht.entity.TravellerEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Csaba
 */

public class TravellerMapperTest {
    
    public TravellerMapperTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

   
    @Test
    public void testMapTravellerEntityToDTO() throws Exception {
        TravellerEntity te = new TravellerEntity("alma", "Sanyi", "Hungary", "http");
        
        TravellerDTO dto = TravellerMapper.mapTravellerEntityToDTO(te);
        
        Assertions.assertEquals(te.getUserName(), dto.getUserName());
        Assertions.assertEquals(te.getFullName(), dto.getFullName());
     
    }

   
    @Test
    public void testMapTravellerEntityListToDtoList() {
        
        TravellerEntity te = new TravellerEntity("kutyus", "kutya", "Hungary", "http");
        TravellerEntity te1 = new TravellerEntity("cicus", "cica", "Hungary", "get");
        
        
        List<TravellerEntity> tes = new ArrayList<>();
        tes.add(te);
        tes.add(te1);
        List<TravellerDTO> dto = TravellerMapper.mapTravellerEntityListToDtoList(tes);
        
        
            
    }
    
}

