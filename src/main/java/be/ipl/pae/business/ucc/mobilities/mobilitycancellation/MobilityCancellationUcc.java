package be.ipl.pae.business.ucc.mobilities.mobilitycancellation;/*
 * @author de Pape Alexandre
 */

import be.ipl.pae.business.dto.mobilities.mobilitycancellation.MobilityCancellationDto;
import be.ipl.pae.business.ucc.GenericUcc;
import be.ipl.pae.dal.mobilitycancellation.MobilityCancellationDao;

public interface MobilityCancellationUcc extends
    GenericUcc<MobilityCancellationDao, MobilityCancellationDto> {

}
