package be.ipl.pae.business.dto.user;

import be.ipl.pae.business.dto.GenericDtoImpl;

import org.mindrot.bcrypt.BCrypt;

import java.time.LocalDate;

public class UserImpl extends GenericDtoImpl implements UserDto, User {

  private Integer succeededYears;
  private Integer versionNumber;
  private String lastname;
  private String firstname;
  private String email;
  private String password;
  private String username;
  private String type;
  private String nationality;
  private String title;
  private String address;
  private String postcode;
  private String town;
  private String telephone;
  private String sex;
  private String iban;
  private String bankHolder;
  private String bankName;
  private String bicCode;
  private LocalDate birthdate;
  private LocalDate registration;

  @Override
  public boolean checkPassword(String password) {
    return BCrypt.checkpw(password, this.password);
  }

  @Override
  public void setIdUser(Integer idUser) {
    id = idUser;
  }

  @Override
  public void setLastName(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public void setFirstName(String firstname) {
    this.firstname = firstname;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public void setPasswordUser(String passwordUser) {
    this.password = passwordUser;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public void setTypeUser(String typeUser) {
    this.type = typeUser;
  }

  @Override
  public void setRegistrationDate(LocalDate registrationDate) {
    this.registration = registrationDate;
  }

  @Override
  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }


  @Override
  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  @Override
  public void setAddressUser(String addressUser) {
    this.address = addressUser;
  }

  @Override
  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  @Override
  public void setTown(String town) {
    this.town = town;
  }

  @Override
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Override
  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public void setSucceededYears(Integer succeededYears) {
    this.succeededYears = succeededYears;
  }

  @Override
  public void setIban(String iban) {
    this.iban = iban;
  }

  @Override
  public void setBankHolder(String bankHolder) {
    this.bankHolder = bankHolder;
  }

  @Override
  public void setBicCode(String bicCode) {
    this.bicCode = bicCode;
  }

  @Override
  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  @Override
  public void setVersionNumber(Integer versionNumber) {
    this.versionNumber = versionNumber;
  }

  @Override
  public Integer getIdUser() {
    return id;
  }

  @Override
  public String getLastName() {
    return lastname;
  }

  @Override
  public String getFirstName() {
    return firstname;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getPasswordUser() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getTypeUser() {
    return type;
  }

  @Override
  public LocalDate getRegistrationDate() {
    return registration;
  }

  @Override
  public String getNationality() {
    return nationality;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LocalDate getBirthdate() {
    return birthdate;
  }

  @Override
  public String getAddressUser() {
    return address;
  }

  @Override
  public String getPostcode() {
    return postcode;
  }

  @Override
  public String getTown() {
    return town;
  }

  @Override
  public String getTelephone() {
    return telephone;
  }

  @Override
  public String getSex() {
    return sex;
  }

  @Override
  public Integer getSucceededYears() {
    return succeededYears;
  }

  @Override
  public String getIban() {
    return iban;
  }

  @Override
  public String getBankHolder() {
    return bankHolder;
  }

  @Override
  public String getBicCode() {
    return bicCode;
  }

  @Override
  public String getBankName() {
    return bankName;
  }

  @Override
  public Integer getVersionNumber() {
    return this.versionNumber;
  }
}
