package be.ipl.pae.dal;

import be.ipl.pae.exceptions.DbErrorException;

public interface DalServices {

  /**
   * Starts a data base transaction.
   */
  void startTransaction() throws DbErrorException;

  /**
   * Commits a data base transaction.
   */
  void commitTransaction() throws DbErrorException;

  /**
   * Rollback the transaction in case of problems.
   */
  void rollbackTransaction() throws DbErrorException;

  /**
   * Closes the connection for this transaction.
   */
  void closeConnection() throws DbErrorException;
}
