/**
 * 
 */
package model;

/**
 * @author GRost
 *
 */
public class Person {

  private String firstName;
  private String surName;

  /**
   * Constructor.
   * @param firstName First name.
   * @param surName Surname.
   */
  public Person(String firstName, String surName) {
    this.firstName = firstName;
    this.surName = surName;
  }

  /**
   * Changes the first name.
   * @param name First Name.
   */
  public void changeFirstName(String name) {
    this.firstName = name;
  }
  
  
  /**
   * Returns the class properties in a string.
   */
  public String toString() {
    return firstName + " " + surName;
  }
}
