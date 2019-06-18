package be.ipl.pae.business.dto.organisation;

import be.ipl.pae.business.ucc.GenericUccImpl;
import be.ipl.pae.dal.organisation.OrganisationDao;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;

import java.util.List;

public class OrganisationUccImpl extends GenericUccImpl<OrganisationDao, OrganisationDto>
    implements OrganisationUcc {

  @Override
  public List<OrganisationDto> researchOrganisations(String nameCriteria, String countryCriteria,
      String cityCriteria) throws InternalServerException {
    List<OrganisationDto> list = null;
    try {
      dalServices.startTransaction();
      list = this.dao.researchOrganisation(nameCriteria, countryCriteria, cityCriteria);
      dalServices.commitTransaction();
    } catch (DbErrorException err) {
      err.printStackTrace();
      try {
        dalServices.rollbackTransaction();
      } catch (DbErrorException err2) {
        err2.printStackTrace();
      }
    }
    return list;
  }
}
