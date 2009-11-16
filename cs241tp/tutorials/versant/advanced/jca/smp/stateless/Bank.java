package smp.stateless;

import javax.ejb.*;
import java.rmi.RemoteException;
import java.rmi.Remote;

import model.*;


/**
 * This is the remote interface of the Session Bean, Bank.
 * It has methods to perform basic sample bank operation,
 * e.g. search for a member, create a new account.
 */
public interface Bank extends EJBObject, Remote {


  public String createMember(long ssn, String name, String emailAddress)   throws RemoteException;


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
