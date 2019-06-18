package be.ipl.pae.business.dto.mobilities.mobility;

import be.ipl.pae.business.dto.GenericDto;

public interface MobilityDto extends GenericDto {

  /**
   * Get the mobility State of a MobilityDto Object.
   *
   * @return MobilityState Object that contains the state of a MobilityDto object
   */
  String getMobilityState();

  /**
   * Get the MobilityConfirmation of a MobilityDto Object.
   *
   * @return MobilityConfirmation Object that contains the state of the confirmation of a
   *         MobilityDto object
   */
  String getMobilityConfirmation();

  /**
   * Get the state of the usage of the pro eco software.
   *
   * @return a boolean that contains the state of the sofware usage
   */
  boolean getProEco();

  /**
   * Get the MobilityTool object.
   *
   * @return The state of the usage for the others tools
   */
  boolean getMobilityTool();


  /**
   * Get the mobilitychoice id.
   *
   * @return The mobility mobilitychoice id where the mobility came from
   */
  int getIdMobility();

  Integer getVersionNumber();

  boolean getPaymentSent();
}
