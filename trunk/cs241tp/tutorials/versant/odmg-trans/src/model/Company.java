package model;

public class Company {

  String name;
  String state;
  int zipCode;

  /**
   * the ctor
   */
  public Company (String aName, String aState, int aZip) {
    name = aName;
    state = aState;
    zipCode = aZip;
  }


  /**
   * returns the Company information
   */
  public String toString () {
    return (new String (name + " " + state + ":" +
			((zipCode < 10000) ? "0" : "") + zipCode));
  }
}
