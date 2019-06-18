package be.ipl.pae.util;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

  private static MyLogger logMe;
  private static Logger logger;
  private static FileHandler fileHandler;
  private static Formatter formatter;

  /**
   * The constructor of the logger.
   */
  public MyLogger() {
    logger = Logger.getLogger("LogMe");
    logMe = this;
    try {
      fileHandler = new FileHandler("LogFile.log");
    } catch (Exception err) {
      err.printStackTrace();
    }
    formatter = new SimpleFormatter();
    fileHandler.setFormatter(formatter);
    logger.addHandler(fileHandler);
  }

  /**
   * This method is used to get the logger.
   * 
   * @return the logger
   */
  public static Logger getLogger() {
    if (logMe == null) {
      logMe = new MyLogger();
    }
    return logger;
  }
}
