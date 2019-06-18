package be.ipl.pae.dal;


import be.ipl.pae.business.BusinessFactory;
import be.ipl.pae.business.dto.GenericDto;
import be.ipl.pae.exceptions.ClassCompatibilityException;
import be.ipl.pae.exceptions.DbErrorException;
import be.ipl.pae.exceptions.OptimisticLockException;
import be.ipl.pae.util.MyLogger;
import be.ipl.pae.util.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;

public abstract class GenericDaoImpl<T extends GenericDto, R extends T> implements GenericDao<T> {

  protected DalBackendServices dalBackendServices;
  protected BusinessFactory businessFactory;
  private final Supplier<? extends T> supplier;
  private final Class<T> clazz;
  private final Class<R> interfaceClass;

  /**
   * Constructor of GenericDaoImpl class, it creates an object GenericDaoImpl.
   * 
   * @param supplier Allow to provide an instance of a dto object from clazz
   * @param clazz The class that we want a dto object
   * @param interfaceClass Interface of clazz
   */
  public GenericDaoImpl(Supplier<? extends T> supplier, Class<T> clazz, Class<R> interfaceClass) {
    this.supplier = Objects.requireNonNull(supplier);
    this.clazz = clazz;
    this.interfaceClass = interfaceClass;
  }


  @Override
  public void update(T dto, int id)
      throws DbErrorException, OptimisticLockException, ClassCompatibilityException {
    String tableName = Utils.getTableName(clazz);
    String tableNameWithS = Utils.getTableNameWithS(tableName);
    StringBuilder query = new StringBuilder("UPDATE pae." + tableNameWithS + " SET");

    for (Method getterMethod : clazz.getDeclaredMethods()) {
      String attributeName = Utils.getterToAttribute(getterMethod.getName());
      try {
        if (getterMethod.invoke(dto) != null && !getterMethod.getName().contains("getId")) {
          query.append(" ").append(attributeName).append(" = ?,");
        }
      } catch (IllegalAccessException | InvocationTargetException err) {
        MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
        throw new ClassCompatibilityException(err.getMessage());
      }
    }
    query.deleteCharAt(query.length() - 1);
    query.append(" WHERE id_").append(tableName).append(" = ? AND version_number = ?");
    PreparedStatement ps = dalBackendServices.getPreparedStatement(query.toString());
    try {
      int counter = Utils.fillPreparedStatement(dto, ps, clazz, false);
      ps.setInt(counter, id);
      ps.setInt(counter + 1, (int) clazz.getDeclaredMethod("getVersionNumber").invoke(dto) - 1);
      if (ps.executeUpdate() == 0) {
        throw new OptimisticLockException();
      }
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException err) {
      MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
      throw new ClassCompatibilityException(err.getMessage());
    }
  }


  @Override
  public List<T> findAll() throws DbErrorException, ClassCompatibilityException {
    List<T> list = new ArrayList<>();
    String tableName = Utils.getTableName(clazz);
    String tableNameWithS = Utils.getTableNameWithS(tableName);
    String query = "SELECT * FROM pae." + tableNameWithS;
    PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
    try {
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
        T dto = supplier.get();
        fillDto(dto, resultSet);
        list.add(dto);
      }
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
    return list;
  }

  @Override
  public List<T> findAllById(int id, String researchId)
      throws DbErrorException, ClassCompatibilityException {
    List<T> list = new ArrayList<>();
    String tableName = Utils.getTableName(clazz);
    String tableNameWithS = Utils.getTableNameWithS(tableName);
    String query = "SELECT * FROM pae." + tableNameWithS + " WHERE "
        + Utils.camelToUnderScore(researchId) + " = (?)";
    PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
    try {
      ps.setInt(1, id);
      ResultSet resultSet = ps.executeQuery();
      while (resultSet.next()) {
        T dto = supplier.get();
        fillDto(dto, resultSet);
        list.add(dto);
      }
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
    return list;
  }

  /*
  
   */
  @Override
  public void delete(int id) {

  }


  @Override
  public T findById(int id) throws DbErrorException, ClassCompatibilityException {
    String tableName = Utils.getTableName(clazz);
    String tableNameWithS = Utils.getTableNameWithS(tableName);
    String query = "SELECT * FROM pae." + tableNameWithS + " WHERE id_" + tableName + " = (?)";
    PreparedStatement ps = dalBackendServices.getPreparedStatement(query);
    T dto = supplier.get();
    try {
      ps.setInt(1, id);
      ResultSet resultSet = ps.executeQuery();
      if (!resultSet.next()) {
        return null;
      }
      fillDto(dto, resultSet);
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
    return dto;
  }


  @Override
  public void fillDto(T dto, ResultSet resultSet) throws ClassCompatibilityException {

    R rawType = (R) dto;
    for (Method getterMethod : clazz.getDeclaredMethods()) {
      try {
        char[] nameArray = getterMethod.getName().toCharArray();
        nameArray[0] = 's';
        String setterName = String.valueOf(nameArray);
        Method setterMethod =
            interfaceClass.getDeclaredMethod(setterName, getterMethod.getReturnType());
        Method getter = Utils.getMethod(ResultSet.class,
            "get" + Utils.getDbType(setterMethod.getParameterTypes()[0]), String.class);
        Object getterResult;
        if (getter != null) {
          getterResult = getter.invoke(resultSet, Utils.getFieldName(getterMethod));
          if (getterResult != null) {
            if (setterMethod.getParameterTypes()[0].getSimpleName().equals("LocalDate")) {
              setterMethod.invoke(rawType, ((Date) getterResult).toLocalDate());
            } else {
              setterMethod.invoke(rawType, getterResult);
            }
          }
        }
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException err) {
        MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
        throw new ClassCompatibilityException(err.getMessage());
      }
    }
  }


  @Override
  public void insert(T dto) throws ClassCompatibilityException, DbErrorException {
    String tableName = Utils.getTableName(clazz);
    String tableNameWithS = Utils.getTableNameWithS(tableName);
    StringBuilder query = new StringBuilder("INSERT INTO pae." + tableNameWithS + " (");
    // set fields
    int columnsCounter = 0;
    for (Method getterMethod : clazz.getDeclaredMethods()) {
      String attributeName = Utils.getterToAttribute(getterMethod.getName());
      System.out.println(getterMethod.getName());
      try {
        if (getterMethod.invoke(dto) != null) {
          query.append(" ").append(attributeName).append(",");
          columnsCounter++;
        }
      } catch (IllegalAccessException | InvocationTargetException err) {
        MyLogger.getLogger().log(Level.SEVERE, err.toString(), err);
        throw new ClassCompatibilityException(err.getMessage());
      }

    }
    query.deleteCharAt(query.length() - 1);
    query.append(") VALUES (");
    for (int i = 0; i < columnsCounter; i++) {
      query.append("?,");
    }
    query.deleteCharAt(query.length() - 1);
    query.append(")");
    PreparedStatement ps;
    try {
      ps = dalBackendServices.getPreparedStatement(query.toString());
      Utils.fillPreparedStatement(dto, ps, clazz, true);
      ps.executeUpdate();
    } catch (SQLException err) {
      MyLogger.getLogger().log(Level.WARNING, err.toString(), err);
      throw new DbErrorException(err.getMessage());
    }
  }

  @Override
  public void setBusinessFactory(BusinessFactory businessFactory) {
    this.businessFactory = businessFactory;
  }

  @Override
  public void setDalBackendServices(DalBackendServices dalBackendServices) {
    this.dalBackendServices = dalBackendServices;
  }
}
