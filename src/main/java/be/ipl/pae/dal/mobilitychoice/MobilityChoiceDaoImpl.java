package be.ipl.pae.dal.mobilitychoice;

import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoice;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceDto;
import be.ipl.pae.business.dto.mobilities.mobilitychoice.MobilityChoiceImpl;
import be.ipl.pae.dal.GenericDaoImpl;

public class MobilityChoiceDaoImpl extends
    GenericDaoImpl<MobilityChoiceDto, MobilityChoice> implements MobilityChoiceDao {

  public MobilityChoiceDaoImpl() {
    super(MobilityChoiceImpl::new, MobilityChoiceDto.class, MobilityChoice.class);
  }

}
