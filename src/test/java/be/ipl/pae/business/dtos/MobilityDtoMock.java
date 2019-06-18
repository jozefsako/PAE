package be.ipl.pae.business.dtos;

import be.ipl.pae.business.dto.mobilities.mobility.Mobility;
import be.ipl.pae.business.dto.mobilities.mobility.MobilityDto;

public class MobilityDtoMock implements MobilityDto, Mobility {

  private String mobilityState;
  private String mobilityConfirmation;
  private boolean proEco;
  private boolean mobilityTool;
  private int idMobilityChoice;
  private boolean paymentSent;
  private int versionNumber;
  private boolean setVersionNumberExpected;
  private boolean setVersionNumberCalled;
  private boolean setMobilityStateExpected;
  private boolean setMobilityStateCalled;
  private boolean setMobilityConfirmationExpected;
  private boolean setMobilityConfirmationCalled;
  private boolean setProEcoExpected;
  private boolean setProEcoCalled;
  private boolean setMobilityToolExpected;
  private boolean setMobilityToolCalled;
  private boolean setIdMobilityChoiceExpected;
  private boolean setIdMobilityChoiceCalled;
  private boolean setPaymentSentExpected;
  private boolean setPaymentSentCalled;

  @Override
  public Integer getId() {
    // TODO Auto-generated method stub
    return Integer.valueOf(idMobilityChoice);
  }

  @Override
  public String getMobilityState() {
    // TODO Auto-generated method stub
    return mobilityState;
  }

  @Override
  public String getMobilityConfirmation() {
    // TODO Auto-generated method stub
    return mobilityConfirmation;
  }

  @Override
  public boolean getProEco() {
    // TODO Auto-generated method stub
    return proEco;
  }

  @Override
  public boolean getMobilityTool() {
    // TODO Auto-generated method stub
    return mobilityTool;
  }

  @Override
  public Integer getVersionNumber() {
    // TODO Auto-generated method stub
    return versionNumber;
  }

  @Override
  public boolean getPaymentSent() {
    // TODO Auto-generated method stub
    return paymentSent;
  }

  @Override
  public void setMobilityState(String mobilityState) {
    setMobilityStateCalled = true;
  }

  @Override
  public void setMobilityConfirmation(String mobilityConfirmation) {
    setMobilityConfirmationCalled = true;
  }

  @Override
  public void setProEco(boolean proEco) {
    setProEcoCalled = true;
  }


  public void setMobilityTool(boolean mobilityTool) {
    setMobilityToolCalled = true;
  }

  @Override
  public void setPaymentSent(boolean paymentSent) {
    setPaymentSentCalled = true;
  }


  /**
   * Test if all the methods have been tested.
   * 
   * @return true if all the methods have been tested and false if it's not the case
   */
  public boolean verify() {
    if (setVersionNumberExpected != setVersionNumberCalled
        || setMobilityStateExpected != setMobilityStateCalled
        || setMobilityConfirmationExpected != setMobilityConfirmationCalled
        || setProEcoExpected != setProEcoCalled || setMobilityToolExpected != setMobilityToolCalled
        || setIdMobilityChoiceExpected != setIdMobilityChoiceCalled
        || setPaymentSentExpected != setPaymentSentCalled) {
      return false;
    }

    setVersionNumberExpected = false;
    setVersionNumberCalled = false;
    setMobilityStateExpected = false;
    setMobilityStateCalled = false;
    setMobilityConfirmationExpected = false;
    setMobilityConfirmationCalled = false;
    setProEcoExpected = false;
    setProEcoCalled = false;
    setMobilityToolExpected = false;
    setMobilityToolCalled = false;
    setIdMobilityChoiceExpected = false;
    setIdMobilityChoiceCalled = false;
    setPaymentSentExpected = false;
    setPaymentSentCalled = false;
    boolean verify = true;
    return verify;
  }

  @Override
  public void setVersionNumber(Integer versionNumber) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setIdMobility(int idMobility) {
    // TODO Auto-generated method stub

  }

  @Override
  public int getIdMobility() {
    // TODO Auto-generated method stub
    return 0;
  }

}
