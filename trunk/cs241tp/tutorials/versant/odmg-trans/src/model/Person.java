package model;

import com.versant.fund.VException;
import com.versant.fund.FundSession;
import com.versant.trans.TransSession;

public class Person {

  String firstName; 
  String lastName;
  int age;
  float height;
  double salary;
  Company workPlace;

  Person previous;

  /**
   * Standard constructor.
   */
  public Person() {
  
  }

  /**
   * the ctor
   */
  public Person (String aFirst, String aLast, int old, float ht) {
    firstName = aFirst;
    lastName = aLast;
    age = old;
    height = ht;
  }

  /**
   * the ctor
   */
  public Person (String aFirst, String aLast, int old, float ht, Person aPrev) {
    firstName = aFirst;
    lastName = aLast;
    age = old;
    height = ht;
    previous = aPrev;
  }


  /**
   * returns the persons first name
   */
  String getFirstName () {
    return firstName;
  }


  /**
   * returns the persons last name
   */
  String getLastName () {
    return lastName;
  }


  /**
   * sets the Company and and salary fields
   */
  public void setJob (Company aCompany, double dough) {
    workPlace = aCompany;
    salary = dough;
  }


  /**
   * returns the Person information
   */
  public String toString () {
    String ret = new String (lastName + ", " + firstName + " " +
			     age + "yrs., " + height + " ft.");

    if (workPlace != null) {
      ret = ret.concat (" [" + workPlace + "]");
    }
    else {
      ret = ret.concat (" [No employment information]");
    }

    if (previous != null) {
      ret = ret.concat (" -> " + "(Previous): " + previous);
    }

    return ret;
  }
 
  /**
   * Provide a delete hook that is called whenever an instance of the
   * Person class is deleted using TransSession.deleteObject(). This allows
   * an application to delete any logically contained or subordinate objects of
   * a instance of a class that provides an implementation for this hook.
   */
  protected void vDelete ( ) {
      System.out.println("deleting " + lastName + " " + firstName);

      // Get the session associated with the current thread
      TransSession session = 
                         (TransSession) FundSession.sessionOfCurrentThread ();

      try {
          session.deleteObject(workPlace);
      }
      catch (VException ve) {
          if (ve.getErrno() == 5006) 
              System.out.println("The company associated with " + lastName + " " + firstName  + " has already been deleted");
          else
              throw ve; 
      }
  }
  
}
