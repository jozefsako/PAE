package be.ipl.pae.business.ucc.country;

import be.ipl.pae.business.dto.country.CountryDto;
import be.ipl.pae.business.ucc.GenericUcc;
import be.ipl.pae.dal.country.CountryDao;

public interface CountryUcc extends GenericUcc<CountryDao, CountryDto> {

  CountryDto getCountryByCode(String country);
}
