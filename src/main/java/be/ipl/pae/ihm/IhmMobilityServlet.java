package be.ipl.pae.ihm;

import static be.ipl.pae.business.ServerMessage.CLIENT_NOT_SIGNED_IN;
import static be.ipl.pae.business.dto.mobilities.mobility.MobilityState.COMPLETED;
import static be.ipl.pae.business.dto.mobilities.mobility.MobilityState.CREATED;

import be.ipl.pae.business.dto.country.CountryDto;
import be.ipl.pae.business.dto.mobilities.mobility.Mobility;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityImpl;
import be.ipl.pae.business.dto.mobilities.mobilitycancellation.MobilityCancellation;
import be.ipl.pae.business.dto.mobilities.mobilitycancellation.MobilityCancellationDto;
import be.ipl.pae.business.dto.mobilities.mobilitycancellation.MobilityCancellationImpl;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoice;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceDto;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceImpl;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityType;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.Program;
import be.ipl.pae.business.dto.mobilities.mobilitydocument.MobilityDocumentDto;
import be.ipl.pae.business.dto.organisation.OrganisationDto;
import be.ipl.pae.business.dto.organisation.OrganisationUcc;
import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.business.dto.user.UserType;
import be.ipl.pae.business.dto.user.UserUcc;
import be.ipl.pae.business.ucc.country.CountryUcc;
import be.ipl.pae.business.ucc.mobilities.mobility.MobilityUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitycancellation.MobilityCancellationUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitychoice.MobilityChoiceUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitydocument.MobilityDocumentUcc;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.IdNotFoundException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.MobilityAlreadyConfirmedException;
import be.ipl.pae.exceptions.MobilityChoiceExpiredException;
import be.ipl.pae.exceptions.TransactionErrorException;
import be.ipl.pae.util.Utils;

import com.owlike.genson.Genson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IhmMobilityServlet {

  /**
   * This is method is called when a user wants to cancel a mobility.
   * 
   * @param req The request to the servlet
   * @param resp The answer of the servlet
   * @param mobilityCancellationUcc The mobility to be cancelled
   */
  public void cancelMobility(HttpServletRequest req, HttpServletResponse resp,
      MobilityCancellationUcc mobilityCancellationUcc) {
    int mobilityId = Integer.valueOf(req.getParameter("idMobilityChoice"));
    try {
      /* Checks if it's not already canceled */
      if (mobilityCancellationUcc.findAllDtosById(mobilityId, "idMobilityChoice").isEmpty()) {
        MobilityCancellation mobilityCancellation = new MobilityCancellationImpl();
        mobilityCancellation.setDateCancellation(LocalDate.now());
        mobilityCancellation.setIdUser((int) IhmUserServlet.getCurrentUser(req));
        mobilityCancellation.setIdMobilityChoice(mobilityId);
        mobilityCancellation.setReason(req.getParameter("reason"));
        mobilityCancellationUcc.insertDto(mobilityCancellation);
        String json = new Genson().serialize(mobilityCancellation);
        resp.setContentType("application/json");
        resp.getOutputStream().write(json.getBytes());
      }
    } catch (IOException err) {
      err.printStackTrace();
    } catch (DbDataExpiredException err) {
      err.printStackTrace();
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err) {
      err.printStackTrace();
    }
  }

  /**
   * This method is called to retrieve all the data of a mobility from a mobility document.
   * 
   * @param req The request to the servlet
   * @param resp The reponse of the servlet
   * @param mobilityDocumentUcc The mobility document whose data we want to retrieve
   */
  public void getMobilityData(HttpServletRequest req, HttpServletResponse resp,
      MobilityDocumentUcc mobilityDocumentUcc) {
    int mobilityId = Integer.valueOf(req.getParameter("id"));
    try {
      List<MobilityDocumentDto> mobilityDocumentDtos =
          mobilityDocumentUcc.findAllDtosById(mobilityId, "idMobilityChoice");
      String json = new Genson().serialize(mobilityDocumentDtos);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err1) {
      err1.printStackTrace();
    } catch (IOException err2) {
      err2.printStackTrace();
    }
  }

  /**
   * This method is called to retrieve all the mobilities from the database.
   * 
   * @param req The request to the servlet
   * @param resp The answer of the servlet
   * @param userUcc The user who want to retrieve all of his mobilities
   * @param mobilityUcc The list that will contains all of the mobilities found
   */
  public void getMobilities(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc,
      MobilityUcc mobilityUcc) {
    try {
      UserDto userDto = userUcc.findDto((int) IhmUserServlet.getCurrentUser(req));
      String json;
      ArrayList<MobilityDto> mobilityDtos = new ArrayList<>();

      /*
       * Checks if you are connected setResponseMessage if null
       */
      if (userDto == null) {
        Utils.setResponseMessage(resp, CLIENT_NOT_SIGNED_IN);
      } else if (userDto.getTypeUser().equals(UserType.STUDENT.getUserType())) {
        /* a finir trouver toutes les mobilites de l'utilisateur connecté */
        mobilityDtos = new ArrayList<>(mobilityUcc.findAllMobilitiesByUser(userDto.getIdUser()));
      } else {
        mobilityDtos = new ArrayList<>(mobilityUcc.findAllDtos());
      }
      json = new Genson().serialize(mobilityDtos);
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
   * This method is called to get all of the organisations.
   * 
   * @param resp The response from the servlet
   * @param organisationUcc The list that will contains all of the organisation found
   */
  public void getOrganisations(HttpServletResponse resp, OrganisationUcc organisationUcc) {
    try {
      List<OrganisationDto> organisations = new ArrayList<>();
      organisations.addAll(organisationUcc.findAllDtos());
      String json = new Genson().serialize(organisations);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException exp) {
      exp.printStackTrace();
    } catch (TransactionErrorException exp1) {
      exp1.printStackTrace();
    } catch (InternalServerException exp2) {
      exp2.printStackTrace();
    }
  }

  /**
   * This method is called to confirm a mobility.
   * 
   * @param req The request for the servlet
   * @param resp The answer from the servlet
   * @param mobilityUcc The mobility to be confirm
   * @param userUcc The user who wants to confirm the mobility
   * @param countryUcc The country that we want to retrieve the code
   * @param mobilityChoiceUcc The mobility choice to be confirm
   * @throws IOException Produced by failed or interrupted I/O operations.
   */
  public void confirmMobility(HttpServletRequest req, HttpServletResponse resp,
      MobilityUcc mobilityUcc, UserUcc userUcc, CountryUcc countryUcc,
      MobilityChoiceUcc mobilityChoiceUcc) throws IOException {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* User est connecté et si c'est un professeur */
      if (currentUser != null
          && !currentUser.getTypeUser().equals(UserType.STUDENT.getUserType())) {
        int mobilityId = Integer.parseInt(req.getParameter("mobility_id"));
        MobilityChoiceDto mobilityChoiceDto = mobilityChoiceUcc.findDto(mobilityId);

        CountryDto countryDto = countryUcc.getCountryByCode(mobilityChoiceDto.getCountry());

        try {
          MobilityDto mobilityDto = mobilityUcc.findDto(mobilityId);
          if (mobilityDto != null) {
            /* Mobility already exists */
            resp.getOutputStream().write("-2".getBytes());
          }
        } catch (TransactionErrorException err1) {
          err1.printStackTrace();
        } catch (InternalServerException err2) {
          err2.printStackTrace();
        } catch (IdNotFoundException err3) {
          /* Verify if we have to take care of this mobility */
          Mobility mobility = new MobilityImpl();
          mobility.setIdMobility(mobilityId);
          mobility.setVersionNumber(0);
          mobility.setMobilityConfirmation(
              userUcc.findDto((int) IhmUserServlet.getCurrentUser(req)).getTypeUser());

          if (!countryDto.getTakenCareOf()) {
            mobility.setMobilityState(CREATED.getState());
            mobility.setMobilityTool(false);
            mobility.setProEco(false);
          } else {
            mobility.setMobilityState(COMPLETED.getState());
            resp.getOutputStream().write("-1".getBytes());
          }
          mobilityUcc.confirmMobility(mobility);
        }


      }
    } catch (IOException exp) {
      exp.printStackTrace();
    } catch (TransactionErrorException exp1) {
      exp1.printStackTrace();
    } catch (InternalServerException exp2) {
      exp2.printStackTrace();
    } catch (IdNotFoundException exp3) {
      exp3.printStackTrace();
    } catch (MobilityAlreadyConfirmedException exp4) {
      exp4.printStackTrace();
    } catch (DbDataExpiredException exp5) {
      exp5.printStackTrace();
    } catch (MobilityChoiceExpiredException exp6) {
      exp6.printStackTrace();
    }
  }

  /**
   * This method is called to add a mobility choice.
   * 
   * @param req The request for the servlet
   * @param mobilityChoiceUcc The mobility choice to be add
   * @param userUcc The user that adds the mobility
   */
  public void addMobilityChoice(HttpServletRequest req, MobilityChoiceUcc mobilityChoiceUcc,
      UserUcc userUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* User est connecté */
      if (currentUser != null) {
        MobilityChoice mobilityChoice = new MobilityChoiceImpl();
        mobilityChoice.setProgram(req.getParameter("program"));
        mobilityChoice.setMobilityType(req.getParameter("type"));
        mobilityChoice.setCountry(req.getParameter("country"));
        mobilityChoice.setIdUser((int) IhmUserServlet.getCurrentUser(req));
        mobilityChoice.setAcademicYear(LocalDate.now().getYear());
        mobilityChoice.setSemester(Integer.valueOf(req.getParameter("semester")));
        mobilityChoice.setPreferenceOrder(Integer.valueOf(req.getParameter("preference")));
        String organisation = req.getParameter("organisation");
        if (organisation != null) {
          mobilityChoice.setPartner(Integer.valueOf(organisation));
        }
        mobilityChoice.setVersionNumber(0);
        mobilityChoiceUcc.insertDto(mobilityChoice);
      }

    } catch (IOException ioe) {
      ioe.printStackTrace();
    } catch (DbDataExpiredException exp1) {
      exp1.printStackTrace();
    } catch (TransactionErrorException exp2) {
      exp2.printStackTrace();
    } catch (InternalServerException exp3) {
      exp3.printStackTrace();
    } catch (IdNotFoundException exp4) {
      exp4.printStackTrace();
    }
  }

  /**
   * This method is called to get all of the mobility choice from a user.
   * 
   * @param req The request for the servlet
   * @param resp The answer for the servlet
   * @param userUcc The user that wants to retrieve all of his mobility choice
   * @param mobilityChoiceUcc The list that will contains all of the mobilities retrieved
   * @param organisationUcc The list that will contains all of the organisations retrieved from the
   *        mobilities choices
   */
  public void getAllMobilityChoices(HttpServletRequest req, HttpServletResponse resp,
      UserUcc userUcc, MobilityChoiceUcc mobilityChoiceUcc, OrganisationUcc organisationUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);
      List<MobilityChoiceDto> mobilities;
      List<OrganisationDto> organisations;
      List<UserDto> users;

      /* If user is Student */
      if (currentUser.getTypeUser().equals(UserType.STUDENT.getUserType())) {
        mobilities = mobilityChoiceUcc.findAllDtosById(currentUserId, "idUser");
      } else {
        mobilities = mobilityChoiceUcc.findAllDtos();
      }
      organisations = organisationUcc.findAllDtos();
      users = userUcc.findAllDtos();
      Map<String, List> data = new HashMap<>();

      data.put("mobilities", mobilities);
      data.put("organisations", organisations);
      data.put("users", users);

      String json = new Genson().serialize(data);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException exp) {
      exp.printStackTrace();
    } catch (InternalServerException exp1) {
      exp1.printStackTrace();
    } catch (IdNotFoundException exp2) {
      exp2.printStackTrace();
    } catch (TransactionErrorException exp3) {
      exp3.printStackTrace();
    }
  }

  /**
   * This method is called to get all of the programs.
   * 
   * @param resp The request for the servlet
   * @param programs The list that will contains all of the programs found
   */
  public void getPrograms(HttpServletResponse resp, Map<String, String> programs) {
    try {
      if (programs == null) {
        programs = new HashMap<>();
        for (Program program : Program.values()) {
          programs.put(program.getProgramAbbreviation(), program.getProgram());
        }
      }
      String json = new Genson().serialize(programs);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  /**
   * This method is called to get all of the types.
   * 
   * @param resp The response to the servlet
   * @param types The list that will contains all of the types
   */
  public void getTypes(HttpServletResponse resp, List<MobilityType> types) {
    try {
      if (types == null) {
        types = new ArrayList<>();
        types.addAll(Arrays.asList(MobilityType.values()));
      }
      String json = new Genson().serialize(types);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException exp) {
      exp.printStackTrace();
    }
  }

  /**
   * This method is called to get all of the countries.
   * 
   * @param resp The response of the servlet
   * @param countries The list that will contain all of the countries found
   * @param countryUcc The class object that will be used to retrieve all of the countries
   */
  public void getCountries(HttpServletResponse resp, List<CountryDto> countries,
      CountryUcc countryUcc) {
    try {
      if (countries == null) {
        countries = countryUcc.findAllDtos();
      }
      String json = new Genson().serialize(countries);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException exp) {
      exp.printStackTrace();
    } catch (TransactionErrorException exp1) {
      exp1.printStackTrace();
    } catch (InternalServerException exp2) {
      exp2.printStackTrace();
    }
  }

  /**
   * This method is called to get all of the data from a mobility.
   * 
   * @param req The request to the servlet
   * @param resp The response of the servlet
   * @param userUcc The user that ask to see his data mobility
   * @param mobilityChoiceUcc The class object that will help to retrieve all of the mobility choice
   * @param organisationUcc The class object that will help to retrieve all of the organisation
   * @param mobilityDocumentUcc The class object that will help to retrieve all of the document
   * @param mobilityUcc The class object that will help to retrieve all of the mobilities
   * @param mobilityCancellationUcc The class object that will help to retrieve all of the cancelled
   *        mobility choices
   */
  public void getAllDataRelatedToMobility(HttpServletRequest req, HttpServletResponse resp,
      UserUcc userUcc, MobilityChoiceUcc mobilityChoiceUcc, OrganisationUcc organisationUcc,
      MobilityDocumentUcc mobilityDocumentUcc, MobilityUcc mobilityUcc,
      MobilityCancellationUcc mobilityCancellationUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* User est connecté */
      if (currentUser != null) {
        Map<String, Object> data = new HashMap<>();

        /* Get the mobility choice */
        int idMobilityChoice = Integer.parseInt(req.getParameter("idMobility"));
        MobilityChoiceDto mobilityChoiceDto = mobilityChoiceUcc.findDto(idMobilityChoice);
        data.put("mobilityChoice", mobilityChoiceDto);

        /* Get the mobility */
        MobilityDto mobilityDto = mobilityUcc.findDto(idMobilityChoice);
        data.put("mobility", mobilityDto);

        /* Get the student */
        if (currentUser.getTypeUser().equals("S")) {
          data.put("user", currentUser);
        } else {
          data.put("user", userUcc.findDto(mobilityChoiceDto.getIdUser()));
        }

        /* Get the partner */
        if (mobilityChoiceDto.getPartner() != null && mobilityChoiceDto.getPartner() != 0) {
          data.put("org", organisationUcc.findDto(mobilityChoiceDto.getPartner()));
        }

        /* Get the docs */
        List<MobilityDocumentDto> mobilityDocumentDtos =
            mobilityDocumentUcc.findAllDtosById(idMobilityChoice, "idMobilityChoice");
        data.put("docs", mobilityDocumentDtos);


        /* Get the cancelation */
        data.put("cancelation",
            mobilityCancellationUcc.findAllDtosById(idMobilityChoice, "idMobilityChoice"));

        String json = new Genson().serialize(data);
        resp.setContentType("application/json");
        resp.getOutputStream().write(json.getBytes());

      }

    } catch (IOException exp) {
      exp.printStackTrace();
    } catch (InternalServerException exp1) {
      exp1.printStackTrace();
    } catch (IdNotFoundException exp2) {
      exp2.printStackTrace();
    } catch (TransactionErrorException exp3) {
      exp3.printStackTrace();
    }
  }

  /**
   * This method is called to get the mobility choices from a user.
   * 
   * @param req The request for the servlet
   * @param resp The response to the servlet
   * @param userUcc The user that ask for his mobility choice
   * @param mobilityChoiceUcc The class object that will help to retrieve mobility data
   */
  public void getMobilityChoice(HttpServletRequest req, HttpServletResponse resp, UserUcc userUcc,
      MobilityChoiceUcc mobilityChoiceUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* User est connecté */
      if (currentUser != null) {
        int idMobilityChoice = Integer.parseInt(req.getParameter("mobilityChoiceId"));
        MobilityChoiceDto mobilityChoiceDto = mobilityChoiceUcc.findDto(idMobilityChoice);
        String json = new Genson().serialize(mobilityChoiceDto);
        resp.setContentType("application/json");
        resp.getOutputStream().write(json.getBytes());
      }

    } catch (IOException err) {
      err.printStackTrace();
    } catch (InternalServerException err1) {
      err1.printStackTrace();
    } catch (IdNotFoundException err2) {
      err2.printStackTrace();
    } catch (TransactionErrorException err3) {
      err3.printStackTrace();
    }
  }

  /**
   * This method is called when the user reasearch some mobilities.
   * 
   * @param req The request to the servlet
   * @param resp The response from the servlet
   * @param mobilityUcc The class object that will help to retrieve all of the mobilities
   * @param userUcc The user that made the research
   */
  void researchMobility(HttpServletRequest req, HttpServletResponse resp, MobilityUcc mobilityUcc,
      UserUcc userUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);
      /* User est connecté */
      if (currentUser != null
          && !currentUser.getTypeUser().equals(UserType.STUDENT.getUserType())) {
        String yearCriteria = req.getParameter("yearCriteria");
        String stateCriteria = req.getParameter("stateCriteria");
        List<MobilityDto> mobilityDtos;
        mobilityDtos = mobilityUcc.researchMobility(yearCriteria, stateCriteria);
        String json = new Genson().serialize(mobilityDtos);
        resp.setContentType("application/json");
        resp.getOutputStream().write(json.getBytes());
      }

    } catch (InternalServerException err) {
      err.printStackTrace();
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (IdNotFoundException err) {
      err.printStackTrace();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  /**
   * This method is called to get the reasons why the mobilities have been cancelled.
   * 
   * @param req The request to the servlet
   * @param resp The response from the servlet
   * @param mobilityCancellationUcc The class object that will help to retrieve all of the reasons
   * @param userUcc The user that ask to see all of the reasons
   */
  public void getCancelationReasons(HttpServletRequest req, HttpServletResponse resp,
      MobilityCancellationUcc mobilityCancellationUcc, UserUcc userUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* User est connecté */
      if (currentUser != null && !currentUser.getTypeUser().equals("S")) {
        List<MobilityCancellationDto> list = mobilityCancellationUcc.findAllDtos();
        String json = new Genson().serialize(list);
        resp.setContentType("application/json");
        resp.getOutputStream().write(json.getBytes());
      }
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err1) {
      err1.printStackTrace();
    } catch (IOException err2) {
      err2.printStackTrace();
    } catch (IdNotFoundException err3) {
      err3.printStackTrace();
    }
  }

  /**
   * This method is called to view all of the organisations.
   * 
   * @param resp The response to the servlet
   * @param req The request form the servlet
   * @param organisationUcc The organisation class object that will help to retrieve all of the
   *        organisations
   */
  public void viewOrganisation(HttpServletResponse resp, HttpServletRequest req,
      OrganisationUcc organisationUcc) {
    try {
      Integer idOrg = Integer.valueOf(req.getParameter("idOrganisation"));
      OrganisationDto dto = organisationUcc.findDto(idOrg);
      String json = new Genson().serialize(dto);
      resp.setContentType("application/json");
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException exp) {
      exp.printStackTrace();
    } catch (TransactionErrorException exp1) {
      exp1.printStackTrace();
    } catch (InternalServerException exp2) {
      exp2.printStackTrace();
    } catch (IdNotFoundException exp3) {
      exp3.printStackTrace();
    }
  }
}
