
package model;

import java.io.*;

public class PersonalDetails implements Serializable {
  private long pdid;
  private static long nextpdid = Math.round(Math.random() * 1000000);

  public int phoneNumber2;
  public int faxNumber;
  public int mobileNumber;
  public String email2;

  public String birthPlace;
  public String countryOfBirth;
  public String passportId;
  public String driverId;
  public short noOfChildren;
  public boolean isMarried;

  public long creditCardId; // oops
  public float height;
  public double weight;       // very fat
  public byte heightUnit;
  public byte weightUnit;

  public PersonalDetails(int phone2, int fax, int mobile, 
      String mail2, String birthplace, String countryOfBirth, 
      String passportId, String driverId, short noOfChildren, 
      boolean maritalstatus, long creditCardId, float height,
      double weight, byte heightUnit, byte weightUnit) {
    this.phoneNumber2 = phone2;
    this.faxNumber = fax;
    this.mobileNumber = mobile;
    this.email2 = mail2;
    this.birthPlace = birthplace;
    this.countryOfBirth = countryOfBirth;
    this.passportId = passportId;
    this.driverId = driverId;
    this.noOfChildren = noOfChildren;
    this.isMarried = maritalstatus;
    this.creditCardId = creditCardId;
    this.height = height;
    this.weight = weight;
    this.heightUnit = heightUnit;
    this.weightUnit = weightUnit;
    this.pdid = nextpdid++;
  }

  public PersonalDetails() {
    this.pdid = nextpdid++;
  }

  public boolean equals(Object obj) { 
    if (obj == this)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;

    PersonalDetails personaldetails = (PersonalDetails)obj;
    return ((personaldetails.phoneNumber2 == this.phoneNumber2) && 
        (personaldetails.faxNumber == this.faxNumber) && 
        (personaldetails.mobileNumber == this.mobileNumber) && 
        (personaldetails.passportId.equals(this.passportId)) && 
        (personaldetails.driverId.equals(this.driverId)) && 
        (personaldetails.noOfChildren == this.noOfChildren) && 
        (personaldetails.isMarried == this.isMarried) && 
        (personaldetails.creditCardId == this.creditCardId) && 
        (personaldetails.height == this.height) && 
        (personaldetails.weight == this.weight) && 
        (personaldetails.heightUnit == this.heightUnit) && 
        (personaldetails.weightUnit == this.weightUnit) &&
        (personaldetails.email2.equals(this.email2)) && 
        (personaldetails.birthPlace.equals(this.birthPlace)) && 
        (personaldetails.countryOfBirth.equals(this.countryOfBirth)));

  }

}

