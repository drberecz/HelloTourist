/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.mapper;

import hu.ps.ht.dto.ReservationDTO;
import hu.ps.ht.entity.ReservationEntity;
import java.time.LocalDate;
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
public class ReservationMapperTest {
    
    public ReservationMapperTest() {
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
    public void testMapReservationEntityToDTO() throws Exception {
          
        ReservationEntity rs = new ReservationEntity(LocalDate.now(), "gazda", null);

        ReservationDTO dto = ReservationMapper.mapReservationEntityToDTO(rs);

        Assertions.assertEquals(rs.getGuideUsername(), dto.getGuideUserName());
       
    }

   
    @Test
    public void testMapReservationEntitySetDtoSet() throws Exception {

    }
    
}
