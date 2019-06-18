package be.ipl.pae.business.dto.mobilities.mobilitydocument;

public interface MobilityDocument extends MobilityDocumentDto {

  /**
   * Set or update a the mobilitychoice for the MobilityDocumentDto.
   *
   * @param idMobilityChoice The mobility mobilitychoice to be set
   */
  void setIdMobilityChoice(int idMobilityChoice);

  /**
   * Set or update a the document for the MobilityDocumentDto.
   * 
   * @param document The document to be set
   */
  void setDocument(String document);
}
