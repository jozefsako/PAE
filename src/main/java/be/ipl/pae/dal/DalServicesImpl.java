package be.ipl.pae.dal;

import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.util.Config;
import be.ipl.pae.util.MyLogger;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class DalServicesImpl implements DalBackendServices, DalServices {

  private ThreadLocal<Connection> connections = new ThreadLocal<>();
  private BasicDataSource dataSource = new BasicDataSource();
  // private ThreadLocal<Integer> transactionLevel = new ThreadLocal<>();

  /**
   * Constructor of DalServicesImpl Create an object DalServicesImpl that's allow the program to.
   * have a connection to the database.
   */
  public DalServicesImpl() {
    try {
      Class.forName("org.postgresql.Driver");

    } catch (ClassNotFoundException exp) {
      exp.printStackTrace();
      System.out.println("Driver PostgreSQL manquant !");
    }
    String url = Config.getProperty("server", "dal") + Config.getProperty("host", "dal") + ":"
        + Config.getProperty("port", "dal") + "/" + Config.getProperty("db", "dal") + "?"
        + Config.getProperty("user", "dal");

    dataSource.setUrl(url);
    dataSource.setUsername(Config.getProperty("user", "dal"));
    dataSource.setPassword(Config.getProperty("password", "dal"));
  }


  @Override
  public PreparedStatement getPreparedStatement(String query) throws DbErrorException {
    try {
      return connections.get().prepareStatement(query);
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
  }

  @Override
  public void startTransaction() throws DbErrorException {
    try {
      Connection connection = dataSource.getConnection();
      connections.set(connection);
      connection.setAutoCommit(false);
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
  }

  @Override
  public void commitTransaction() throws DbErrorException {
    try {
      connections.get().commit();
      closeConnection();
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
  }

  @Override
  public void rollbackTransaction() throws DbErrorException {
    try {
      connections.get().rollback();
      closeConnection();
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
  }

  @Override
  public void closeConnection() throws DbErrorException {
    try {
      connections.get().close();
      connections.remove();
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
  }
}
