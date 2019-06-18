package be.ipl.pae.business;

import be.ipl.pae.business.dto.country.CountryDto;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceDto;
import be.ipl.pae.business.dto.organisation.OrganisationDto;
import be.ipl.pae.business.dto.user.UserDto;
import be.ipl.pae.dal.DalBackendServices;
import be.ipl.pae.exceptions.DbErrorException;

public interface BusinessFactory {
  /**
   * Create a new empty UserDto Object.
   * 
   * @return new empty UserDto Object
   * 
   */
  UserDto getUser();


  /**
   * Create a new empty MobilityChoiceDto Object.
   * 
   * @return new empty MobilityChoiceDto Object
   * 
   */
  MobilityChoiceDto getMobilityChoice();

  /**
   * Create a new empty DalBackendServices Object.
   * 
   * @return new empty DalBackendServices Object
   * 
   * @throws DbErrorException if an error occur during the connection
   */
  DalBackendServices getDalServices() throws DbErrorException;

  /**
   * Create a new empty CountryDto Object.
   * 
   * @return new empty CountryDto Object
   * 
   */
  CountryDto getCountry();

  /**
   * Create a new empty mobility Object.
   *
   * @return new empty MobilityDto Object
   *
   */
  MobilityDto getMobility();

  OrganisationDto getOrganisation();
}
