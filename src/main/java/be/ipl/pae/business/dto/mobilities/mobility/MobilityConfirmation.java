package be.ipl.pae.business.dto.mobilities.mobility;

public enum MobilityConfirmation {
  TEACHER("T", "Teacher"), STUDENT("S", "Student");

  private String mobilityConfirmation;
  private String mobilityConfirmationFull;

  MobilityConfirmation(String mobilityConfirmaiton, String mobilityConfirmationFull) {
    this.mobilityConfirmation = mobilityConfirmaiton;
    this.mobilityConfirmationFull = mobilityConfirmationFull;
  }

  /**
   * Get the type of person who confirmed the mobility.
   * 
   * @return a String that contains the type of person who confirmed the mobilitychoice of mobility
   */
  public String getMobilityConfirmaiton() {
    return mobilityConfirmation;
  }

  /**
   * Get the type of person who confirmed the mobility.
   *
   * @return a String that contains the type of person who confirmed the mobilityChoice of mobility
   */
  public String getMobilityConfirmationFull() {
    return mobilityConfirmationFull;
  }

  /**
   * Give the full string for the uppercase enum.
   * 
   * @param mobilityConfirmation The uppercase that matches the from the enum
   * @return the corresponding string from the uppercase
   */
  public static MobilityConfirmation getMobilityConfirmation(String mobilityConfirmation) {
    for (MobilityConfirmation mc : MobilityConfirmation.values()) {
      if (mc.getMobilityConfirmaiton().equals(mobilityConfirmation)
          || mc.getMobilityConfirmationFull().equals(mobilityConfirmation)) {
        return mc;
      }
    }
    return null;
  }
}
