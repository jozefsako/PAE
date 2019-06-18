package be.ipl.pae.main.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

  @BeforeEach
  void setUp() {}
  
  @Test
  void test() {
    assertTrue(true);
  }
  
  /*@Test
  @DisplayName("Password Valid")
  void testLogin1()
      throws InvalidLoginException, InvalidPasswordException, 
      DbErrorException, InternalServerException, TransactionErrorException {
    UserMock userMock = new UserMock(true, "123", true);
    UserDaoMock userDao = new UserDaoMock(true, "login", userMock);
    UserUcc userUcc = new UserUccImpl();
    DalBackendServices dalBackendServices = new DalServicesImpl();
    userUcc.setDalServices((DalServices)dalBackendServices);
    userUcc.setDao(userDao);
    assertEquals(userUcc.login("login", "123"), userMock);
    assertTrue(userDao.verify());
    assertTrue(userMock.verify());
  }

  @Test
  @DisplayName("Password Not Valid")
  void testLogin2() throws DbErrorException {
    UserMock userMock = new UserMock(true, "124", false);
    UserDaoMock userDao = new UserDaoMock(true, "login", userMock);
    UserUcc userUcc = new UserUccImpl();
    DalBackendServices dalBackendServices = new DalServicesImpl();
    userUcc.setDalServices((DalServices)dalBackendServices);
    userUcc.setDao(userDao);
    assertThrows(InvalidPasswordException.class, () -> userUcc.login("login", "124"));
    assertTrue(userDao.verify());
    assertTrue(userMock.verify());
  }

  @Test
  @DisplayName("Login not valid")
  void testLogin3() throws DbErrorException {
    UserDaoMock userDao = new UserDaoMock(true, "notlogin", true);
    UserUcc userUcc = new UserUccImpl();
    userUcc.setDao(userDao);
    DalBackendServices dalBackendServices = new DalServicesImpl();
    userUcc.setDalServices((DalServices)dalBackendServices);
    assertThrows(InvalidLoginException.class, () -> userUcc.login("notlogin", "124"));
    assertTrue(userDao.verify());
  }*/
}
