package be.ipl.pae.business.ucc.mobilities.mobility;

import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.business.ucc.GenericUccImpl;
import be.ipl.pae.dal.mobility.MobilityDao;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.OptimisticLockException;
import be.ipl.pae.exceptions.TransactionErrorException;
import be.ipl.pae.util.MyLogger;

import java.util.List;
import java.util.logging.Level;

public class MobilityUccImpl extends GenericUccImpl<MobilityDao, MobilityDto>
    implements MobilityUcc {

  @Override
  public void confirmMobility(MobilityDto mobilityDto)
      throws TransactionErrorException, InternalServerException, DbDataExpiredException {
    try {
      dalServices.startTransaction();
      dao.insert(mobilityDto);
      dalServices.commitTransaction();
    } catch (OptimisticLockException err) {

      MyLogger.getLogger().log(Level.INFO, err.toString(), err);
      throw new DbDataExpiredException(err.getMessage());
    } catch (DbErrorException err) {
      try {
        dalServices.rollbackTransaction();
      } catch (DbErrorException err2) {
        throw new TransactionErrorException(err2.getMessage());
      }
      throw new TransactionErrorException(err.getMessage());
    } catch (ClassCompatibilityException err) {
      throw new InternalServerException(err.getMessage());
    }
  }

  @Override
  public List<MobilityDto> findAllMobilitiesByUser(Integer id)
      throws InternalServerException, TransactionErrorException {
    List<MobilityDto> list = null;
    try {
      dalServices.startTransaction();
      list = this.dao.findAllMobilitiesByUser(id);
      dalServices.commitTransaction();
    } catch (DbErrorException err) {
      try {
        dalServices.rollbackTransaction();
      } catch (DbErrorException err2) {
        throw new TransactionErrorException(err2.getMessage());
      }
    }
    return list;
  }

  @Override
  public List<MobilityDto> researchMobility(String yearCriteria, String stateCriteria)
      throws InternalServerException, TransactionErrorException {
    List<MobilityDto> list = null;
    try {
      dalServices.startTransaction();
      list = this.dao.researchMobility(yearCriteria, stateCriteria);
      dalServices.commitTransaction();
    } catch (DbErrorException err) {
      try {
        dalServices.rollbackTransaction();
      } catch (DbErrorException err2) {
        throw new TransactionErrorException(err2.getMessage());
      }
    }
    return list;
  }

}
