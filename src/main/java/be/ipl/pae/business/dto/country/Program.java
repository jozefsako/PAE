package be.ipl.pae.business.dto.country;

public enum Program {
  ERASMUS("ER"), ERABEL("EB"), FAME("FA");


  private final String program;

  Program(String program) {
    this.program = program;
  }

  /**
   * Return the program.
   * 
   * @return a String object that contains the program
   */
  public String getProgram() {
    return program;
  }
}
