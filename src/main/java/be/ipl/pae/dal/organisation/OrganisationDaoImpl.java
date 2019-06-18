package be.ipl.pae.dal.organisation;

import be.ipl.pae.business.dto.organisation.Organisation;
import be.ipl.pae.business.dto.organisation.OrganisationDto;
import be.ipl.pae.business.dto.organisation.OrganisationImpl;
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

public class OrganisationDaoImpl extends GenericDaoImpl<OrganisationDto, Organisation>
    implements OrganisationDao {

  public OrganisationDaoImpl() {
    super(OrganisationImpl::new, OrganisationDto.class, Organisation.class);
  }

  @Override
  public List<OrganisationDto> researchOrganisation(String nameCriteria, String countryCriteria,
      String cityCriteria) throws DbErrorException, InternalServerException {
    List<OrganisationDto> list = new ArrayList<>();

    String query = "SELECT org.* FROM pae.organisations org, pae.countries co "
        + "WHERE org.country = co.country_code3 AND org.legal_name ILIKE '%' || ? || '%' "
        + "AND co.country_name ILIKE '%' || ? || '%' AND org.city ILIKE '%' || ? || '%'";
    try {
      PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
      ps.setString(1, nameCriteria.toLowerCase());
      ps.setString(2, countryCriteria.toLowerCase());
      ps.setString(3, cityCriteria.toLowerCase());
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        OrganisationDto organisationDto = new OrganisationImpl();
        fillDto(organisationDto, resultSet);
        list.add(organisationDto);
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
