/**
 * Example that demonstrates the security changes in JVI6.0.  Before a session
 * can be started on a database the user name and password has to be set,
 * unless the os user is the DBA or the os user does not have a password in
 * the database user list. The user name and password is passed as session
 * properties. Once the session is established this demo then shows how to
 * change the existing user name and password and establish a connection to
 * another database using the new user name and password.
 */
import com.versant.util.*;
import com.versant.trans.*;
import com.versant.fund.*;
import java.util.*;

class SecurityDemo
{
  // Error number 2994 corresponds to the current user not being a authorized
  // user of the database.
  public static final int NOT_IN_USER_LIST=2994;

  public static void main(String[] args)
  {
    if (args.length < 2)
    {
      System.out.println("Usage: SecurityDemo <database1> <database2>");
      System.exit(-1);
    }
    
    // name of the user database
    String sessionDatabase = args[0];
    String connectDatabase = args[1];
    
    // Build the Session properties
    Properties prop = new Properties();
    prop.put ("database", sessionDatabase);

    // Before beginning a session, the user name and password has to be set.
    // In this case use a arbitrary name and password that is expected to fail
    // the password validation.
    prop.put ("userName", "user1");
    prop.put ("userPassword",  "user1");

    // Since its more than likely a user 'user1' does not exist in the database
    // user list we can expect the session creation to fail. That is the
    // user 'user1' will not be validated as a database user.
    try
    {
      TransSession session = new TransSession(prop);
    }
    catch (VException ve)
    {
      if (ve.getErrno() != NOT_IN_USER_LIST)
	throw ve;
      System.out.println("Could not begin session on Database " + 
                         sessionDatabase + " as user: user1");
    }

    // now add the user 'user1' as a valid user and then try to begin a 
    // session on the database.
    String userName = "user1";
    String userPassword = "user1";
    String userPrivileges = "rw";
    DBUtility.addDBUser(sessionDatabase, userName, userPassword, userPrivileges);

    // since the user "user1" is now listed as a valid database user, a
    // connection is established to the back end. After this validation
    // any other threads joining this session will not be validated. The user
    // has to implement his own security mechanism for threads joining this
    // session.
    TransSession session = new TransSession(prop);

    // have session establish a connection to the second database. This time
    // around we will connect as "user2".
    // first add user "user2" as a valid database user to the connectDatabase
    userName = "user2";
    userPassword = "user2";
    userPrivileges = "rw";
    DBUtility.addDBUser(connectDatabase, userName, userPassword, userPrivileges);

    // This is the first attempt to connect. This attempt will fail because
    // the user name currently cached in the thread is user1. user1 is not a
    // valid user of connectDatabase.
    try
    {
      session.connectDatabase(connectDatabase, Constants.READ_WRITE_ACCESS);
    }
    catch (VException vex)
    {
      if (vex.getErrno() != NOT_IN_USER_LIST)
	throw vex;
	
      System.out.println("Could not connect to Database " + connectDatabase +
                          "as user: user2");
    }

    // now change the user name and password cached in this thread.
    session.setUserLogin(userName, userPassword);

    // try to establish connection now.
    session.connectDatabase(connectDatabase, Constants.READ_WRITE_ACCESS);
  }
}
