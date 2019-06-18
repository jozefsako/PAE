package be.ipl.pae.util;

import be.ipl.pae.business.ServerMessage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

public class Utils {

  /**
   * Works as a Regex. Verify a String based on a pattern.
   *
   * @param errors : list of errors
   * @param pattern : the pattern used to verify the text
   * @param text : the input that has to be verified
   * @param errorName : the list of errors that has been detected
   */
  public static void checkPattern(List<String> errors, String pattern, String text,
      String errorName) {
    if (text == null) {
      return;
    }
    if (!Pattern.compile(pattern).matcher(text).matches()) {
      errors.add(errorName);
    }
  }

  /**
   * Verify if the input is empty.
   * 
   * @param string The string to be verified
   * @param errors The list of errors in which we put the name of the next field
   * @param fieldName The field name put into the errors list
   */
  public static void checkEmpty(String string, List<String> errors, String fieldName) {
    if (string.isEmpty()) {
      errors.add(fieldName);
    }
  }

  /**
   * Return the name of table based on the class.
   * 
   * @param classType the class of the object
   * @return returns the name of the class
   */
  public static String getTableName(Class classType) {
    String classNameWithoutS = camelToUnderScore(classType.getSimpleName());
    return classNameWithoutS.substring(0, classNameWithoutS.length() - 4);
  }

  /**
   * Return the name of the class to match the database table name.
   * 
   * @param tableName the name of the table
   * @return tableNameWithS the name of the table in plurals
   */
  public static String getTableNameWithS(String tableName) {
    String tableNameWithS;
    if (tableName.charAt(tableName.length() - 1) == 'y') {
      tableNameWithS = tableName.substring(0, tableName.length() - 1);
      tableNameWithS += "ies";
    } else {
      tableNameWithS = tableName + "s";
    }
    return tableNameWithS;
  }

  /**
   * Transforms the string with underscores when a Uppercase occurs.
   * 
   * @param string the name to be transformed
   * @return the new formated string
   */
  public static String camelToUnderScore(String string) {
    String regex = "([a-z])([A-Z]+)";
    String replacement = "$1_$2";
    return string.replaceAll(regex, replacement).toLowerCase();
  }

  /**
   * Extracts the fieldname based on a method.
   * 
   * @param declaredMethod Method
   * @return a string transformed with camelToUnderScore
   */
  public static String getFieldName(Method declaredMethod) {
    return camelToUnderScore(declaredMethod.getName().substring(3));
  }

  /**
   * Returns a simpleName to uppercase.
   * 
   * @param simpleName The simpelname to put into uppercase
   * @return a string of the new name
   */
  public static String getUppercaseType(String simpleName) {
    return String.valueOf(simpleName.toUpperCase().charAt(0)).concat(simpleName.substring(1));
  }

  /**
   * Returns the getter of an attribute.
   * 
   * @param getterName The name to be change
   * @return a string of the attribute as a string
   */
  public static String getterToAttribute(String getterName) {
    return camelToUnderScore(getterName).substring(4);
  }

  /**
   * Returns the type of a parameter.
   * 
   * @param parameterType parameter type
   * @return a string that contains the type of the parameter
   */
  public static String getDbType(Class<?> parameterType) {
    String parameterTypeSimple = parameterType.getSimpleName();
    if (parameterTypeSimple.equals("LocalDate")) {
      return "Date";
    }
    if (parameterTypeSimple.equals("Integer")) {
      return "Int";
    }
    return Utils.getUppercaseType(parameterType.getSimpleName());
  }

  /**
   * Returns a Method based on a class.
   * 
   * @param clazz Class that contains the method
   * @param methodName The name of the method
   * @param parameterTypes Parameters types
   * @return a Method
   */
  public static Method getMethod(Class clazz, String methodName, Class... parameterTypes) {
    try {
      return clazz.getDeclaredMethod(methodName, parameterTypes);
    } catch (NoSuchMethodException exp) {
      exp.printStackTrace();
    }
    return null;
  }

  /**
   * This method is used to fill the prepared statement.
   * 
   * @param dto This dto object is used to fill the prepare statement
   * @param ps The prepared statement to be fill
   * @param clazz The type of the class
   * @param insertId boolean that precise if we want to put the id or not
   * @return The number of fields put into the prepared statement
   */
  public static <T> int fillPreparedStatement(T dto, PreparedStatement ps, Class<T> clazz,
      boolean insertId) {
    int counter = 1;
    for (Method getterMethod : clazz.getDeclaredMethods()) {
      try {
        if (getterMethod.invoke(dto) != null
            && (insertId || !getterMethod.getName().contains("getId"))) {
          Method setterMethod;
          Class returnType = Utils.getReturnType(getterMethod.getReturnType());
          setterMethod = Utils.getMethod(PreparedStatement.class,
              "set" + Utils.getDbType(getterMethod.getReturnType()), int.class, returnType);
          setterMethod.invoke(ps, counter,
              Utils.getGetterResult(getterMethod.invoke(dto), returnType));
          counter++;
        }
      } catch (IllegalAccessException | InvocationTargetException exp) {
        exp.printStackTrace();
      }
    }
    return counter;
  }

  /**
   * This method is used to obtain the result from the getter based on his return type.
   * 
   * @param invoke This is the object used to call the method
   * @param returnType The type of return
   * @return an object of the returnType parameter
   */
  private static Object getGetterResult(Object invoke, Class returnType) {
    if (returnType.getSimpleName().equals("Date")) {
      return Date.valueOf((LocalDate) invoke);
    }
    return invoke;
  }

  /**
   * This method obtains the return type for sql.
   * 
   * @param returnType The type of return
   * @return an object of the returnType parameter
   */
  private static Class getReturnType(Class<?> returnType) {
    if (returnType.getSimpleName().equals("LocalDate")) {
      return Date.class;
    }
    if (returnType.getSimpleName().equals("Integer")) {
      return int.class;
    }
    return returnType;
  }

  /**
   * This method is used to retrieve the file from a directory.
   * 
   * @param folder The folder in which we want to list the files
   * @return The list of file
   */
  public static String listFilesInFolder(final File folder) {
    StringBuilder contentBuilder = new StringBuilder();
    for (final File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        listFilesInFolder(fileEntry);
      } else {
        contentBuilder.append(loadRessources(fileEntry.getPath()));
      }
    }
    return contentBuilder.toString();
  }

  /**
   * This method is used to retrieved the content of a file.
   * 
   * @param filename The name of the file
   * @return The content of the file
   */
  public static String loadRessources(String filename) {
    StringBuilder contentBuilder = new StringBuilder();
    try {
      BufferedReader in = new BufferedReader(new FileReader(filename));
      String str;
      while ((str = in.readLine()) != null) {
        contentBuilder.append(str);
      }
      in.close();
    } catch (IOException exp) {
      exp.printStackTrace();
    }
    return contentBuilder.toString();
  }

  /**
   * This method is called to set the response message for the request.
   * 
   * @param resp The response to the servlet request
   * @param serverMessage The message from the server
   * @throws IOException Is produced by failed or interrupted I/O operations
   */
  public static void setResponseMessage(HttpServletResponse resp, ServerMessage serverMessage)
      throws IOException {
    resp.getOutputStream().write(serverMessage.getMessage().getBytes());
    resp.setStatus(serverMessage.getMessageCode());
  }

}
