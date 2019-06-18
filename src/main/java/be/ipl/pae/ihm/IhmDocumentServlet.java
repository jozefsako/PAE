package be.ipl.pae.ihm;

import be.ipl.pae.business.dto.mobilities.mobility.Mobility;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityState;
import be.ipl.pae.business.dto.mobilities.mobilitydocument.MobilityDocument;
import be.ipl.pae.business.dto.mobilities.mobilitydocument.MobilityDocumentDto;
import be.ipl.pae.business.dto.mobilities.mobilitydocument.MobilityDocumentImpl;
import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.business.dto.user.UserUcc;
import be.ipl.pae.business.ucc.mobilities.mobility.MobilityUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitychoice.MobilityChoiceUcc;
import be.ipl.pae.business.ucc.mobilities.mobilitydocument.MobilityDocumentUcc;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.IdNotFoundException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.TransactionErrorException;

import com.owlike.genson.Genson;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IhmDocumentServlet {

  /**
   * This method is called to update the state of the tools used.
   * 
   * @param req The request from the servlet
   * @param resp The response for the servlet
   * @param mobilityUcc The mobility object that will help to update the mobility state
   * @param userUcc The user that updates the state
   */
  public void updateTools(HttpServletRequest req, HttpServletResponse resp, MobilityUcc mobilityUcc,
      UserUcc userUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* Si user est connecté et c'est un professeur */
      if (currentUser != null && !currentUser.getTypeUser().equals("S")) {
        int mobilityId = Integer.valueOf(req.getParameter("idMobilityChoice"));
        boolean proEco = Boolean.valueOf(req.getParameter("proEco"));
        boolean mobilityTool = Boolean.valueOf(req.getParameter("mobilityTool"));
        Mobility mobility = (Mobility) mobilityUcc.findDto(mobilityId);
        mobility.setMobilityTool(mobilityTool);
        mobility.setProEco(proEco);
        int versionNumber = Integer.valueOf(req.getParameter("versionNumber"));
        mobility.setVersionNumber(versionNumber + 1);
        mobilityUcc.update(mobility);

        String json = new Genson().serialize(mobility);
        resp.setContentType("application/json");
        resp.getOutputStream().write(json.getBytes());
      }
    } catch (TransactionErrorException exp) {
      exp.printStackTrace();
    } catch (InternalServerException exp1) {
      exp1.printStackTrace();
    } catch (IdNotFoundException exp2) {
      exp2.printStackTrace();
    } catch (DbDataExpiredException exp3) {
      exp3.printStackTrace();
    } catch (IOException exp4) {
      exp4.printStackTrace();
    }

  }

  /**
   * This method is called to confirm a payment.
   * 
   * @param req The request from the servlet
   * @param resp the answer for the servlet
   * @param mobilityUcc The mobility class object that will help to retrieve all of the mobilities
   * @param mobilityDocumentUcc The document class object that will help to retrieve all of the
   *        documents
   * @param mobilityChoiceUcc The mobility choice class object that will help to retrieve all of the
   *        mobilities choices
   * @param userUcc The user that confirm the payment
   */
  public void confirmPayment(HttpServletRequest req, HttpServletResponse resp,
      MobilityUcc mobilityUcc, MobilityDocumentUcc mobilityDocumentUcc,
      MobilityChoiceUcc mobilityChoiceUcc, UserUcc userUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* Si user est connecté et c'est un professeur */
      if (currentUser != null && !currentUser.getTypeUser().equals("S")) {
        int mobilityId = Integer.valueOf(req.getParameter("idMobilityChoice"));
        Mobility mobility = (Mobility) mobilityUcc.findDto(mobilityId);
        List<MobilityDocumentDto> mobilityDocumentDtos =
            mobilityDocumentUcc.findAllDtosById(mobilityId, "idMobilityChoice");

        String program = mobilityChoiceUcc.findDto(mobilityId).getProgram();

        mobility.setPaymentSent(true);
        if (program.equals("ER") ? mobilityDocumentDtos.size() == 5
            : mobilityDocumentDtos.size() == 4) {
          mobility.setMobilityState(MobilityState.IN_PROGRESS.getState());
        } else if (program.equals("ER") ? mobilityDocumentDtos.size() == 10
            : mobilityDocumentDtos.size() == 8) {
          mobility.setMobilityState(MobilityState.COMPLETED.getState());
        }
        int versionNumber = Integer.valueOf(req.getParameter("versionNumber"));
        mobility.setVersionNumber(versionNumber + 1);
        mobilityUcc.update(mobility);

      }

    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err) {
      err.printStackTrace();
    } catch (IdNotFoundException err) {
      err.printStackTrace();
    } catch (DbDataExpiredException err) {
      err.printStackTrace();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  /**
   * This method is called to sign a document for a mobility.
   * 
   * @param req The request from the servlet
   * @param resp The response for the servlet
   * @param mobilityDocumentUcc The document class object that will help to retrieve all of the
   *        documents
   * @param mobilityChoiceUcc The mobility choice class object that will help to retrieve all of the
   *        mobilities choices
   * @param mobilityUcc The mobility class object that will help to retrieve all of the mobilities
   * @param userUcc The user that wants to sign the document
   */
  public void signDocument(HttpServletRequest req, HttpServletResponse resp,
      MobilityDocumentUcc mobilityDocumentUcc, MobilityChoiceUcc mobilityChoiceUcc,
      MobilityUcc mobilityUcc, UserUcc userUcc) {
    try {
      int currentUserId = (int) IhmUserServlet.getCurrentUser(req);
      UserDto currentUser = userUcc.findDto(currentUserId);

      /* Si user est connecté et c'est un professeur */
      if (currentUser != null && !currentUser.getTypeUser().equals("S")) {
        int mobilityChoiceId = Integer.valueOf(req.getParameter("idMobilityChoice"));
        List<String> documents =
            new Genson().deserialize(req.getParameter("mobilityDocuments"), List.class);
        String program = mobilityChoiceUcc.findDto(mobilityChoiceId).getProgram();

        for (String document : documents) {
          Mobility mobility = (Mobility) mobilityUcc.findDto(mobilityChoiceId);
          MobilityDocument mobilityDocument = new MobilityDocumentImpl();
          mobilityDocument.setIdMobilityChoice(mobilityChoiceId);
          mobilityDocument.setDocument(document);

          mobilityDocumentUcc.insertDto(mobilityDocument);
          List<MobilityDocumentDto> mobilityDocumentDtos =
              mobilityDocumentUcc.findAllDtosById(mobilityChoiceId, "idMobilityChoice");

          if (mobilityDocumentDtos.size() == 1) {
            mobility.setMobilityState(MobilityState.IN_PREPARATION.getState());
          } else if (program.equals("ER") ? mobilityDocumentDtos.size() == 5
              : mobilityDocumentDtos.size() == 4) {
            if (mobility.getPaymentSent()) {
              mobility.setMobilityState(MobilityState.IN_PROGRESS.getState());
            } else {
              mobility.setMobilityState(MobilityState.TO_PAY.getState());
            }
          } else if (program.equals("ER") ? mobilityDocumentDtos.size() == 10
              : mobilityDocumentDtos.size() == 8) {
            mobility.setMobilityState(MobilityState.TO_PAY_SOLD.getState());
          }

          mobility.setVersionNumber(mobility.getVersionNumber() + 1);
          mobilityUcc.update(mobility);
        }

      }

    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err) {
      err.printStackTrace();
    } catch (DbDataExpiredException err) {
      err.printStackTrace();
    } catch (IdNotFoundException err) {
      err.printStackTrace();
    } catch (IOException err) {
      err.printStackTrace();
    }
  }
}
