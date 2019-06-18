package be.ipl.pae.business.dto.country;

public interface Country extends CountryDto {

  /**
   * Set or update the boolean takenCareOf.
   *
   * @param takenCareOf Said if it has been treated properly or not
   */
  void setTakenCareOf(boolean takenCareOf);

  /**
   * Set or update the country code.
   * 
   * @param countryCode3 The country code to be set
   */
  void setCountryCode3(String countryCode3);

  /**
   * Set or update the name of the country.
   * 
   * @param countryName The name to be set
   */
  void setCountryName(String countryName);
}
