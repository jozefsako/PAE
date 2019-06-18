package be.ipl.pae.dal.user;

import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.dal.GenericDao;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;

import java.util.List;

public interface UserDao extends GenericDao<UserDto> {

  UserDto findUserByLogin(String login) throws DbErrorException, ClassCompatibilityException;

  List<UserDto> researchStudents(String firstNameCriteria, String lastNameCriteria)
      throws InternalServerException, DbErrorException;
}
