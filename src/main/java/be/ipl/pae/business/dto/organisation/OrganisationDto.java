package be.ipl.pae.business.dto.organisation;

import be.ipl.pae.business.dto.GenericDto;

public interface OrganisationDto extends GenericDto {

  /**
   * Get the id of the partner.
   *
   * @return the idOrganisation
   */
  Integer getIdOrganisation();

  /**
   * Get the legal name of the partner.
   *
   * @return the legal name
   */
  String getLegalName();

  /**
   * Get the business name of the partner.
   *
   * @return the business name
   */
  String getBusinessName();

  /**
   * Get the full legal name of the partner.
   *
   * @return the full legal name of the partner
   */
  String getFullLegalName();

  /**
   * Get the department of the partner.
   *
   * @return department of the partner
   */
  String getDepartement();

  /**
   * Get the type of the partner.
   *
   * @return the type of the partner
   */
  String getTypeOrganisation();

  /**
   * Get the number of employees.
   *
   * @return the number of employees
   */
  Integer getNumberOfEmployees();

  /**
   * Get the email of the partner.
   *
   * @return the email
   */
  String getEmail();

  /**
   * Get the website of the partner.
   *
   * @return the website of the partner
   */
  String getWebSite();

  /**
   * Get the legal address of the partner.
   *
   * @return the legal address of the partner
   */
  String getLegalAddress();

  /**
   * Get the country of the partner.
   *
   * @return the country of the partner
   */
  String getCountry();

  /**
   * Get the region of the partner.
   *
   * @return the region of the partner
   */
  String getRegion();

  /**
   * Get the city of the partner.
   *
   * @return the city of the partner
   */
  String getCity();


  /**
   * Get the poBox of the partner.
   *
   * @return the poBox of the partner
   */
  String getPoBox();

  /**
   * Get the postCode of the partner.
   *
   * @return the postCode of the partner
   */
  String getPostCode();

  /**
   * Get the cedex of the partner.
   *
   * @return the cedex of the partner
   */
  String getCedex();

  /**
   * Get the first telephone number of the partner.
   *
   * @return the first telephone number of the partner
   */
  String getTelephone1();

  /**
   * Get the second telephone number of the partner.
   *
   * @return the second telephone number of the partner
   */
  String getTelephone2();

  /**
   * Get the fax number of the partner.
   *
   * @return the fax number of the partner
   */
  String getFax();

  /**
   * Get the comment in relation with the partner.
   *
   * @return the comment in relation with the partner
   */
  String getCommentOrga();

  /**
   * Get the public character of the partner.
   *
   * @return the public character of the partner
   */
  boolean getPublicBody();

  /**
   * Get the non profit character of the partner.
   *
   * @return the non profit character of the partner
   */
  boolean getNonProfit();

  /**
   * Get the type of organisation of the partner.
   *
   * @return the type of organisation of the partner
   */
  String getOrganisationCode();
}
