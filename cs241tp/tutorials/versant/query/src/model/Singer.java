
/** Singer is a special person who has produced one or more CDs.
 */
package model;

import java.util.Date;
import java.util.ArrayList;
import java.io.*;

public class Singer extends Person implements Serializable {
  public ArrayList cds;

  public Singer(int ssn, String name, Date dob, String gender, 
      Address addr, PersonalDetails details, Image photo) {
    super(ssn, name, dob, gender, addr, details, photo);
    this.cds = new ArrayList();
  }
  
  public Singer(int  ssn, String name, Date dob, String gender) {
    super(ssn, name, dob, gender, new Address(), new  PersonalDetails(), new Image() );
    this.cds = new ArrayList();
  }
  
  
  public void addCD(CD c) {
    if (!this.cds.contains(c)) 
      this.cds.add(c);
  }
  
  
  public String toString() {
    return "Name : " + this.name + "  " + "Date of Birth : " + this.dateOfBirth ;
  }
  
  
  public boolean equals(Object obj) {     
 
    if (obj == this)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;

    Singer singer = (Singer)obj;
    return (singer.getName().equals(this.getName()));
  }

}
