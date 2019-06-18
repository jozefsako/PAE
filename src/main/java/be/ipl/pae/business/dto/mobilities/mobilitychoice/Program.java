package be.ipl.pae.business.dto.mobilities.mobilitychoice;

import java.io.Serializable;

public enum Program implements Serializable {
  ERASMUS("ER", "Erasmus+"), ERABEL("EB", "Erabel"), FAME("FA", "FAME");

  private String programAbbreviation;
  private String program;

  Program(final String programAbbreviation, final String program) {
    this.programAbbreviation = programAbbreviation;
    this.program = program;
  }

  /**
   * Get the mobility program of the Program enum.
   * 
   * @return a String that contains the mobility program
   */
  public String getProgram() {
    return program;
  }

  /**
   * Get the mobility program abbreviation of the Program enum.
   * 
   * @return a String that contains the mobility program abbreviation
   */
  public String getProgramAbbreviation() {
    return programAbbreviation;
  }

  /**
   * Get the the complete name of the program or the opposite.
   * 
   * @param mobilityProgram It's either the abbreviation or the complete name of the mobility
   *        program
   * @return a String that contains the mobility program abbreviation ,the complete name or null if
   *         the mobilityProgram is not known
   */
  public static Program getMobilityProgram(String mobilityProgram) {
    for (Program m : Program.values()) {
      if (m.getProgramAbbreviation().equals(mobilityProgram)
          || m.getProgram().equals(mobilityProgram)) {
        return m;
      }
    }
    return null;
  }
}
