package be.ipl.pae.business;

import be.ipl.pae.business.dto.country.CountryDto;
import be.ipl.pae.business.dto.country.CountryImpl;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityImpl;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceDto;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceImpl;
import be.ipl.pae.business.dto.organisation.OrganisationDto;
import be.ipl.pae.business.dto.organisation.OrganisationImpl;
import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.business.dto.user.UserImpl;
import be.ipl.pae.dal.DalBackendServices;
import be.ipl.pae.dal.DalServicesImpl;
import be.ipl.pae.exceptions.DbErrorException;

public class BusinessFactoryImpl implements BusinessFactory {

  public UserDto getUser() {
    return new UserImpl();
  }

  public MobilityChoiceDto getMobilityChoice() {
    return new MobilityChoiceImpl();
  }

  public DalBackendServices getDalServices() throws DbErrorException {
    return new DalServicesImpl();
  }

  public CountryDto getCountry() {
    return new CountryImpl();
  }

  @Override
  public MobilityDto getMobility() {
    return new MobilityImpl();
  }

  @Override
  public OrganisationDto getOrganisation() {
    return new OrganisationImpl();
  }
}
