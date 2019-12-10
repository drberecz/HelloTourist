package hu.ps.ht.service;

import hu.ps.ht.dao.GuideDAO;
import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.entity.GuideEntity;
import hu.ps.ht.enumerated.BookingStatus;
import hu.ps.ht.enumerated.Region;
import hu.ps.ht.mapper.GuideMapper;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Stateless
@Slf4j
public class GuideService {

    @Inject
    GuideDAO guideDAO;

    public GuideService(GuideDAO guideDAO) {
        this.guideDAO = guideDAO;
    }

    public void evaluateGuide(GuideDTO inputGuideDTO, Integer evaluatedScore) {

        GuideEntity guideEntityRetrieved = null;
        GuideDTO guideDTO = null;

        inputGuideDTO.getId();

        try {
            guideEntityRetrieved = guideDAO.find(GuideEntity.class, inputGuideDTO.getId());
        } catch (Exception ex) {
            log.error("couldnt find record to EVALUATE: " + ex.getLocalizedMessage());
        }
        guideEntityRetrieved.setScoreSum(inputGuideDTO.getScoreSum() + evaluatedScore);
        guideEntityRetrieved.setNumOfReviews(inputGuideDTO.getNumOfReviews() + 1);
        guideEntityRetrieved.setAverageScore(calcaulateAverage(guideEntityRetrieved));

        try {
            guideDAO.updateEntity(guideEntityRetrieved);
        } catch (Exception ex) {
            log.error("Store EVALUATION FAILED at evaluateGuide! " + ex);
        }

    }

    public double calcaulateAverage(GuideEntity guideEntityRetrieved) {
        return Math.floor((guideEntityRetrieved.getScoreSum() / (double) guideEntityRetrieved.getNumOfReviews()) * 1e2) / 1e2;
    }

    public List<GuideDTO> findAll() {

        List<GuideEntity> guideEntityList = guideDAO.findAll(GuideEntity.class);
        return GuideMapper.mapGuideEntityListToDtoList(guideEntityList);
    }

    public List<GuideDTO> getGuideDtoListByRegion(Region region) {

        List<GuideEntity> entityList = guideDAO.getGuideListByRegion(region);

        List<GuideDTO> dtoList = GuideMapper.mapGuideEntityListToDtoList(entityList);
        return dtoList;

    }

    public GuideDTO getGuideByUsername(String username) {

        GuideDTO guideDTO = new GuideDTO();
        GuideEntity entity = guideDAO.getGuideByUserName(username);
        try {
            guideDTO = GuideMapper.mapGuideEntityToDTO(entity);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(GuideService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return guideDTO;

    }

    public boolean guideCanConfirm(boolean isGuide, BookingStatus status, LocalDate tourDate) {

        return (isGuide && status == BookingStatus.AWAITING_CONFIRMATION && !tourDate.isBefore(LocalDate.now()));

    }

    public boolean guideCanComplete(boolean isGuide, BookingStatus bookingStatus, LocalDate tourDate) {
        return (isGuide
                && tourDate.equals(LocalDate.now())
                && bookingStatus == BookingStatus.CONFIRMED);
    }

}
