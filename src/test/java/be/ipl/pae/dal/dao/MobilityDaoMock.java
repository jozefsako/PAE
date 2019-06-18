package be.ipl.pae.dal.dao;

import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.dal.GenericDaoMock;
import be.ipl.pae.dal.mobility.MobilityDao;

import java.util.List;

public class MobilityDaoMock extends GenericDaoMock<MobilityDto> implements MobilityDao {

  private boolean findAllMobilitiesExpected;
  private boolean findAllMobilitiesCalled;

  @Override
  public List<MobilityDto> findAllMobilitiesByUser(Integer id) {
    findAllMobilitiesCalled = true;
    return null;
  }

  public void setUpFindAllByUser() {
    findAllMobilitiesExpected = true;
  }

  /**
   * Test if all the methods have been tested.
   * 
   * @return true if all the methods have been test, false if it's not the case
   */
  public boolean verify() {
    boolean verify = super.verify();

    if (findAllMobilitiesExpected != findAllMobilitiesCalled) {
      verify = false;
    }


    findAllMobilitiesExpected = false;
    findAllMobilitiesCalled = false;

    return verify;
  }

  @Override
  public List<MobilityDto> researchMobility(String yearCriteria, String stateCriteria) {
    // TODO Auto-generated method stub
    return null;
  }

}
