package be.ipl.pae.dal;

import be.ipl.pae.business.BusinessFactory;
import be.ipl.pae.business.dto.GenericDto;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.OptimisticLockException;

import java.sql.ResultSet;
import java.util.List;

public interface GenericDao<T extends GenericDto> {

  /**
   * Execute an SQL query update.
   *
   * @param dto Dto with the new information
   * @param id id of the object containing the updated information
   */
  void update(T dto, int id)
      throws DbErrorException, OptimisticLockException, ClassCompatibilityException;

  /**
   * Finds all tuples of the table corresponding to T.
   */
  List<T> findAll() throws DbErrorException, ClassCompatibilityException;

  /**
   * Finds all tuples for the id specified by researchId.
   */
  List<T> findAllById(int id, String researchId)
      throws DbErrorException, ClassCompatibilityException;

  void delete(int id);

  /**
   * Finds a tuple based on its id.
   *
   * @param id id of the desired tuple
   */
  T findById(int id) throws DbErrorException, ClassCompatibilityException;

  /**
   * Fills the dto with the resultset content.
   *
   * @param dto dto to be filled
   * @param resultSet resultSet containing the information
   */
  void fillDto(T dto, ResultSet resultSet) throws ClassCompatibilityException;

  /**
   * Insert the dto in the data base.
   *
   * @param dto dto to be inserted
   */
  void insert(T dto) throws OptimisticLockException, ClassCompatibilityException, DbErrorException;

  /**
   * Set the BusinessFactory variable of the class.
   *
   * @param businessFactory object to be set
   */
  void setBusinessFactory(BusinessFactory businessFactory);

  /**
   * Set the DalBackendServices variable of the class.
   *
   * @param dalBackendServices object to be set
   */
  void setDalBackendServices(DalBackendServices dalBackendServices);
}
