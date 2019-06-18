package be.ipl.pae.business.dto.organisation;

public enum TypeOrganisation {

  SCHOOL("S"), ORGANISATION("O");

  private String typeOrganisation;

  TypeOrganisation(String typeOrganisation) {
    this.typeOrganisation = typeOrganisation;
  }


  /**
   * Get the type of organisation of the partner.
   * 
   * @return a Strong that contains the the type of organisation
   */
  public String getTypeOrganisation() {
    return typeOrganisation;
  }
}
