package be.ipl.pae.business.dto.mobilities.mobilitychoice;

import be.ipl.pae.business.dto.GenericDtoImpl;

public class MobilityChoiceImpl extends GenericDtoImpl
    implements MobilityChoice, MobilityChoiceDto {

  private Integer preferenceOrder;
  private String country;
  private Integer academicYear;
  private Integer semester;
  private Integer idUser;
  private Integer partner;
  private String mobilityType;
  private String program;
  private Integer versionNumber;

  @Override
  public void setProgram(String program) {
    if (Program.getMobilityProgram(program) != null) {
      this.program = program;
    }
  }

  @Override
  public void setVersionNumber(Integer versionNumber) {
    this.versionNumber = versionNumber;
  }

  @Override
  public void setIdMobilityChoice(Integer id) {
    this.id = id;
  }

  @Override
  public void setPreferenceOrder(Integer order) {
    this.preferenceOrder = order;
  }

  @Override
  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public void setAcademicYear(Integer year) {
    this.academicYear = year;
  }

  @Override
  public void setSemester(Integer semester) {
    this.semester = semester;
  }

  @Override
  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  @Override
  public void setPartner(Integer partner) {
    this.partner = partner;
  }

  @Override
  public void setMobilityType(String mobilityType) {
    this.mobilityType = mobilityType;
  }

  @Override
  public Integer getIdMobilityChoice() {
    return this.id;
  }

  @Override
  public Integer getPreferenceOrder() {
    return this.preferenceOrder;
  }

  @Override
  public String getCountry() {
    return this.country;
  }

  @Override
  public Integer getAcademicYear() {
    return this.academicYear;
  }

  @Override
  public Integer getSemester() {
    return this.semester;
  }

  @Override
  public Integer getIdUser() {
    return this.idUser;
  }

  @Override
  public Integer getPartner() {
    return this.partner;
  }

  @Override
  public String getMobilityType() {
    return this.mobilityType;
  }

  @Override
  public String getProgram() {
    return program;
  }

  @Override
  public Integer getVersionNumber() {
    return versionNumber;
  }


  @Override
  public String toString() {
    return "MobilityChoiceImpl{" + "idMobilityChoice=" + id + ", preferenceOrder=" + preferenceOrder
        + ", country='" + country + '\'' + ", academicYear=" + academicYear + ", semester="
        + semester + ", idUser=" + idUser + ", partner='" + partner + '\'' + ", mobilityType="
        + mobilityType + ", program=" + program + '}';
  }
}
