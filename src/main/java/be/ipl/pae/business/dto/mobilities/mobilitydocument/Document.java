package be.ipl.pae.business.dto.mobilities.mobilitydocument;

public enum Document {
  SCHOLARSHIP("SS"),
  INTERNSHIP_OR_SCHOOL_AGREEMENT("ASI"),
  STUDENT_CHARTER("SCH"),
  LANGUAGE_TEST_DEPARTURE("LTD"),
  COMMITMENT_DOCS("CD"),
  CERTIFICATE_OF_RESIDENCE("RP"),
  CERTIFICATE_OF_INTERNSHIP("CI"),
  TRANSCRIPT_OF_NOTES("TN"),
  FINAL_REPORT("FR"),
  LANGUAGE_TEST_RETURN("LTR");

  private String document;

  Document(String document) {
    this.document = document;
  }

  /**
   * Get the the abbreviation name of the Document.
   * 
   * @return a String that contains the name of the document
   */
  public String getDocument() {
    return document;
  }
}
