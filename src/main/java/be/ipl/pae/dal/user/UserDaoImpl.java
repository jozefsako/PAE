package be.ipl.pae.dal.user;

import be.ipl.pae.business.dto.user.User;
import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.business.dto.user.UserImpl;
import be.ipl.pae.dal.GenericDaoImpl;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.util.MyLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


public class UserDaoImpl extends GenericDaoImpl<UserDto, User> implements UserDao {

  public UserDaoImpl() {
    super(UserImpl::new, UserDto.class, User.class);
  }

  /**
   * Finds user by login in the database.
   *
   * @param login the login received by the servlet
   * @return The UserDto acquired with the login parameter
   */
  @Override
  public UserDto findUserByLogin(String login)
      throws DbErrorException, ClassCompatibilityException {
    String query = "SELECT * FROM pae.users WHERE username = (?) OR email = (?)";
    PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
    UserDto userDto = businessFactory.getUser();
    try {
      ps.setString(1, login);
      ps.setString(2, login);
      ResultSet resultSet = ps.executeQuery();
      if (!resultSet.next()) {
        return null;
      }
      fillDto(userDto, resultSet);
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
    return userDto;
  }

  @Override
  public List<UserDto> researchStudents(String firstNameCriteria, String lastNameCriteria)
      throws InternalServerException, DbErrorException {
    List<UserDto> list = new ArrayList<>();
    String query = "SELECT * FROM pae.users u WHERE first_name ILIKE '%' || ? || "
        + "'%' AND last_name ILIKE '%' || ? || '%'";
    try {
      PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
      String firstName = firstNameCriteria.toLowerCase();
      String lastName = lastNameCriteria.toLowerCase();
      ps.setString(1, firstName);
      ps.setString(2, lastName);

      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        UserDto mobilityDto = new UserImpl();
        fillDto(mobilityDto, resultSet);
        list.add(mobilityDto);
      }
    } catch (ClassCompatibilityException err) {
      throw new InternalServerException(err.getMessage());
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
    return list;
  }

}
