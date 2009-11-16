
package demo;

import model.Person;
import com.versant.trans.*;


public class JVIDemo {

  /** demo session */
  private TransSession session;

  /**
   * Create a session to start a transaction
   */
  public JVIDemo(String databaseName) {
    try {
      this.session = new TransSession(databaseName);
    }
    catch(Exception e) {
      System.err.println("Could not connect to database: " + databaseName +
          "\n Reason: " + e.getMessage());
      this.session = null;
    }
  }

  /**
   * Ends the current session.
   */
  public void endSession() {
    this.session.endSession();
  }

  /**
   * Checks whether a connect to a database could be established or not.
   * @return True if a connection could be  established, false otherwise.
   */
  public boolean isConnected() {
    return this.session != null;
  }

  /**
   * Insert object into the database bu making the persistent.
   */
  public void insertPerson(Person person) {
    this.session.makePersistent(person);
  }

  /**
   * Commit the current transaction.
   */
  public void commit() {
    session.commitAndCleanCod();
  }

  /**
   * Deletes all one or more persons from the database.
   */
  public void deletePerson(String name) {
    VQLQuery query = new VQLQuery(session, "select * from model.Person where surName like $1");
    query. bind(name);

    VEnumeration venum = query.execute();
    while (venum.hasMoreElements ()) {
      Object person = venum.nextElement();
      this.session.deleteObject(person);
    }
  }

  /**
   * Returns the first person in the database the satisfies the name.
   */
  public Person getPerson(String name) {
    VQLQuery query = new VQLQuery(session, "select * from model.Person where surName like $1");
    query. bind(name);

    VEnumeration venum = query.execute();
    return (Person)venum.nextElement();
  }

  /**
   * Displays all persons in the database.
   */
  public void showContent() {
    System.out.println("Persons in database:");
    System.out.println("--------------------");

    VQLQuery query = new VQLQuery(session, "select * from model.Person");   
    VEnumeration venum = query.execute();

    while (venum.hasMoreElements ()) {
      Object person = venum.nextElement();
      System.out.println((Person)person);
    }

    System.out.println();
  }


  /**
   * 
   * Main program.
   * @param args Arguments.
   * 
   */  
  public static void main(String[] args) {
    // check for exactly one input parameter
    if (args.length != 1) {
      System.out.println("Usage  java Test <dbname>");
      System.exit(0);
    }

    String databaseName = args[0];
    JVIDemo jviDemo = new JVIDemo(databaseName);
    // if the connection failed then exit
    if (!jviDemo.isConnected()) 
      System.exit(0);


    //
    // 4 persons get inserted into the database.
    //
    System.out.println("[Inserting...]");
    Person guido   = new Person("Guido", "Rost");
    Person andreas = new Person("Andreas", "Gries");
    Person michael = new Person("Michael", "Damkier");
    Person andy    = new Person("Andy", "Renner");

    jviDemo.insertPerson(guido);
    jviDemo.insertPerson(andreas);
    jviDemo.insertPerson(michael);
    jviDemo.insertPerson(andy); 
    jviDemo.showContent();


    //
    // 1 person gets deleted.
    //
    System.out.println("[Deleting Michael Damkier...]");
    jviDemo.deletePerson("Damk*");
    jviDemo.showContent();


    //
    // 1 persons first name get updated.
    //
    System.out.println("[Renaming Andy Renner to Andreas Renner...]");
    Person arenner = jviDemo.getPerson("Renner");
    arenner.changeFirstName("Andreas");
    jviDemo.showContent();


    //
    // clean up the database before leaving
    //
    jviDemo.deletePerson("*");
    jviDemo.endSession();
  }
}
