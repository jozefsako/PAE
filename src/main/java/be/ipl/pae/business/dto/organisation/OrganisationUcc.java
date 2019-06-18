package be.ipl.pae.business.dto.organisation;

import be.ipl.pae.business.ucc.GenericUcc;
import be.ipl.pae.dal.organisation.OrganisationDao;
import be.ipl.pae.exceptions.InternalServerException;

import java.util.List;

public interface OrganisationUcc extends GenericUcc<OrganisationDao, OrganisationDto> {

  List<OrganisationDto> researchOrganisations(String nameCriteria, String countryCriteria,
      String cityCriteria) throws InternalServerException;
}
