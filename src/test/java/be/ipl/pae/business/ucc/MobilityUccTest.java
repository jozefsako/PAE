package be.ipl.pae.business.ucc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import be.ipl.pae.business.dtos.MobilityDtoMock;
import be.ipl.pae.business.ucc.mobilities.mobility.MobilityUcc;
import be.ipl.pae.business.ucc.mobilities.mobility.MobilityUccImpl;
import be.ipl.pae.dal.DalServicesMock;
import be.ipl.pae.dal.dao.MobilityChoiceDaoMock;
import be.ipl.pae.dal.dao.MobilityDaoMock;
import be.ipl.pae.dal.dao.MobilityDocumentDaoMock;
import be.ipl.pae.exceptions.DbDataExpiredException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.exceptions.MobilityAlreadyConfirmedException;
import be.ipl.pae.exceptions.MobilityChoiceExpiredException;
import be.ipl.pae.exceptions.TransactionErrorException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MobilityUccTest {

  private MobilityUcc mobilityUcc = new MobilityUccImpl();
  private DalServicesMock dalServices = new DalServicesMock();
  private MobilityDaoMock mobilityDao = new MobilityDaoMock();
  private MobilityChoiceDaoMock mobilityChoiceDao = new MobilityChoiceDaoMock();
  private MobilityDocumentDaoMock mobilityDocumentDao = new MobilityDocumentDaoMock();


  @BeforeEach
  void setUp() {
    mobilityUcc.setDalServices(dalServices);
    mobilityUcc.setDao(mobilityDao);
  }

  @Test
  @DisplayName("Confirm Mobility")
  void testConfirmMobilityOk() throws TransactionErrorException, InternalServerException,
      MobilityChoiceExpiredException, DbDataExpiredException, MobilityAlreadyConfirmedException {
    MobilityDtoMock mobilityDto = new MobilityDtoMock();
    mobilityDto.setIdMobility(1);
    dalServices.setUpTransactionOk();
    mobilityDao.setUpInsert();

    mobilityUcc.confirmMobility(mobilityDto);

    assertTrue(mobilityDto.verify());
    assertTrue(dalServices.verify());
    assertTrue(mobilityDao.verify());
  }

  /*
   * 
   * @Test
   * 
   * @DisplayName("Confirm Mobility : Optimistic Lock") void testConfirmMobilityLock() throws
   * TransactionErrorException, InternalServerException, MobilityChoiceExpiredException,
   * DbDataExpiredException, MobilityAlreadyConfirmedException { MobilityDtoMock mobilityDto = new
   * MobilityDtoMock(); mobilityDto.setIdMobilityChoice(1); dalServices.setUpTransactionKo();
   * mobilityDao.setUpInsert();
   * 
   * mobilityUcc.confirmMobility(mobilityDto);
   * 
   * mobilityDto.verify(); dalServices.verify(); mobilityDao.verify(); }
   * 
   */

  // findAllMobilitiesUser

  @Test
  @DisplayName("Find All Mobilities User")
  void testFindAllMobilitiesUserOk() throws TransactionErrorException, InternalServerException {
    dalServices.setUpTransactionOk();
    mobilityDao.setUpFindAllByUser();

    mobilityUcc.findAllMobilitiesByUser(1);

    assertTrue(dalServices.verify());
    assertTrue(mobilityDao.verify());
  }

}
