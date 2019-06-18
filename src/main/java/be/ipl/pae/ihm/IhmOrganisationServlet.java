package be.ipl.pae.ihm;

import be.ipl.pae.business.dto.organisation.OrganisationDto;
import be.ipl.pae.business.dto.organisation.OrganisationImpl;
import be.ipl.pae.business.dto.organisation.OrganisationUcc;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.TransactionErrorException;

import com.owlike.genson.Genson;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IhmOrganisationServlet {

  /**
   * The method called to retrieve the organisations according some criteria.
   * 
   * @param req The request from the servlet
   * @param resp The response to the servlet
   * @param organisationUcc The organisation class object that will help to retrieve the
   *        organisations
   */
  public void researchOrganisations(HttpServletRequest req, HttpServletResponse resp,
      OrganisationUcc organisationUcc) {

    List<OrganisationDto> organisationDtos = null;
    try {
      organisationDtos = organisationUcc.researchOrganisations(req.getParameter("nameCriteria"),
          req.getParameter("countryCriteria"), req.getParameter("cityCriteria"));
    } catch (InternalServerException err) {
      err.printStackTrace();
    }
    String json = new Genson().serialize(organisationDtos);
    resp.setContentType("application/json");
    try {
      resp.getOutputStream().write(json.getBytes());
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  /**
   * This method is called to add an organisation.
   * 
   * @param req The request from the servlet
   * @param resp The answer for the servlet
   * @param organisationUcc The class object that will called a method to add the organisation
   */
  public void addOrganisation(HttpServletRequest req, HttpServletResponse resp,
      OrganisationUcc organisationUcc) {
    System.out.println(req.getParameter("json"));
    OrganisationDto organisation =
        new Genson().deserialize(req.getParameter("json"), OrganisationImpl.class);
    System.out.println(organisation);
    try {
      organisationUcc.insertDto(organisation);
    } catch (TransactionErrorException err) {
      err.printStackTrace();
    } catch (InternalServerException err) {
      err.printStackTrace();
    } catch (DbDataExpiredException err) {
      err.printStackTrace();
    }
  }
}
