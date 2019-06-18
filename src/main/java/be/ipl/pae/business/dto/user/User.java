package be.ipl.pae.business.dto.user;

import java.time.LocalDate;

public interface User extends UserDto {
  /**
   * Check if the user password is equals to the one inside the User object.
   * 
   * @param password The password to be check with the one inside the user object
   */
  boolean checkPassword(String password);

  /**
   * Set or update the id of the User.
   *
   * @param idUser The id to be set
   */
  void setIdUser(Integer idUser);

  /**
   * Set or update the lastname of the user.
   * 
   * @param lastName The lastname to be set
   */
  void setLastName(String lastName);

  /**
   * Set or update the first name of the user.
   * 
   * @param firstName The first name to be set
   */
  void setFirstName(String firstName);

  /**
   * Set or update the email of the user.
   * 
   * @param email The email to be set
   */
  void setEmail(String email);

  /**
   * Set or update the password of the user.
   *
   * @param passwordUser The password to be set
   */
  void setPasswordUser(String passwordUser);

  /**
   * Set or update the user name of the user.
   * 
   * @param username The user name to be set
   */
  void setUsername(String username);

  /**
   * Set or update the user type of the user.
   * 
   * @param typeUser The user type to be set
   */
  void setTypeUser(String typeUser);

  /**
   * Set or update the registration date of the user.
   * 
   * @param registrationDate The registration date to be set
   */
  void setRegistrationDate(LocalDate registrationDate);

  /**
   * Set or update the nationality of the user.
   * 
   * @param nationality The nationality to be set
   */
  void setNationality(String nationality);

  /**
   * Set or update the title of the user.
   * 
   * @param title The title to be set
   */
  void setTitle(String title);

  /**
   * Set or update the birthdate of the user.
   *
   * @param birthdate The birthdate to be set
   */
  void setBirthdate(LocalDate birthdate);

  /**
   * Set or update the address of the user.
   *
   * @param addressUser The address to be set
   */
  void setAddressUser(String addressUser);

  /**
   * Set or update the postcode of the user.
   * 
   * @param postcode The postcode to be set
   */
  void setPostcode(String postcode);

  /**
   * Set or update the town of the user.
   * 
   * @param town The town to be set
   */
  void setTown(String town);

  /**
   * Set or update the telephone of the user.
   * 
   * @param telephone The telephone to be set
   */
  void setTelephone(String telephone);

  /**
   * Set or update the sex of the user.
   * 
   * @param sex The sex to be set
   */
  void setSex(String sex);

  /**
   * Set or update the succeeded years of the user.
   * 
   * @param succeededYears The succeeded years to be set
   */
  void setSucceededYears(Integer succeededYears);

  /**
   * Set or update the iban of the user.
   * 
   * @param iban The iban to be set
   */
  void setIban(String iban);

  /**
   * Set or update the bank holder of the user.
   * 
   * @param bankHolder The bank holder to be set
   */
  void setBankHolder(String bankHolder);

  /**
   * Set or update the bic code of the user.
   * 
   * @param bicCode The bic code to be set
   */
  void setBicCode(String bicCode);

  /**
   * Set or update the bank name of the user.
   * 
   * @param bankName The bank name to be set
   */
  void setBankName(String bankName);

  /**
   * Set or update the version number of the user.
   * 
   * @param versionNumber The version number to be set
   */
  void setVersionNumber(Integer versionNumber);
}
