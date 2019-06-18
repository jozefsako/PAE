package be.ipl.pae.business.dto.organisation;

import be.ipl.pae.business.dto.GenericDtoImpl;

import java.io.Serializable;

public class OrganisationImpl extends GenericDtoImpl
    implements Organisation, OrganisationDto, Serializable {

  private String legalName;
  private String businessName;
  private String fullLegalName;
  private String departement;
  private String typeOrganisation;
  private Integer numberOfEmployees;
  private String email;
  private String webSite;
  private String legalAddress;
  private String country;
  private String region;
  private String city;
  private String poBox;
  private String postCode;
  private String cedex;
  private String telephone1;
  private String telephone2;
  private String fax;
  private String commentOrga;
  private boolean publicBody;
  private boolean nonProfit;
  private String organisationCode;

  /**
   * Constructor of OrganisationImpl without parameters.
   */
  public OrganisationImpl() {}

  /**
   * Constructor of OrganisationImpl with parameters.
   * 
   * @param legalName Legal name of the organisation
   * @param businessName Business name of the organisation
   * @param fullLegalName Full legal name of the organisation
   * @param departement Departement of the organisation
   * @param typeOrganisation Type of the organisation
   * @param numberOfEmployees The number of employees of the organisation
   * @param email The email of the organisation
   * @param webSite The website of the organisation
   * @param legalAddress The legal address of the organisation
   * @param country The country of the organisation
   * @param region The region of the organisation
   * @param city The city of the organisation
   * @param poBox The post office code of the organisation
   * @param postCode The post code of the organisation
   * @param cedex The cedex of the organisation
   * @param telephone1 The first phone number of the organisation
   * @param telephone2 The second phone number of the organisation
   * @param fax The fax number of the organisation
   * @param commentOrga A comment on the organisation
   * @param publicBody If it's a public organisation or not
   * @param nonProfit If it's a non-profit organisation
   * @param organisationCode If it's a school or a company
   */
  public OrganisationImpl(String legalName, String businessName, String fullLegalName,
      String departement, String typeOrganisation, Integer numberOfEmployees, String email,
      String webSite, String legalAddress, String country, String region, String city, String poBox,
      String postCode, String cedex, String telephone1, String telephone2, String fax,
      String commentOrga, boolean publicBody, boolean nonProfit, String organisationCode) {
    super();
    this.legalName = legalName;
    this.businessName = businessName;
    this.fullLegalName = fullLegalName;
    this.departement = departement;
    this.typeOrganisation = typeOrganisation;
    this.numberOfEmployees = numberOfEmployees;
    this.email = email;
    this.webSite = webSite;
    this.legalAddress = legalAddress;
    this.country = country;
    this.region = region;
    this.city = city;
    this.poBox = poBox;
    this.postCode = postCode;
    this.cedex = cedex;
    this.telephone1 = telephone1;
    this.telephone2 = telephone2;
    this.fax = fax;
    this.commentOrga = commentOrga;
    this.publicBody = publicBody;
    this.nonProfit = nonProfit;
    this.organisationCode = organisationCode;
  }

  @Override
  public void setIdOrganisation(Integer idOrganisation) {
    id = idOrganisation;
  }

  @Override
  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }

  @Override
  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  @Override
  public void setFullLegalName(String fullLegalName) {
    this.fullLegalName = fullLegalName;
  }

  @Override
  public void setDepartement(String departement) {
    this.departement = departement;
  }

  @Override
  public void setTypeOrganisation(String typeOrganisation) {
    this.typeOrganisation = typeOrganisation;
  }

  @Override
  public void setNumberOfEmployees(Integer numberOfEmployees) {
    this.numberOfEmployees = numberOfEmployees;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  @Override
  public void setLegalAddress(String legalAddress) {
    this.legalAddress = legalAddress;
  }

  @Override
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public void setRegion(String region) {
    this.region = region;
  }

  @Override
  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public void setPoBox(String poBox) {
    this.poBox = poBox;
  }

  @Override
  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  @Override
  public void setCedex(String cedex) {
    this.cedex = cedex;
  }

  @Override
  public void setTelephone1(String telephone1) {
    this.telephone1 = telephone1;
  }

  @Override
  public void setTelephone2(String telephone2) {
    this.telephone2 = telephone2;
  }

  @Override
  public void setFax(String fax) {
    this.fax = fax;
  }

  @Override
  public void setCommentOrga(String commentOrga) {
    this.commentOrga = commentOrga;
  }

  @Override
  public void setPublicBody(boolean publicBody) {
    this.publicBody = publicBody;
  }

  @Override
  public void setNonProfit(boolean nonProfit) {
    this.nonProfit = nonProfit;
  }

  @Override
  public void setOrganisationCode(String organisationCode) {
    this.organisationCode = organisationCode;
  }

  @Override
  public Integer getIdOrganisation() {
    return id;
  }

  @Override
  public String getLegalName() {
    return legalName;
  }

  @Override
  public String getBusinessName() {
    return businessName;
  }

  @Override
  public String getFullLegalName() {
    return fullLegalName;
  }

  @Override
  public String getDepartement() {
    return departement;
  }

  @Override
  public String getTypeOrganisation() {
    return typeOrganisation;
  }

  @Override
  public Integer getNumberOfEmployees() {
    return numberOfEmployees;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getWebSite() {
    return webSite;
  }

  @Override
  public String getLegalAddress() {
    return legalAddress;
  }

  @Override
  public String getCountry() {
    return country;
  }

  @Override
  public String getRegion() {
    return region;
  }

  @Override
  public String getCity() {
    return city;
  }

  @Override
  public String getPoBox() {
    return poBox;
  }

  @Override
  public String getPostCode() {
    return postCode;
  }

  @Override
  public String getCedex() {
    return cedex;
  }

  @Override
  public String getTelephone1() {
    return telephone1;
  }

  @Override
  public String getTelephone2() {
    return telephone2;
  }

  @Override
  public String getFax() {
    return fax;
  }

  @Override
  public String getCommentOrga() {
    return commentOrga;
  }

  @Override
  public boolean getPublicBody() {
    return publicBody;
  }

  @Override
  public boolean getNonProfit() {
    return nonProfit;
  }

  @Override
  public String getOrganisationCode() {
    return this.organisationCode;
  }

  @Override
  public String toString() {
    return "OrganisationImpl [legalName=" + legalName + ", businessName=" + businessName
        + ", fullLegalName=" + fullLegalName + ", departement=" + departement
        + ", typeOrganisation=" + typeOrganisation + ", numberOfEmployees=" + numberOfEmployees
        + ", email=" + email + ", webSite=" + webSite + ", legalAddress=" + legalAddress
        + ", country=" + country + ", region=" + region + ", city=" + city + ", poBox=" + poBox
        + ", postCode=" + postCode + ", cedex=" + cedex + ", telephone1=" + telephone1
        + ", telephone2=" + telephone2 + ", fax=" + fax + ", commentOrga=" + commentOrga
        + ", publicBody=" + publicBody + ", nonProfit=" + nonProfit + ", organisationCode="
        + organisationCode + "]";
  }


}
