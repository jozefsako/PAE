package be.ipl.pae.business.dto.organisation;

public interface Organisation  extends OrganisationDto {

  /**
   * Set or update the id of the partner.
   *
   * @param idOrganisation The id to be set
   */
  void setIdOrganisation(Integer idOrganisation);

  /**
   * Set or update the legal name of the partner.
   *
   * @param legalName The legal name to be set
   */
  void setLegalName(String legalName);

  /**
   * Set or update the business name of the partner.
   *
   * @param businessName The business name to be set
   */
  void setBusinessName(String businessName);

  /**
   * Set or update the full legal name of the partner.
   *
   * @param fullLegalName The full legal name to be set
   */
  void setFullLegalName(String fullLegalName);

  /**
   * Set or update the department of the partner.
   *
   * @param departement The department to be set
   */
  void setDepartement(String departement);

  /**
   * Set or update type of the partner.
   *
   * @param typeOrganisation The type of the partner to be set
   */
  void setTypeOrganisation(String typeOrganisation);

  /**
   * Set or update the number of employees.
   *
   * @param numberOfEmployees The number of employees to be set
   */
  void setNumberOfEmployees(Integer numberOfEmployees);

  /**
   * Set or update the mail of the partner.
   *
   * @param email The mail to be set
   */
  void setEmail(String email);

  /**
   * Set or update the website of the partner.
   *
   * @param webSite The website to be set
   */
  void setWebSite(String webSite);

  /**
   * Set or update the legal address of the partner.
   *
   * @param legalAddress The legal address to be set
   */
  void setLegalAddress(String legalAddress);

  /**
   * Set or update the country of the partner.
   *
   * @param country The country to be set
   */
  void setCountry(String country);

  /**
   * Set or update the region of the partner.
   *
   * @param region The region to be set
   */
  void setRegion(String region);

  /**
   * Set or update the city of the partner.
   *
   * @param city The city to be set
   */
  void setCity(String city);

  /**
   * Set or update the pobox of the partner.
   *
   * @param poBox The pobox to be set
   */
  void setPoBox(String poBox);

  /**
   * Set or update the postCode of the partner.
   *
   * @param postCode The postCode to be set
   */
  void setPostCode(String postCode);

  /**
   * Set or update the cedex of the partner.
   *
   * @param cedex The cedex to be set
   */
  void setCedex(String cedex);

  /**
   * Set or update the first telephone number of the partner.
   *
   * @param telephone1 The first telephone number to be set
   */
  void setTelephone1(String telephone1);

  /**
   * Set or update the second telephone number of the partner.
   *
   * @param telephone2 The second telephone number to be set
   */
  void setTelephone2(String telephone2);

  /**
   * Set or update the fax number of the partner.
   *
   * @param fax The fax number to be set
   */
  void setFax(String fax);

  /**
   * Set or update a comment in relation to the partner.
   *
   * @param commentOrga The comment to be set
   */
  void setCommentOrga(String commentOrga);

  /**
   * Set or update the public character of the partner.
   *
   * @param publicBody The public character to be set
   */
  void setPublicBody(boolean publicBody);

  /**
   * Set or update the non profit character of the partner.
   *
   * @param nonProfit The non profit character to be set
   */
  void setNonProfit(boolean nonProfit);

  /**
   * Set or update the type of organisation of the partner.
   *
   * @param organisation The type of organisation to be set
   */
  void setOrganisationCode(String organisation);
}
