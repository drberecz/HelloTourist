package hu.ps.ht.dao;

import hu.ps.ht.entity.ReservationEntity;
import javax.ejb.Local;
import javax.ejb.Singleton;

@Singleton
@Local
public class ReservationDAO extends BaseDAO<ReservationEntity> {

}
