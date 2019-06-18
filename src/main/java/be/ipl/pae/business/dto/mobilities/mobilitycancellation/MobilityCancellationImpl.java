package be.ipl.pae.business.dto.mobilities.mobilitycancellation;

import be.ipl.pae.business.dto.GenericDtoImpl;

import java.time.LocalDate;

public class MobilityCancellationImpl extends GenericDtoImpl
    implements MobilityCancellation, MobilityCancellationDto {

  private String reason;
  private LocalDate dateCancellation;
  private Integer idUser;
  private Integer idMobilityChoice;

  @Override
  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public void setDateCancellation(LocalDate date) {
    this.dateCancellation = date;
  }

  @Override
  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  @Override
  public void setIdMobilityChoice(Integer idMobilityChoice) {
    this.idMobilityChoice = idMobilityChoice;
  }

  @Override
  public String getReason() {
    return this.reason;
  }

  @Override
  public LocalDate getDateCancellation() {
    return dateCancellation;
  }

  @Override
  public Integer getIdUser() {
    return idUser;
  }

  @Override
  public Integer getIdMobilityChoice() {
    return idMobilityChoice;
  }
}
