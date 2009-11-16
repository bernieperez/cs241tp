package model;
import java.io.*;


public class Address implements Serializable {

  private long addrid;
  private static long  nextaddrid = Math.round(Math.random() * 1000000);
  public String street;
  public String city;
  public String state;
  public int zip;
  public int phoneNumber;
  public String email;
  public String[] emails; 
  public int[] phoneNumbers;

  
  public Address(String street, String city, String state, 
      int zip, int phone, String email) {
    addrid  = nextaddrid++;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.phoneNumber = phone;
    this.email = email;
  }

  
  public Address() {
  }

  
  public void addEmails(String[] emails) {
    this.emails = emails;
  }

  
  public void addPhoneNumbers(int[] phones) {
    this.phoneNumbers = phones;
  }

  
  public void add_addPhoneNumber(int phonenum) {
    this.phoneNumber = phonenum;
  }

  
  public String toString() {
    String temail = "";
    String tphone = "";

    if (this.emails != null) 
      for (int i = 0 ; i < this.emails.length; i++)
        temail += " \n " + this.emails[i];

    if (this.phoneNumbers != null) 
      for (int i = 0; i < this.phoneNumbers.length; i++)
        tphone += "\n"  + this.phoneNumbers[i];

    return ("Street: " +  this.street + 
        "\n City: " + this.city + 
        "\n State: " + this.state + " " + " Zip: " + this.zip +  
        "\n Phone No : " + this.phoneNumber + 
        "\n Email : " + this.email + temail +   
        "\n Phones: " + tphone);
  }

  
  public boolean equals(Object obj) { 
    if (obj == this)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;

    Address address = (Address)obj;
    return ((address.street.equals(this.street)) && 
        (address.city.equals(this.city)) && 
        (address.state.equals(this.state)) && 
        (address.zip == this.zip) && 
        (address.phoneNumber == this.phoneNumber) && 
        (address.email.equals(this.email))) ; 
  }
}

