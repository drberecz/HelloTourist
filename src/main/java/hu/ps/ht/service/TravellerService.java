package hu.ps.ht.service;

import hu.ps.ht.dao.TravellerDAO;
import hu.ps.ht.dto.TravellerDTO;
import hu.ps.ht.entity.TravellerEntity;
import hu.ps.ht.mapper.TravellerMapper;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Local
public class TravellerService {

    @Inject
    TravellerDAO travellerDAO;

    public TravellerService(TravellerDAO travellerDAO) {
        this.travellerDAO = travellerDAO;
    }

    public List<TravellerDTO> findAll() {

        List<TravellerEntity> travellerEntityList = travellerDAO.findAll(TravellerEntity.class);

        return TravellerMapper.mapTravellerEntityListToDtoList(travellerEntityList);
    }

    public List<TravellerDTO> getAllBookingsByTravellerName() {

        List<TravellerEntity> travellerEntityList = travellerDAO.findAll(TravellerEntity.class);

        return TravellerMapper.mapTravellerEntityListToDtoList(travellerEntityList);
    }

    public TravellerDTO getTravellerByUsername(String username) {

        TravellerEntity entity = travellerDAO.getTravellerByUsername(username);
        TravellerDTO dTO = null;
        try {
            dTO = TravellerMapper.mapTravellerEntityToDTO(entity);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(TravellerService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dTO;
    }

    public Map<String, Integer> sumCountry() {
        Map<String, Integer> result = new HashMap<>();
        List<TravellerEntity> travellerEntityList = travellerDAO.findAll(TravellerEntity.class);

        for (TravellerEntity travellerEntity : travellerEntityList) {
            String country = travellerEntity.getCountry();
            if (!result.containsKey(travellerEntity.getCountry())) {
                result.put(country, 1);
            } else {
                result.put(country, result.get(travellerEntity.getCountry()) + 1);
            }
        }
        return result;

    }

}
