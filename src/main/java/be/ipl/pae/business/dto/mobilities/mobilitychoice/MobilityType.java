package be.ipl.pae.business.dto.mobilities.mobilitychoice;

import java.io.Serializable;

public enum MobilityType implements Serializable {

  SMS("SMS"), SMP("SMP");

  private String mobilityType;

  MobilityType(String mobilityType) {
    this.mobilityType = mobilityType;
  }

  /**
   * Get the mobility type of the MobilityType.
   * 
   * @return a String that contains the mobility type
   */
  public String getMobilityType() {
    return mobilityType;
  }

  /**
   * Get the the abbreviation name of the MobilityType.
   * 
   * @param mobilityType It's the complete name of the mobility program
   * @return a String that contains the mobility type or null if the MobilityType is unknown
   */
  public static MobilityType getMobilityType(String mobilityType) {
    for (MobilityType m : MobilityType.values()) {
      if (m.getMobilityType().equals(mobilityType)) {
        return m;
      }
    }
    return null;
  }
}
