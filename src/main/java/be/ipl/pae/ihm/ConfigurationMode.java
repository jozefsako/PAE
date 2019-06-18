package be.ipl.pae.ihm;

public enum ConfigurationMode {
  DEV("development"), PROD("production");

  private String configurationMode;

  ConfigurationMode(String configurationMode) {
    this.configurationMode = configurationMode;
  }

  public String getConfigurationMode() {
    return configurationMode;
  }
}
