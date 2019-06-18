package be.ipl.pae.business.dto.mobilities.mobility;

public enum MobilityState {

  CREATED("cr", "Created"), IN_PREPARATION("ipp", "In Preparation"), TO_PAY("tp",
      "To Pay"), IN_PROGRESS("ip",
          "In Progress"), TO_PAY_SOLD("tps", "To Pay Sold"), COMPLETED("co", "Completed");

  private String state;
  private String fullState;

  MobilityState(String state, String fullState) {
    this.state = state;
    this.fullState = fullState;
  }

  /**
   * Get the state of the mobility.
   *
   * @return a String that contains the state of the mobility
   */
  public String getState() {
    return state;
  }

  /**
   * Give the full string for the uppercase enum.
   *
   * @param state The uppercase that matches the from the enum
   * @return the corresponding string from the uppercase
   */
  public MobilityState getState(String state) {
    for (MobilityState s : MobilityState.values()) {
      if (s.getState().equals(state) || s.getFullState().equals(state)) {
        return s;
      }
    }
    return null;
  }

  /**
   * Get the fullstate of the mobility.
   *
   * @return a String that contains the fullstate of the mobility
   */
  public String getFullState() {
    return fullState;
  }


}
