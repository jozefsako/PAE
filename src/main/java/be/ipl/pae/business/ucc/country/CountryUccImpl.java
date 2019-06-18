package be.ipl.pae.business.ucc.country;

import be.ipl.pae.business.dto.country.CountryDto;
import be.ipl.pae.business.ucc.GenericUccImpl;
import be.ipl.pae.dal.country.CountryDao;
import be.ipl.pae.exceptions.DbErrorException;

public class CountryUccImpl extends GenericUccImpl<CountryDao, CountryDto> implements CountryUcc {

  @Override
  public CountryDto getCountryByCode(String code) {
    CountryDto countryDto = null;
    try {
      dalServices.startTransaction();
      countryDto = dao.getCountryByCode(code);
      dalServices.commitTransaction();
    } catch (DbErrorException exp) {
      exp.printStackTrace();
    }
    return countryDto;
  }
}

