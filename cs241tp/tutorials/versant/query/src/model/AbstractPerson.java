package model;

import java.util.Date;
import java.io.*;


public  class AbstractPerson implements Serializable {
  private long personid;
  protected String name;
  protected String gender;
  protected   Date dateOfBirth;
  static private long nextpersonid = Math.round(Math.random() * 1000000) + 100;

  
  public AbstractPerson(String name, Date dob, String gender) {
    this.name = name;
    this.dateOfBirth = dob;
    this.gender = gender;
    personid = nextpersonid++;
  }
  
  
  public AbstractPerson() {
    personid = nextpersonid++;
  }

  
  public long getPersonid() {
    return personid;
  }

  
  public final String getName() {
    return this.name;
  }
  
  
  public final String getGender() {
    return this.gender;
  }
  
  
  public final Date getDateOfBirth() {
    return this.dateOfBirth;
  }
  

  public String toString() {
    return "Name=" + this.name + 
    ", Gender=" + this.gender + 
    ", Birthday=" + dateOfBirth;
  }
}

