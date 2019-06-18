package be.ipl.pae.business.dto.country;

import be.ipl.pae.business.dto.GenericDtoImpl;

public class CountryImpl extends GenericDtoImpl implements Country, CountryDto {

  private String countryCode3;
  private String countryName;
  private boolean takenCareOf;

  public CountryImpl() {
  }

  @Override
  public boolean getTakenCareOf() {
    return takenCareOf;
  }

  @Override
  public void setTakenCareOf(boolean takenCareOf) {
    this.takenCareOf = takenCareOf;
  }

  @Override
  public String getCountryCode3() {
    return countryCode3;
  }

  @Override
  public String getCountryName() {
    return this.countryName;
  }

  @Override
  public void setCountryCode3(String countryCode3) {
    this.countryCode3 = countryCode3;
  }

  @Override
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

}
