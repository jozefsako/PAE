package be.ipl.pae.business.dto.mobilities.mobilitycancellation;

import be.ipl.pae.business.dto.GenericDto;

import java.time.LocalDate;

public interface MobilityCancellationDto extends GenericDto {
  /**
   * Get the reason of the cancellation.
   * 
   * @return a String that contains reason of the cancellation
   */
  String getReason();

  /**
   * Get the date of the cancellation.
   * 
   * @return a LacalDate object that contains the date of the cancellation
   */
  LocalDate getDateCancellation();

  /**
   * Get the user that has made the cancellation.
   * 
   * @return a User object that contains the user who made the cancellation
   */
  Integer getIdUser();

  /**
   * Get the mobility cancelled.
   * 
   * @return a mobilitychoice object that contains the mobility cancelled
   */
  Integer getIdMobilityChoice();
}
