package smp.stateless;

import javax.naming.*;
import javax.ejb.*;
import java.rmi.*;
import java.util.*;
import java.io.*;
import javax.resource.*;
import javax.resource.cci.*;

import com.versant.connector.jvi.cci.VConnection;
import com.versant.trans.TransSession;
import com.versant.trans.VQLQuery;
import com.versant.trans.VEnumeration;
import com.versant.fund.VException;
import model.Member;
import model.Account;

/**
 * This is the bean class of the Session Bean, Bank.
 * It accesses the enjin database to perform basic sample bank operation
 * e.g. search for a member, create a new account.
 */
public class BankBean implements SessionBean {

  private SessionContext sc;
  private ConnectionFactory conFactory;

  /**
   * Set the session context.
   * And look up Versant connection factory.
   * @param sc Session context
   */
  public void setSessionContext (SessionContext sc) {
    String connectionName = null;
    StringBuffer buf = new StringBuffer();
    this.sc = sc;

    Properties props = new Properties ();
    try
    {
	  InputStream in = model.Account.class.getResourceAsStream("jndi.properties");
	  props.load (in);
	  connectionName = props.getProperty("jndi.name").trim();
	  }catch (Exception ioe)
	    {
	     System.out.println("Couldn't load resource 'jndi.properties'."+ ioe);
	     ioe.printStackTrace();
         }

    try {
      buf = new StringBuffer();
      InitialContext ctx = new InitialContext();
      conFactory =  (ConnectionFactory)ctx.lookup (connectionName);
    } catch (NamingException ex) {
	    log(buf, "failed to do look up for connection " + connectionName + " with exception " + ex.getMessage ());
      ex.printStackTrace();
      throw new EJBException (ex.getMessage ());
    }
  }

  /**
   * Get the session context
   * @return session context
   */
  public SessionContext getSessionContext () {
    return sc;
  }

  public void ejbCreate () {}

  public void ejbRemove () {}

  public void ejbActivate () {}

  public void ejbPassivate () {}

  public String createMember(long ssn, String name, String emailAddress)
  {

	Connection connection = null;
    StringBuffer buf = new StringBuffer();
    try {
    connection = conFactory.getConnection();
    TransSession session = ((VConnection)connection).getTransSession();
    Member memberInfo = new Member(ssn,name,emailAddress);
    session.makePersistent(memberInfo);
    } catch (ResourceException exp) {
	  exp.printStackTrace();
      log (buf, "got ResourceException " + exp.getMessage());
      log (buf, exp);
      throw new EJBException (exp.getMessage(), exp);
    } finally {
      closeConnection (connection);
    }
    return buf.toString();

  }

  /**
   * Find a specific member from the database
   * Here are the steps illustrated in this example
   * <li> Get a connection from Versant connection factory.
   * <li> Get a Versant session from Versant Connection.
   * <li> Construct a Versant Query to look for a member with the given social
   * security number.
   * <li> Return a propery message to the client
   * <li> Close the connection at the finally block
   * @param ssn Social Security Number
   * @return the query result
   */
  public String findMember (long ssn) {
    Connection connection = null;
    StringBuffer buf = new StringBuffer();
    try {
      connection = conFactory.getConnection();
      TransSession session = ((VConnection)connection).getTransSession();
      VQLQuery query = new VQLQuery(session,
        "select * from model.Member where ssn = " + ssn);
      VEnumeration queryRes = query.execute();
      if (queryRes.hasMoreElements()) {
        Member member = (Member)queryRes.nextElement();
        log (buf, "Found " + member.getName());
      } else {
        log (buf, "Can not find the member with SSN " + ssn);
      }
    } catch (ResourceException exp) {
	  exp.printStackTrace();
      log (buf, "got ResourceException " + exp.getMessage());
      log (buf, exp);
      throw new EJBException (exp.getMessage(), exp);
    } finally {
      closeConnection (connection);
    }
    return buf.toString();
  }

  /**
   * Create a new account with the given information
   * Here are the steps illustrated in this example
   * <li> Get a connection from Versant connection factory.
   * <li> Get a Versant session from Versant Connection.
   * <li> Get the maximum account number in the database, since a unique btree index
   * is created on attribute account nubmer, the query result will be sorted in
   * ascending order.
   * <li> Create a Account object
   * <li> Construct a Versant Query to look for a member with the given social
   * security number.
   * <li> Add the new account to this member
   * <li> Return a propery message to the client
   * <li> Close the connection at the finally block
   * @param ssn Social security number
   * @param accountType Account Type
   * @param totalAmount initial balance to create this account
   * @return a message to inform client if the account creation succeed.
   */
  public String newAccount(long ssn, int accountType, double totalAmount) {
    Connection connection = null;
    StringBuffer buf = new StringBuffer();
    try {
      connection = conFactory.getConnection();
      TransSession session = ((VConnection)connection).getTransSession();
      Account account = new Account();
      account.setAccountType(accountType);
      VQLQuery query1 = new VQLQuery(session,
        "select * from model.Account");
      int maxAccountNum = 0;
      VEnumeration queryRes1 = query1.execute();
      while (queryRes1.hasMoreElements()) {
        maxAccountNum = ((Account)queryRes1.nextElement()).getAccountNumber();
      }
      account.setAccountNumber(maxAccountNum + 1);
      account.setTotalAmount(totalAmount);
      VQLQuery query2 = new VQLQuery(session,
        "select * from model.Member where ssn = " + ssn);
      VEnumeration queryRes2 = query2.execute();
      if (queryRes2.hasMoreElements()) {
        Member member = (Member)queryRes2.nextElement();
        member.addAccount(account);
        log (buf, "Added account " + account.getAccountNumber());
        session.makePersistent(account);
      } else {
        log (buf, "Can not find the member with SSN " + ssn);
      }
    } catch (ResourceException exp) {
      exp.printStackTrace();
      log (buf, "got ResourceException " + exp.getMessage());
      log (buf, exp);
      throw new EJBException (exp.getMessage(), exp);
    } finally {
      closeConnection (connection);
    }
    return buf.toString();
  }

  private void log(StringBuffer buf, String msg) {
    System.out.println (msg);
    buf.append(msg + "\n");
  }

  private void log(StringBuffer buf, Throwable t) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    t.printStackTrace(pw);
    buf.append(sw.toString() + "\n");
  }

  private void closeConnection(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (ResourceException re) {
      throw new EJBException ("ResourceException in closing connection " +
      re.getMessage(), re);
    }
  }
}

