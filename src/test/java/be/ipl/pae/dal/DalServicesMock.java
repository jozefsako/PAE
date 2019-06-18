package be.ipl.pae.dal;

import be.ipl.pae.exceptions.DbErrorException;

public class DalServicesMock implements DalServices {

  private boolean startTransactionExpected;
  private boolean startTransactionCalled;
  private boolean commitTransactionExpected;
  private boolean commitTransactionCalled;
  private boolean rollbackTransactionExpected;
  private boolean rollbackTransactionCalled;

  @Override
  public void startTransaction() throws DbErrorException {
    startTransactionCalled = true;
  }

  @Override
  public void commitTransaction() throws DbErrorException {
    commitTransactionCalled = true;
    if (rollbackTransactionExpected) {
      throw new DbErrorException("test");
    }

  }

  @Override
  public void rollbackTransaction() throws DbErrorException {
    rollbackTransactionCalled = true;

  }

  @Override
  public void closeConnection() throws DbErrorException {
    // TODO Auto-generated method stub

  }

  public void setUpTransactionOk() {
    startTransactionExpected = true;
    commitTransactionExpected = true;
  }

  /**
   * Set the tested transaction to a KO state.
   */
  public void setUpTransactionKo() {
    startTransactionExpected = true;
    commitTransactionExpected = true;
    rollbackTransactionExpected = true;
  }

  /**
   * Test if all the methods have been tested.
   * 
   * @return true if all the methods have been test, false if it's not the case
   */
  public boolean verify() {
    boolean verify = true;

    if (startTransactionExpected != startTransactionCalled
        || commitTransactionExpected != commitTransactionCalled
        || rollbackTransactionExpected != rollbackTransactionCalled) {
      verify = false;
    }


    startTransactionExpected = false;
    startTransactionCalled = false;
    commitTransactionExpected = false;
    commitTransactionCalled = false;
    rollbackTransactionExpected = false;
    rollbackTransactionCalled = false;

    return verify;
  }

}
