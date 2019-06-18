package be.ipl.pae.business.ucc.mobilities.mobility;

import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.business.ucc.GenericUcc;
import be.ipl.pae.dal.mobility.MobilityDao;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.MobilityAlreadyConfirmedException;
import be.ipl.pae.exceptions.MobilityChoiceExpiredException;
import be.ipl.pae.exceptions.TransactionErrorException;

import java.util.List;

public interface MobilityUcc extends GenericUcc<MobilityDao, MobilityDto> {

  /**
   * The method connect itself to the database via a DalServices object. Afterwards, it confirm the
   * mobility .
   *
   * @param mobilityDto The unique identifier of the mobility
   * @throws DbErrorException the database has an issue to retrieve the data
   */
  void confirmMobility(MobilityDto mobilityDto)
      throws TransactionErrorException, InternalServerException, MobilityChoiceExpiredException,
      DbDataExpiredException, MobilityAlreadyConfirmedException;

  /**
   * Find all mobilities by user.
   * 
   * @param id The id of the user
   * 
   * @return The list of the mobilities that were found
   * @throws TransactionErrorException When an error occur during the transaction
   * @throws InternalServerException if an error occur during the retrieval of the data into the
   *         database
   */
  List<MobilityDto> findAllMobilitiesByUser(Integer id)
      throws InternalServerException, TransactionErrorException;

  /**
   * Research a mobility with certain criteria.
   * 
   * @param yearCriteria The year of the mobilities searched
   * @param stateCriteria The state of the mobilities searched
   * 
   * @return The list of the mobilities that were found
   * @throws InternalServerException if an error occur during the retrieval of the data into the
   *         database
   * @throws TransactionErrorException When an error occur during the transaction
   */
  List<MobilityDto> researchMobility(String yearCriteria, String stateCriteria)
      throws InternalServerException, TransactionErrorException;
}
