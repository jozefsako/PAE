package be.ipl.pae.business.dto.mobilities.mobility;

import be.ipl.pae.business.dto.GenericDtoImpl;

public class MobilityImpl extends GenericDtoImpl implements Mobility, MobilityDto {

  private String mobilityState;
  private String mobilityConfirmation;
  private boolean proEco;
  private boolean mobilityTool;
  private boolean paymentSent;
  private Integer versionNumber;

  @Override
  public void setPaymentSent(boolean paymentSent) {
    this.paymentSent = paymentSent;
  }

  @Override
  public void setVersionNumber(Integer versionNumber) {
    this.versionNumber = versionNumber;
  }

  @Override
  public void setMobilityState(String mobilityState) {
    this.mobilityState = mobilityState;
  }

  @Override
  public void setMobilityConfirmation(String mobilityConfirmation) {
    this.mobilityConfirmation = mobilityConfirmation;
  }

  @Override
  public void setProEco(boolean proEco) {
    this.proEco = proEco;
  }

  @Override
  public void setMobilityTool(boolean mobilityTool) {
    this.mobilityTool = mobilityTool;
  }

  @Override
  public void setIdMobility(int idMobilityChoice) {
    this.id = idMobilityChoice;
  }

  @Override
  public String getMobilityState() {
    return mobilityState;
  }

  @Override
  public String getMobilityConfirmation() {
    return mobilityConfirmation;
  }

  @Override
  public boolean getProEco() {
    return proEco;
  }

  @Override
  public boolean getMobilityTool() {
    return mobilityTool;
  }

  @Override
  public int getIdMobility() {
    return id;
  }

  @Override
  public Integer getVersionNumber() {
    return versionNumber;
  }

  @Override
  public boolean getPaymentSent() {
    return paymentSent;
  }
}
