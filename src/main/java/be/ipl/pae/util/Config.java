package be.ipl.pae.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

  private static String currentPackageName;
  private static final Properties prop = new Properties();

  /**
   * Get the property file from the specified package name.
   * 
   * @param property The property file to be retrieved
   * @param packageName The package name
   * @return property object from the file .properties
   */
  public static String getProperty(String property, String packageName) {
    if (!packageName.equals(currentPackageName)) {
      loadRessource(packageName);
    }
    return prop.getProperty(property);
  }

  /**
   * This method is used to load the ressources from a package.
   * 
   * @param packageName The package needed to load the ressources
   */
  private static void loadRessource(String packageName) {
    currentPackageName = packageName;
    InputStream input = null;
    try {
      input = new FileInputStream(packageName + "config.properties");
      prop.load(input);
    } catch (final IOException exp) {
      exp.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (final IOException exp1) {
          exp1.printStackTrace();
        }
      }
    }
  }
}
