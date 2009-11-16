package smp.stateful;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.rmi.Remote;
import javax.transaction.*;

/**
 * This is the remote interface of the Session Bean, Bank.
 * It has methods to perform basic sample bank operation,
 * e.g. search for a member, create a new account.
 */
public interface Bank extends EJBObject, Remote {

    /**
     * Method by which a calling client can start a transaction.
     */
    public void beginTransaction() throws RemoteException;

    /**
     * Method by which a calling client can commit a transaction.
     */
    public void commitTransaction() throws RemoteException;

    /**
     * Method by which a calling client can rollback a transaction.
     */
    public void rollbackTransaction() throws RemoteException;

    public String createMember(long ssn, String name, String emailAddress) throws RemoteException;
  /**
   * create an account for a member
   * @param ssn the social security number for member
   * @param accountType 1 for Checking account, 2 for Savings account
   * @param totalAmount initial amount to open this account
   * @return the account number
   * @exception java.rmi.RemoteException
   */
  public String newAccount(long ssn, int accountType, double totalAmount)
    throws RemoteException;

  /**
   * find a member by a given social security number
   * @param ssn the social security number for member
   * @return the member information
   * @exception java.rmi.RemoteException
   */
  public String findMember(long ssn) throws RemoteException;
}
