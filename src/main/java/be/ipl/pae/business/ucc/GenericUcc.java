package be.ipl.pae.business.ucc;

import be.ipl.pae.business.dto.GenericDto;
import be.ipl.pae.dal.DalServices;
import be.ipl.pae.dal.GenericDao;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.IdNotFoundException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.TransactionErrorException;

import java.util.List;

public interface GenericUcc<T extends GenericDao, R extends GenericDto> {

  /**
   * set the dao.
   */
  void setDao(T dao);

  /**
   * Set or update a DalServices.
   *
   * @param dalServices The dalServices to be set
   */
  void setDalServices(DalServices dalServices);

  /**
   * @return R the dto found with this id.
   */
  R findDto(int id) throws TransactionErrorException, InternalServerException, IdNotFoundException;

  /**
   * @param dto the dto to insert in db.
   */
  void insertDto(R dto)
      throws TransactionErrorException, InternalServerException, DbDataExpiredException;

  /**
   * @return A list of all the dtos.
   */
  List<R> findAllDtos() throws TransactionErrorException, InternalServerException;

  /**
   * @return A list of all the dtos.
   */
  List<R> findAllDtosById(int id, String researchId)
      throws TransactionErrorException, InternalServerException;

  void update(R dto)
      throws TransactionErrorException, DbDataExpiredException, InternalServerException;
}
