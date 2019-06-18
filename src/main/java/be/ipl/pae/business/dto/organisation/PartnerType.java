package be.ipl.pae.business.dto.organisation;

public enum PartnerType {

  TPE("TPE"), PME("PME"), ETI("ETI"), TGE("TGE");

  private String partnerType;

  PartnerType(String partnerType) {
    this.partnerType = partnerType;
  }

  /**
   * Get the partner type.
   * 
   * @return a String that contains the type of the partner
   */
  public String getPartnerType() {
    return partnerType;
  }
}
