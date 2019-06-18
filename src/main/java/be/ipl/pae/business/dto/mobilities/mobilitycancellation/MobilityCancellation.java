package be.ipl.pae.business.dto.mobilities.mobilitycancellation;

import java.time.LocalDate;

public interface MobilityCancellation extends MobilityCancellationDto {

  /**
   * Set or update the reason of the cancellation.
   * 
   * @param reason The reason to be set
   */
  void setReason(String reason);

  /**
   * Set or update the date of the cancellation.
   * 
   * @param date The date to be set
   */
  void setDateCancellation(LocalDate date);

  /**
   * Set or update the idUser that has made the cancellation.
   *
   * @param idUser The idUser to be set
   */
  void setIdUser(Integer idUser);

  /**
   * Set or update the mobility that is cancelled.
   *
   * @param idMobilityChoice idMobilityChoice to be set
   */
  void setIdMobilityChoice(Integer idMobilityChoice);


}
