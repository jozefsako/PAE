package be.ipl.pae.business.dto.mobilities.mobilitydocument;

import be.ipl.pae.business.dto.GenericDtoImpl;

public class MobilityDocumentImpl extends GenericDtoImpl
    implements MobilityDocument, MobilityDocumentDto {

  private String document;

  public MobilityDocumentImpl() {
  }

  @Override
  public void setIdMobilityChoice(int idMobilityChoice) {
    this.id = idMobilityChoice;
  }

  @Override
  public void setDocument(String document) {
    this.document = document;
  }

  @Override
  public int getIdMobilityChoice() {
    return id;
  }

  @Override
  public String getDocument() {
    return document;
  }

}
