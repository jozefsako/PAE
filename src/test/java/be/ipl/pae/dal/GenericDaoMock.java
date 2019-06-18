package be.ipl.pae.dal;

import be.ipl.pae.business.BusinessFactory;
import be.ipl.pae.business.dto.GenericDto;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.OptimisticLockException;

import java.sql.ResultSet;
import java.util.List;

public abstract class GenericDaoMock<T extends GenericDto> implements GenericDao<T> {

  private boolean updateExpected;
  private boolean updateCalled;
  private boolean findAllExpected;
  private boolean findAllCalled;
  private boolean findAllByIdExpected;
  private boolean findAllByIdCalled;
  private boolean deleteExpected;
  private boolean deleteCalled;
  private boolean findByIdExpected;
  private boolean findByIdCalled;
  private boolean fillDtoExpected;
  private boolean fillDtoCalled;
  private boolean insertExpected;
  private boolean insertCalled;

  @Override
  public void update(T dto, int id)
      throws DbErrorException, OptimisticLockException, ClassCompatibilityException {
    updateCalled = true;
  }

  @Override
  public List<T> findAll() throws DbErrorException, ClassCompatibilityException {
    findAllCalled = true;
    return null;
  }

  @Override
  public List<T> findAllById(int id, String researchId)
      throws DbErrorException, ClassCompatibilityException {
    findAllByIdCalled = true;
    return null;
  }

  @Override
  public void delete(int id) {
    deleteCalled = true;
  }

  @Override
  public T findById(int id) throws DbErrorException, ClassCompatibilityException {
    findByIdCalled = true;
    return null;
  }

  @Override
  public void fillDto(T dto, ResultSet resultSet) throws ClassCompatibilityException {
    fillDtoCalled = true;
  }

  @Override
  public void insert(T dto)
      throws OptimisticLockException, ClassCompatibilityException, DbErrorException {
    insertCalled = true;
  }

  @Override
  public void setBusinessFactory(BusinessFactory businessFactory) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setDalBackendServices(DalBackendServices dalBackendServices) {
    // TODO Auto-generated method stub

  }

  public void setUpInsert() {
    insertExpected = true;
  }

  public void setUpFindAll() {
    findAllExpected = true;
  }

  public void setUpFindAllById() {
    findAllByIdExpected = true;
  }

  public void setUpUpdate() {
    updateExpected = true;
  }

  /**
   * Test if all the methods have been tested.
   * 
   * @return true if all the methods have been test, false if it's not the case
   */
  public boolean verify() {
    boolean verify = true;

    if (updateExpected != updateCalled || findAllExpected != findAllCalled
        || findAllByIdExpected != findAllByIdCalled || deleteExpected != deleteCalled
        || findByIdExpected != findByIdCalled || fillDtoExpected != fillDtoCalled
        || insertExpected != insertCalled) {
      verify = false;
    }


    updateExpected = false;
    updateCalled = false;
    findAllExpected = false;
    findAllCalled = false;
    findAllByIdExpected = false;
    findAllByIdCalled = false;
    deleteExpected = false;
    deleteCalled = false;
    findByIdExpected = false;
    findByIdCalled = false;
    fillDtoExpected = false;
    fillDtoCalled = false;
    insertExpected = false;
    insertCalled = false;

    return verify;
  }

}
