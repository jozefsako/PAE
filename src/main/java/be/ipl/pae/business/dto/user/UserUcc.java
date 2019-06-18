package be.ipl.pae.business.dto.user;

import be.ipl.pae.business.ucc.GenericUcc;
import be.ipl.pae.dal.user.UserDao;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.InvalidLoginException;
import be.ipl.pae.exceptions.InvalidPasswordException;
import be.ipl.pae.exceptions.TransactionErrorException;

import java.util.List;

public interface UserUcc extends GenericUcc<UserDao, UserDto> {
  /**
   * This method is used when someone try to login himself to the platform.
   *
   * @param login The login of the user
   * @param password The password of the user
   * @throws InvalidPasswordException if the password isn't correct
   * @throws InvalidLoginException if the login doesn't exist
   */
  UserDto login(String login, String password) throws InvalidPasswordException,
      InvalidLoginException, TransactionErrorException, InternalServerException;

  List<UserDto> researchStudents(String firstNameCriteria, String lastNameCriteria)
      throws TransactionErrorException, InternalServerException;
}
