package be.ipl.pae.dal.organisation;

import be.ipl.pae.business.dto.organisation.OrganisationDto;
import be.ipl.pae.dal.GenericDao;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;

import java.util.List;

public interface OrganisationDao extends GenericDao<OrganisationDto> {

  List<OrganisationDto> researchOrganisation(String nameCriteria, String countryCriteria,
      String cityCriteria) throws DbErrorException, InternalServerException;
}
