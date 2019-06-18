package be.ipl.pae.business.dto.mobilities.mobilitydocument;

import be.ipl.pae.business.dto.GenericDto;

public interface MobilityDocumentDto extends GenericDto {

  /**
   * Get the mobility choices linked to the document.
   * 
   * @return a mobility mobilitychoice object
   */
  int getIdMobilityChoice();

  /**
   * Get document.
   * 
   * @return a Document enum
   */
  String getDocument();
}
