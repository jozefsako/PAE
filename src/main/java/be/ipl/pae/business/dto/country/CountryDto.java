package be.ipl.pae.business.dto.country;

import be.ipl.pae.business.dto.GenericDto;

public interface CountryDto extends GenericDto {

  /**
   * Return the responsibility of the scholarship.
   *
   * @return a boolean that contains the responsibility of the scholarship
   *
   */
  boolean getTakenCareOf();

  /**
   * Return the country code.
   * 
   * @return a String that contains the country code
   * 
   */
  String getCountryCode3();

  /**
   * Return the name code.
   * 
   * @return a String that contains the country name
   * 
   */
  String getCountryName();
}
