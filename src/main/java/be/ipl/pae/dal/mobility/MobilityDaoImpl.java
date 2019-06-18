package be.ipl.pae.dal.mobility;

import be.ipl.pae.business.dto.mobilities.mobility.Mobility;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityImpl;
import be.ipl.pae.dal.GenericDaoImpl;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.InternalServerException;
import be.ipl.pae.util.MyLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MobilityDaoImpl extends GenericDaoImpl<MobilityDto, Mobility> implements MobilityDao {

  public MobilityDaoImpl() {
    super(MobilityImpl::new, MobilityDto.class, Mobility.class);
  }

  @Override
  public List<MobilityDto> findAllMobilitiesByUser(Integer id)
      throws InternalServerException, DbErrorException {
    List<MobilityDto> list = new ArrayList<>();
    String query = "SELECT * FROM pae.mobilities mo WHERE mo.id_mobility IN "
        + "(SELECT mc.id_mobility_choice FROM pae.mobility_choices mc WHERE mc.id_user = ?)";
    try {
      PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
      ps.setInt(1, id);
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        MobilityDto mobilityDto = new MobilityImpl();
        fillDto(mobilityDto, resultSet);
        list.add(mobilityDto);
      }
    } catch (ClassCompatibilityException err) {
      throw new InternalServerException(err.getMessage());
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
    return list;
  }

  @Override
  public List<MobilityDto> researchMobility(String yearCriteria, String stateCriteria)
      throws InternalServerException, DbErrorException {
    List<MobilityDto> list = new ArrayList<>();

    String query = "SELECT m.id_mobility, m.mobility_state, m.mobility_confirmation, m.pro_eco"
        + ", m.version_number,"
        + " m.mobility_tool, m.payment_sent FROM pae.mobilities m, pae.mobility_choices"
        + " mc WHERE m.id_mobility "
        + "= mc.id_mobility_choice AND (m.mobility_state = (?) OR (?) = '') AND "
        + "(mc.academic_year = (?) OR " + "(?) = '0')";
    try {
      PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
      ps.setString(1, stateCriteria);
      ps.setString(2, stateCriteria);
      if (yearCriteria.isEmpty()) {
        ps.setInt(3, 0);
        ps.setInt(4, 0);
      } else {
        ps.setInt(3, Integer.valueOf(yearCriteria));
        ps.setInt(4, Integer.valueOf(yearCriteria));

      }
      ResultSet resultSet = ps.executeQuery();

      while (resultSet.next()) {
        MobilityDto mobilityDto = new MobilityImpl();
        fillDto(mobilityDto, resultSet);
        list.add(mobilityDto);
      }
    } catch (ClassCompatibilityException err) {
      throw new InternalServerException(err.getMessage());
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
    return list;
  }
}
