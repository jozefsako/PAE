package be.ipl.pae.business.dto.mobilities.mobility;

public enum MobilityTool {

  MOBILITY_TOOL("MT"), MOBI("S");

  private String tool;

  MobilityTool(String tool) {
    this.tool = tool;
  }

  /**
   * Get the tool that has been used.
   * 
   * @return a String that contains the tool that has been used
   */
  public String getTool() {
    return tool;
  }
}
