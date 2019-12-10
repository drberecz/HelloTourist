package hu.ps.ht.mapper;

import hu.ps.ht.dto.ReservationDTO;
import hu.ps.ht.entity.ReservationEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;

public class ReservationMapper {

    public static ReservationDTO mapReservationEntityToDTO(ReservationEntity reservationEntity) throws IllegalAccessException, InvocationTargetException {

        ReservationDTO reservationDTO = new ReservationDTO();
        BeanUtils.copyProperties(reservationDTO, reservationEntity);
        reservationDTO.setGuideUserName("gazda");

        return reservationDTO;
    }

    public static Set<ReservationDTO> mapReservationEntitySetDtoSet(Set<ReservationEntity> entitySet) throws IllegalAccessException, InvocationTargetException {
        Set<ReservationDTO> dtoSet = new HashSet<>();
        for (ReservationEntity reservationEntity : entitySet) {
            dtoSet.add(mapReservationEntityToDTO(reservationEntity));
        }

        return dtoSet;
    }
}
