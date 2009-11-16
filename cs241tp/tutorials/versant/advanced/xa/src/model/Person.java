// A very simplified Person class.
package model;

public class Person {

  String firstName; 
  String lastName;
  int age;
  float height;


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
   * returns the Person information
   */
  public String toString () {
    String ret = new String (lastName + ", " + firstName + " " +
			     age + "yrs., " + height + " ft.");
    return ret;
  }
}
