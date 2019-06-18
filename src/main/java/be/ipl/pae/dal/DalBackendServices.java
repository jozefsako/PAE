package be.ipl.pae.dal;

import be.ipl.pae.exceptions.DbErrorException;

import java.sql.PreparedStatement;

public interface DalBackendServices {

  /**
   * Returns a preparedStatement for this connection.
   *
   * @param query query to be executed
   */
  PreparedStatement getPreparedStatement(String query) throws DbErrorException;
}
