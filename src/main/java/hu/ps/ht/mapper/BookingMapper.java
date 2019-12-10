package hu.ps.ht.mapper;

import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.entity.BookingEntity;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

public class BookingMapper {

    public static BookingDTO mapBookingEntityToDTO(BookingEntity bookingEntity) throws IllegalAccessException, InvocationTargetException {

        BookingDTO bookingDTO = new BookingDTO();
        BeanUtils.copyProperties(bookingDTO, bookingEntity);
        return bookingDTO;
    }

    public static BookingEntity mapBookingDTOToEntity(BookingDTO bdto) throws IllegalAccessException, InvocationTargetException {

        BookingEntity entity = new BookingEntity();
        BeanUtils.copyProperties(entity, bdto);
        return entity;
    }

    public static BookingEntity mapBookingDtoToEntity(BookingDTO bookingDTO) throws IllegalAccessException, InvocationTargetException {

        BookingEntity bookingEntity = new BookingEntity();
        BeanUtils.copyProperties(bookingEntity, bookingDTO);

        return bookingEntity;
    }

    public static List<BookingDTO> mapBookingEntityListToDtoList(List<BookingEntity> bookingEntityList) {
        List<BookingDTO> dtoList = new ArrayList<>();

        for (BookingEntity bookingEntity : bookingEntityList) {
            try {
                dtoList.add(mapBookingEntityToDTO(bookingEntity));
            } catch (InvocationTargetException | IllegalAccessException ex) {
                Logger.getLogger("bookingmappper*******************\n"
                        + BookingMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dtoList;
    }

}
