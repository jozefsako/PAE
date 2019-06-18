package be.ipl.pae.business.dto.user;

import be.ipl.pae.business.dto.GenericDto;

import java.time.LocalDate;

public interface UserDto extends GenericDto {

  /**
   * Get the id of the user.
   * 
   * @return the id of the user
   */
  Integer getIdUser();

  /**
   * Get the last name of the user.
   * 
   * @return the last name of the user
   */
  String getLastName();

  /**
   * Get the first name of the user.
   * 
   * @return the first name of the user
   */
  String getFirstName();

  /**
   * Get the email of the user.
   * 
   * @return the email of the user
   */
  String getEmail();

  /**
   * Get the password of the user.
   * 
   * @return the password of the user
   */
  String getPasswordUser();

  /**
   * Get the user name (pseudo) of the user.
   * 
   * @return the user name of the user
   */
  String getUsername();

  /**
   * Get the type of the user.
   * 
   * @return the type of the user
   */
  String getTypeUser();

  /**
   * Get the date of the registration.
   * 
   * @return the date of the registration
   */
  LocalDate getRegistrationDate();

  /**
   * Get the nationality of the user.
   * 
   * @return the nationality of the user
   */
  String getNationality();

  /**
   * Get the title of the user.
   * 
   * @return the title of the user
   */
  String getTitle();

  /**
   * Get the birth date of the user.
   * 
   * @return the birth date of the user
   */
  LocalDate getBirthdate();

  /**
   * Get the address of the user.
   * 
   * @return the address of the user
   */
  String getAddressUser();

  /**
   * Get the post code of the user.
   * 
   * @return the post code of the user
   */
  String getPostcode();

  /**
   * Get the town of the user.
   * 
   * @return the town of the user
   */
  String getTown();

  /**
   * Get the telephone of the user.
   * 
   * @return the town of the user
   */
  String getTelephone();

  /**
   * Get the sex of the user.
   * 
   * @return the sex of the user
   */
  String getSex();

  /**
   * Get the succeeded years of the user.
   * 
   * @return the succeeded years of the user
   */
  Integer getSucceededYears();

  /**
   * Get the iban of the user.
   * 
   * @return the iban of the user
   */
  String getIban();

  /**
   * Get the bank holder of the user.
   * 
   * @return the bank holder of the user
   */
  String getBankHolder();

  /**
   * Get the bic code of the user.
   * 
   * @return the bic code of the user
   */
  String getBicCode();

  /**
   * Get the bank name of the user.
   * 
   * @return the bank name of the user
   */
  String getBankName();

  /**
   * Get the version number of the user.
   * 
   * @return the version number of the user
   */
  Integer getVersionNumber();
}
