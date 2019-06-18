package be.ipl.pae.dal.mobility;

import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.dal.GenericDao;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;

import java.util.List;

public interface MobilityDao extends GenericDao<MobilityDto> {

  /**
   * Find all mobilities by user.
   * 
   * @param id The id of the user
   * @return The list of the mobilities that were found
   * @throws InternalServerException if an error occur during the retrieval of the data into the
   *         database
   * @throws DbErrorException If an error occur into the database
   */
  List<MobilityDto> findAllMobilitiesByUser(Integer id)
      throws InternalServerException, DbErrorException;

  /**
   * Research a mobility with certain criteria.
   * 
   * @param yearCriteria The year of the mobilities searched
   * @param stateCriteria The state of the mobilities searched
   * @return The list of the mobilities that were found
   * @throws InternalServerException if an error occur during the retrieval of the data into the
   *         database
   * @throws DbErrorException If an error occur into the database
   */
  List<MobilityDto> researchMobility(String yearCriteria, String stateCriteria)
      throws InternalServerException, DbErrorException;
}
