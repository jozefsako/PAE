package be.ipl.pae.dal.country;

import be.ipl.pae.business.dto.country.CountryDto;
import be.ipl.pae.dal.GenericDao;
import be.ipl.pae.exceptions.DbErrorException;

public interface CountryDao extends GenericDao<CountryDto> {

  CountryDto getCountryByCode(String code) throws DbErrorException;
}

