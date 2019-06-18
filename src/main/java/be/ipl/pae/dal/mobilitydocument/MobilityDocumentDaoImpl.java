package be.ipl.pae.dal.mobilitydocument;

import be.ipl.pae.business.dto.mobilities.mobilitydocument.MobilityDocument;
import be.ipl.pae.business.dto.mobilities.mobilitydocument.MobilityDocumentDto;
import be.ipl.pae.business.dto.mobilities.mobilitydocument.MobilityDocumentImpl;
import be.ipl.pae.dal.GenericDaoImpl;


public class MobilityDocumentDaoImpl extends
    GenericDaoImpl<MobilityDocumentDto, MobilityDocument> implements MobilityDocumentDao {

  public MobilityDocumentDaoImpl() {
    super(MobilityDocumentImpl::new, MobilityDocumentDto.class, MobilityDocument.class);
  }
}
