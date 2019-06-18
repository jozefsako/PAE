package be.ipl.pae.business.ucc;

import be.ipl.pae.business.dto.GenericDto;
import be.ipl.pae.dal.DalServices;
import be.ipl.pae.dal.GenericDao;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.IdNotFoundException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.OptimisticLockException;
import be.ipl.pae.exceptions.TransactionErrorException;
import be.ipl.pae.util.MyLogger;

import java.util.List;
import java.util.logging.Level;

public abstract class GenericUccImpl<T extends GenericDao, R extends GenericDto>
    implements GenericUcc<T, R> {

  protected T dao;
  protected DalServices dalServices;

  @Override
  public void setDao(T dao) {
    this.dao = dao;
  }

  @Override
  public void setDalServices(DalServices dalServices) {
    this.dalServices = dalServices;
  }

  @Override
  public void insertDto(R dto)
      throws TransactionErrorException, InternalServerException, DbDataExpiredException {
    try {
      dalServices.startTransaction();
      dao.insert(dto);
      dalServices.commitTransaction();

    } catch (DbErrorException err1) {
      try {
        dalServices.rollbackTransaction();
      } catch (DbErrorException err11) {
        throw new TransactionErrorException(err11.getMessage());
      }
      throw new TransactionErrorException(err1.getMessage());
    } catch (OptimisticLockException err2) {
      MyLogger.getLogger().log(Level.INFO, err2.toString(), err2);
      throw new DbDataExpiredException(err2.getMessage());
    } catch (ClassCompatibilityException err3) {
      throw new InternalServerException(err3.getMessage());
    }
  }



  @Override
  public R findDto(int id)
      throws TransactionErrorException, InternalServerException, IdNotFoundException {
    R userDto;
    try {
      dalServices.startTransaction();
      userDto = (R) dao.findById(id);
      if (userDto == null) {
        MyLogger.getLogger().log(Level.INFO,
            "id " + id + " not found in dao " + dao.getClass().getSimpleName(),
            new IdNotFoundException());
        throw new IdNotFoundException();
      }
      dalServices.commitTransaction();
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
    return userDto;
  }

  @Override
  public List<R> findAllDtos() throws TransactionErrorException, InternalServerException {
    List dtos;
    try {
      dalServices.startTransaction();
      dtos = dao.findAll();
      dalServices.commitTransaction();
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
    return dtos;
  }

  @Override
  public List<R> findAllDtosById(int id, String researchId)
      throws TransactionErrorException, InternalServerException {
    List dtos;
    try {
      dalServices.startTransaction();
      dtos = dao.findAllById(id, researchId);
      dalServices.commitTransaction();
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
    return dtos;
  }

  @Override
  public void update(R dto)
      throws TransactionErrorException, DbDataExpiredException, InternalServerException {
    try {
      dalServices.startTransaction();
      dao.update(dto, dto.getId());
      dalServices.commitTransaction();
    } catch (DbErrorException err) {
      try {
        dalServices.rollbackTransaction();
      } catch (DbErrorException err2) {
        throw new TransactionErrorException(err2.getMessage());
      }
      throw new TransactionErrorException(err.getMessage());
    } catch (OptimisticLockException err) {
      MyLogger.getLogger().log(Level.INFO, err.toString(), err);
      throw new DbDataExpiredException(err.getMessage());
    } catch (ClassCompatibilityException err) {
      throw new InternalServerException(err.getMessage());
    }
  }
}
