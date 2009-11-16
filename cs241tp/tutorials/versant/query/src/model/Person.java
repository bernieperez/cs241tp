package model;

import java.util.Date;
import java.io.*;

public class Person extends AbstractPerson implements Serializable {
  protected int ssn;               // Key
  protected Person bestFriend;
  protected Address address;
  public Image photo;
  protected PersonalDetails details;

  public Person(int ssn, String name, Date dob, String gender, 
      Address addr, PersonalDetails details, Image photo) {
    super(name, dob, gender);
    this.ssn  = ssn;
    this.address = addr;
    this.details = details;
    this.photo = photo;
  }

  public Person(int ssn,String name, Date dob,String gender) {
    super(name, dob, gender);
    this.ssn = ssn;
  }

  public Person(String name, Date dob,String gender) {
    super(name, dob, gender);
  }

  public void setBestFriend(Person bestFriend) {
    this.bestFriend = bestFriend;
  }

  public final int getSSN() {
    return this.ssn;
  }

  public void setPhoto(Image photo) {
    this.photo = photo;
  }

  public Image getPhoto() {
    return this.photo;
  }

  public final Address getAddress() {
    return this.address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public final PersonalDetails getDetails() {
    return details;
  }

  public String toString() {
    return super.toString() + "\n" +
    "SSN= " + this.ssn + "\n" +
    "Address: \n" + this.address + "\n" +
    "Friend=" + this.bestFriend + "\n" +
    "Details=" + this.details;
  }

}
