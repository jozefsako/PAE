package be.ipl.pae.business.dto.mobilities.mobilitychoice;

public interface MobilityChoice extends MobilityChoiceDto {

  void setVersionNumber(Integer versionNumber);

  /**
   * Set or update the id of the mobility mobilitychoice.
   *
   * @param id The id to be set
   */
  void setIdMobilityChoice(Integer id);

  /**
   * Set or update the preference order of the mobility mobilitychoice.
   *
   * @param order The preference order to be set
   */
  void setPreferenceOrder(Integer order);

  /**
   * Set or update the country of the mobility mobilitychoice.
   *
   * @param country The country to be set
   */
  void setCountry(String country);

  /**
   * Set or update academic year of the mobility mobilitychoice.
   *
   * @param year The year to be set
   */
  void setAcademicYear(Integer year);

  /**
   * Set or update the semester of the mobility mobilitychoice.
   *
   * @param semester The semester to be set
   */
  void setSemester(Integer semester);

  /**
   * Set or update the idUser of the mobility mobilitychoice.
   *
   * @param idUser The idUser to be set
   */
  void setIdUser(Integer idUser);

  /**
   * Set or update the partner of the mobility mobilitychoice.
   *
   * @param partner The partner to be set
   */
  void setPartner(Integer partner);

  /**
   * Set or update the mobilityType of the mobility mobilitychoice.
   *
   * @param mobilityType The mobilityType to be set
   */
  void setMobilityType(String mobilityType);

  /**
   * Set or update the program of the mobility mobilitychoice.
   *
   * @param program The program to be set
   */
  void setProgram(String program);
}
