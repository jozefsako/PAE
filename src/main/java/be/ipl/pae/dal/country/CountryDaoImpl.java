package be.ipl.pae.dal.country;

import be.ipl.pae.business.dto.country.Country;
import be.ipl.pae.business.dto.country.CountryDto;
import be.ipl.pae.business.dto.country.CountryImpl;
import be.ipl.pae.dal.GenericDaoImpl;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.util.MyLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class CountryDaoImpl extends GenericDaoImpl<CountryDto, Country> implements CountryDao {

  public CountryDaoImpl() {
    super(CountryImpl::new, CountryDto.class, Country.class);
  }

  @Override
  public CountryDto getCountryByCode(String code) throws DbErrorException {
    String query = "SELECT * FROM pae.countries WHERE country_code3 = (?)";
    PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
    CountryDto countryDto = businessFactory.getCountry();
    try {
      ps.setString(1, code);
      ResultSet resultSet = ps.executeQuery();
      if (!resultSet.next()) {
        return null;
      }
      fillDto(countryDto, resultSet);
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    } catch (ClassCompatibilityException exp) {
      exp.printStackTrace();
    }
    return countryDto;
  }
}
