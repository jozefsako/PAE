package be.ipl.pae.ihm;

import static be.ipl.pae.business.ServerMessage.LOGIN_FAILED;

import be.ipl.pae.business.dto.user.User;
import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.business.dto.user.UserImpl;
import be.ipl.pae.business.dto.user.UserType;
import be.ipl.pae.business.dto.user.UserUcc;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.InvalidLoginException;
import be.ipl.pae.exceptions.InvalidPasswordException;
import be.ipl.pae.exceptions.TransactionErrorException;
import be.ipl.pae.util.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.mindrot.bcrypt.BCrypt;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IhmConnexionServlet {

  private final Algorithm algorithm = Algorithm.HMAC256("secret");

  /**
   * It's the method call when the user needs to into the platform.
   * 
   * @param req It's the request for the servlet
   * @param resp It's the response from the servlet
   * @param userUcc The userUcc object of the user connected
   * @throws IOException This exceptions produced by failed or interrupted I/O operations
   */
  public void login(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc)
      throws IOException {
    System.out.println("login");
    try {
      UserDto userDto;

      if (req.getParameter("action").equals("register")) {
        userDto = userUcc.login(req.getParameter("email"), req.getParameter("password"));
      } else {
        userDto = userUcc.login(req.getParameter("login"), req.getParameter("password"));
      }

      String token = new JWT().create().withClaim("user", userDto.getIdUser()).sign(algorithm);
      Cookie cookie = new Cookie("user", token);
      cookie.setPath("/");
      cookie.setMaxAge(60 * 60 * 24 * 365);
      resp.addCookie(cookie);
      resp.setStatus(200);
    } catch (InvalidPasswordException | InvalidLoginException exp) {
      Utils.setResponseMessage(resp, LOGIN_FAILED);
    } catch (TransactionErrorException exp1) {
      // problem transaction
      exp1.printStackTrace();
      Utils.setResponseMessage(resp, LOGIN_FAILED);
    } catch (InternalServerException exp2) {
      // problem code
      exp2.printStackTrace();
      Utils.setResponseMessage(resp, LOGIN_FAILED);
    }
  }

  /**
   * It's the method call when the user register into the platform.
   * 
   * @param req It's the request for the servlet
   * @param resp It's the response from the servlet
   * @param userUcc The userUcc object created if the registration is accepted
   * @throws IOException This exceptions produced by failed or interrupted I/O operations
   */
  public void register(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc)
      throws IOException {
    try {
      String typeUser;
      if (userUcc.findAllDtos().isEmpty()) {
        typeUser = UserType.RESPONSIBLE.getUserType();
      } else {
        typeUser = UserType.STUDENT.getUserType();
      }

      User user = new UserImpl();
      user.setFirstName(req.getParameter("firstname"));
      user.setLastName(req.getParameter("lastname"));
      user.setUsername(req.getParameter("username"));
      user.setEmail(req.getParameter("email"));
      user.setTypeUser(typeUser);
      user.setRegistrationDate(LocalDate.now());
      user.setNationality("BEL");
      user.setVersionNumber(0);
      String password = req.getParameter("password");
      String salt = BCrypt.gensalt();
      user.setPasswordUser(BCrypt.hashpw(password, salt));
      userUcc.insertDto(user);
    } catch (DbDataExpiredException exp) {
      // problem version number
      exp.printStackTrace();
    } catch (TransactionErrorException exp1) {
      // problem transaction
      exp1.printStackTrace();
    } catch (InternalServerException exp2) {
      // problem code
      exp2.printStackTrace();
    }
  }

  /**
   * This method is called when the user disconnect from the platform.
   * 
   * @param resp It's the response from the servlet
   */
  public void logout(HttpServletResponse resp) {
    System.out.println("logout");
    Cookie cookie = new Cookie("user", "");
    cookie.setPath("/");
    cookie.setMaxAge(-1);
    resp.addCookie(cookie);
  }

}
