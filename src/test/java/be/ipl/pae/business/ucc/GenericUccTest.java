package be.ipl.pae.business.ucc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import be.ipl.pae.business.dtos.TestDtoMock;
import be.ipl.pae.dal.DalServicesMock;
import be.ipl.pae.dal.dao.TestDaoMock;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.TransactionErrorException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GenericUccTest {

  TestUccMock testUcc = new TestUccMock();
  TestDaoMock testDao = new TestDaoMock();
  DalServicesMock dalServices = new DalServicesMock();

  @BeforeEach
  void setUp() {
    testUcc.setDalServices(dalServices);
    testUcc.setDao(testDao);
  }

  // transaction KO

  @Test
  @DisplayName("Transaction KO")
  void testTransactionKo() {
    dalServices.setUpTransactionKo();
    testDao.setUpInsert();

    assertThrows(TransactionErrorException.class, () -> testUcc.insertDto(new TestDtoMock()));
  }

  // insertDto

  @Test
  @DisplayName("Insert Dto")
  void testInsertDtoOk()
      throws TransactionErrorException, InternalServerException, DbDataExpiredException {
    dalServices.setUpTransactionOk();
    testDao.setUpInsert();

    testUcc.insertDto(new TestDtoMock());

    assertTrue(dalServices.verify());
    assertTrue(testDao.verify());
  }

  // findDto

  @Test
  @DisplayName("Find Dto")
  void testFindDtoOk() {
    assertTrue(true);
  }

  // findAllDtos

  @Test
  @DisplayName("Find All Dtos")
  void testFindAllDtosOk() throws TransactionErrorException, InternalServerException {
    dalServices.setUpTransactionOk();
    testDao.setUpFindAll();

    testUcc.findAllDtos();

    assertTrue(dalServices.verify());
    assertTrue(testDao.verify());
  }

  // findAllDtosById

  @Test
  @DisplayName("Find All Dtos By Id")
  void testFindAllDtosByIdOk() throws TransactionErrorException, InternalServerException {
    dalServices.setUpTransactionOk();
    testDao.setUpFindAllById();

    testUcc.findAllDtosById(1, "testId");

    assertTrue(dalServices.verify());
    assertTrue(testDao.verify());
  }

  // update

  @Test
  @DisplayName("Update")
  void testUpdateOk()
      throws TransactionErrorException, DbDataExpiredException, InternalServerException {
    dalServices.setUpTransactionOk();
    testDao.setUpUpdate();

    testUcc.update(new TestDtoMock());

    assertTrue(dalServices.verify());
    assertTrue(testDao.verify());
  }

  /*
   * 
   * @Test
   * 
   * @DisplayName("Update : Optimistic Lock") void testUpdateLock() { assertTrue(true); }
   * 
   */

}
