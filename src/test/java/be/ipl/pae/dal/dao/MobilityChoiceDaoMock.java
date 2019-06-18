package be.ipl.pae.dal.dao;

import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceDto;
import be.ipl.pae.business.dtos.MobilityChoiceDtoMock;
import be.ipl.pae.dal.GenericDaoMock;
import be.ipl.pae.dal.mobilitychoice.MobilityChoiceDao;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;

public class MobilityChoiceDaoMock extends GenericDaoMock<MobilityChoiceDto>
    implements MobilityChoiceDao {

  public MobilityChoiceDto findById(int id) throws DbErrorException, ClassCompatibilityException {
    super.findById(id);
    return new MobilityChoiceDtoMock();
  }

}
