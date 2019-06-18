package be.ipl.pae.business.dto.user;

import be.ipl.pae.business.ucc.GenericUccImpl;
import be.ipl.pae.dal.user.UserDao;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.InvalidLoginException;
import be.ipl.pae.exceptions.InvalidPasswordException;
import be.ipl.pae.exceptions.TransactionErrorException;

import java.util.List;

public class UserUccImpl extends GenericUccImpl<UserDao, UserDto> implements UserUcc {

  @Override
  public UserDto login(String login, String password) throws InvalidPasswordException,
      InvalidLoginException, TransactionErrorException, InternalServerException {
    UserDto userDto;
    try {
      dalServices.startTransaction();
      userDto = dao.findUserByLogin(login);
      if (userDto == null) {
        throw new InvalidLoginException();
      }
      User user = (User) userDto;
      if (!user.checkPassword(password)) {
        throw new InvalidPasswordException();
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
  public List<UserDto> researchStudents(String firstNameCriteria, String lastNameCriteria)
      throws TransactionErrorException, InternalServerException {
    List<UserDto> list = null;
    try {
      dalServices.startTransaction();
      list = this.dao.researchStudents(firstNameCriteria, lastNameCriteria);
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
