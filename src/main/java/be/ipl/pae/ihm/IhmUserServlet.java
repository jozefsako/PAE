package be.ipl.pae.ihm;

import static be.ipl.pae.business.dto.user.UserType.RESPONSIBLE;
import static be.ipl.pae.util.Utils.checkPattern;

import be.ipl.pae.business.dto.user.User;
import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.business.dto.user.UserImpl;
import be.ipl.pae.business.dto.user.UserUcc;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.IdNotFoundException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.TransactionErrorException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.javadatetime.JavaDateTimeBundle;
import com.owlike.genson.ext.javadatetime.TimestampFormat;

import org.hamcrest.core.Is;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IhmUserServlet {


  /**
   * This method is used to retrieve the user.
   * 
   * @param req The request from the servlet
   * @param resp The response for the servlet
   * @param userUcc The user object created
   * @throws Is produced by failed or interrupted I/O operations
   */
  public void getUser(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc)
      throws IOException {
    try {
      UserDto userDto = userUcc.findDto((int) getCurrentUser(req));
      String json = new Genson().serialize(userDto);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err1) {
      err1.printStackTrace();
    } catch (IdNotFoundException err2) {
      err2.printStackTrace();
    }
  }

  /**
   * This method is used to update the user data.
   * 
   * @param req The request from the servlet
   * @param resp The answer to the servlet
   * @param userUcc The user object to be updated
   * @throws IOException Is produced by failed or interrupted I/O operations
   */
  public void updateUser(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc)
      throws IOException {
    Genson genson = new GensonBuilder().withBundle(new JavaDateTimeBundle()
        .setTemporalAccessorTimestampFormat(LocalDate.class, TimestampFormat.ARRAY)).create();

    /* Checks if user is connected */
    try {
      UserDto connectedUser = userUcc.findDto((int) getCurrentUser(req));

      if (connectedUser != null) {

        User userDto = genson.deserialize(req.getParameter("user"), UserImpl.class);
        userDto.setVersionNumber(userDto.getVersionNumber() + 1);
        List<String> errors = new ArrayList<>();
        checkPattern(errors, "^(.*\\@.*vinci\\.be)?$", userDto.getEmail(), "email");
        checkPattern(errors, "^([a-zA-Z]*)?$", userDto.getNationality(), "nationality");
        checkPattern(errors, "^((\\+32|0)[0-9]{9})?$", userDto.getTelephone(), "telephone");
        checkPattern(errors, "^([A-Z]{2}[0-9]{2}[a-zA-Z0-9]{12})?$", userDto.getIban(), "iban");
        checkPattern(errors, "^([A-Z0-9]{8})?$", userDto.getBicCode(), "bank_bic");

        if (!errors.isEmpty()) {
          for (String error : errors) {
            System.out.println("error = " + error);
          }
          String json = new Genson().serialize(errors);
          resp.setContentType("application/json");
          resp.getOutputStream().write(json.getBytes());
        } else {

          /* If Responsible is connected && want to update the type of user */
          if (connectedUser.getTypeUser().equals(RESPONSIBLE.getUserType())) {
            String typeUser = req.getParameter("typeUser");
            if (!typeUser.equals("")) {
              userDto.setTypeUser(typeUser);
            }
          }

          try {
            userUcc.update(userDto);
            String json = new Genson().serialize(userDto);
            resp.setContentType("application/json");
            resp.getOutputStream().write(json.getBytes());
          } catch (TransactionErrorException | InternalServerException
              | DbDataExpiredException err) {
            resp.setStatus(400);
          }
        }

      }
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err) {
      err.printStackTrace();
    } catch (IdNotFoundException err) {
      err.printStackTrace();
    }

  }

  /**
   * This method is called to retrieved the user data.
   * 
   * @param req The request from the servlet
   * @param resp The answer to the servlet
   * @param userUcc The user object from the data are retrieved
   */
  public void viewUser(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc) {
    try {
      UserDto userDto = userUcc.findDto(Integer.parseInt(req.getParameter("idUser")));
      String json = new Genson().serialize(userDto);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException err) {
      err.printStackTrace();
    } catch (TransactionErrorException err1) {
      err1.printStackTrace();
    } catch (InternalServerException err2) {
      err2.printStackTrace();
    } catch (IdNotFoundException err3) {
      err3.printStackTrace();
    }
  }

  /**
   * This method is called to get all of the users.
   * 
   * @param req The request from the servlet
   * @param resp The response to the servlet
   * @param userUcc This object will be used to retrieve all of the users
   */
  public void getAllUsers(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc) {
    try {
      /*
       * Check if the currentUser is a teacher
       */
      UserDto userDto = userUcc.findDto((int) getCurrentUser(req));
      String json;
      if (userDto.getTypeUser().equals("S")) {
        json = new Genson().serialize(userDto);
      } else {
        List<UserDto> userDtos = userUcc.findAllDtos();
        json = new Genson().serialize(userDtos);
      }
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());

    } catch (IOException exp) {
      exp.printStackTrace();
    } catch (IdNotFoundException exp1) {
      exp1.printStackTrace();
    } catch (TransactionErrorException exp2) {
      exp2.printStackTrace();
    } catch (InternalServerException exp3) {
      exp3.printStackTrace();
    }
  }

  /**
   * This method is used to get the current id user from the cookie.
   * 
   * @param req The request of the servlet
   * @return The id of the current user
   * @throws IOException Is produced by failed or interrupted I/O operations
   */
  public static Object getCurrentUser(HttpServletRequest req) throws IOException {
    /* Cookie */
    String token = null;
    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for (Cookie c : cookies) {
        if ("user".equals(c.getName()) && c.getSecure()) {
          token = c.getValue();
        } else if ("user".equals(c.getName()) && token == null) {
          token = c.getValue();
        }
      }
    }
    Object userId = null;
    /* Vérification JWT */
    try {
      DecodedJWT jwt = JWT.decode(token);
      userId = jwt.getClaim("user").asInt();
    } catch (Exception exp) {
      exp.printStackTrace();
    }
    return userId;
  }

  /**
   * This method is used to get all of the students.
   * 
   * @param req The request from the servlet
   * @param resp The answer for the servlet
   * @param userUcc The userUcc class object that will be used to retrieve all of the students user
   */
  public void researchStudents(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc) {
    List<UserDto> userDtos = null;
    try {
      userDtos = userUcc.researchStudents(req.getParameter("firstNameCriteria"),
          req.getParameter("lastNameCriteria"));
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err) {
      err.printStackTrace();
    }
    String json = new Genson().serialize(userDtos);
    resp.setContentType("application/json");
    try {
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException err) {
      err.printStackTrace();
    }

  }

  /**
   * Update the type of the user.
   * 
   * @param req The request from the servlet
   * @param resp The response to the servlet
   * @param userUcc The userUcc class object that will be used to update the type of the user
   */
  public void updateUserType(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc) {
    User user = new UserImpl();
    user.setIdUser(Integer.valueOf(req.getParameter("idUser")));
    user.setTypeUser(req.getParameter("typeUser"));

    try {
      userUcc.update(user);
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (DbDataExpiredException err) {
      resp.setStatus(400);
      err.printStackTrace();
    } catch (InternalServerException err) {
      err.printStackTrace();
    }
  }
}
