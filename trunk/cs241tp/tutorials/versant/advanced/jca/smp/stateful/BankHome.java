package smp.stateful;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * This is the home interface of the Session Bean, Bank.
 */
public interface BankHome extends EJBHome {

  public Bank create() throws CreateException, RemoteException;
}
