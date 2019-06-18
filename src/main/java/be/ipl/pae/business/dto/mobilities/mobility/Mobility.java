package be.ipl.pae.business.dto.mobilities.mobility;

public interface Mobility extends MobilityDto {

  void setVersionNumber(Integer versionNumber);

  /**
   * Set or update the MobilityState object.
   *
   * @param mobilityState The state of the mobility to be set
   */
  void setMobilityState(String mobilityState);

  /**
   * Set or update the MobilityConfirmation object.
   *
   * @param mobilityConfirmation The state of confirmation to be set - If it has been confirmed or
   *        not
   */
  void setMobilityConfirmation(String mobilityConfirmation);

  /**
   * Set or update a boolean variable that say if the proEco software has been used.
   * 
   * @param proEco The state of the usage for the proEco software to be set - If it has been used or
   *        not
   */
  void setProEco(boolean proEco);

  /**
   * Set or update a MobilityTool object.
   *
   * @param mobilityTool The state of the usage for the others tools to be set - If it has been used
   *        or not
   */
  void setMobilityTool(boolean mobilityTool);


  /**
   * Set the mobilitychoice.
   *
   * @param idMobility The mobility choices where the mobility came from
   */
  void setIdMobility(int idMobility);

  void setPaymentSent(boolean paymentSent);
}
