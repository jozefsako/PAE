package be.ipl.pae.dal.mobilitycancellation;

import be.ipl.pae.business.dto.mobilities.mobilitycancellation.MobilityCancellation;
import be.ipl.pae.business.dto.mobilities.mobilitycancellation.MobilityCancellationDto;
import be.ipl.pae.business.dto.mobilities.mobilitycancellation.MobilityCancellationImpl;
import be.ipl.pae.dal.GenericDaoImpl;

public class MobilityCancellationDaoImpl
    extends GenericDaoImpl<MobilityCancellationDto, MobilityCancellation>
    implements MobilityCancellationDao {

  public MobilityCancellationDaoImpl() {
    super(MobilityCancellationImpl::new, MobilityCancellationDto.class, MobilityCancellation.class);
  }
}
