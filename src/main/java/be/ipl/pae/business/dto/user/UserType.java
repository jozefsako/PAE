package be.ipl.pae.business.dto.user;

public enum UserType {

  TEACHER("T"),
  STUDENT("S"),
  RESPONSIBLE("R");

  private String userType;

  UserType(String userType) {
    this.userType = userType;
  }

  public String getUserType() {
    return userType;
  }
}
