package be.ipl.pae.business.dto.mobilities.mobilitychoice;

import be.ipl.pae.business.dto.GenericDto;

public interface MobilityChoiceDto extends GenericDto {

  Integer getVersionNumber();

  Integer getIdMobilityChoice();

  /**
   * Get the preference order of the mobility mobilitychoice.
   *
   * @return an integer the preference order
   */
  Integer getPreferenceOrder();

  /**
   * Get the country of the mobility mobilitychoice.
   *
   * @return the country
   */
  String getCountry();

  /**
   * Get the academic year of the mobility mobilitychoice.
   *
   * @return the academic year
   */
  Integer getAcademicYear();

  /**
   * Get the semester of the mobility mobilitychoice.
   *
   * @return the semester
   */
  Integer getSemester();

  /**
   * Get the user of the mobility mobilitychoice.
   *
   * @return the user
   */
  Integer getIdUser();

  /**
   * Get the partner of the mobility mobilitychoice.
   *
   * @return the partner
   */
  Integer getPartner();

  /**
   * Get the mobility type of the mobility mobilitychoice.
   *
   * @return the mobility type
   */
  String getMobilityType();

  /**
   * Get the mobility program of the mobility mobilitychoice.
   *
   * @return the mobility program
   */
  String getProgram();

}
