/** Customer is a special person (and very important) who has placed
 *   one or more orders.
 *   He/she has an address for billing (that may differ from his/her
 *   address as a Person).
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class Customer extends Person implements Serializable { 
  public ArrayList orders;

  public Customer(int ssn, String name, Date dob, String gender, 
      Address addr, PersonalDetails details, Image photo) {
    super(ssn, name, dob, gender, addr, details, photo);
  }

  public void setOrders(ArrayList orders) {
    this.orders = orders;
  }

  public ArrayList getOrders() {
    return this.orders;
  }

  public String toString() {
    return super.toString() + "\n Orders [" + orders + "]";
  }

  public boolean equals(Object obj) { 
    System.out.println("check equal cust");

    if (obj == this)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;

 
      Customer cust = (Customer)obj;
      boolean sameOrders = true;

      if (cust.getOrders() != null)  
        sameOrders = cust.getOrders().equals(this.getOrders());

      return ((cust.getSSN() == this.getSSN()) &&
          (cust.getGender() == this.getGender()) &&
          sameOrders &&
          (cust.getAddress().equals(this.getAddress()))&&
          (cust.getName().equals(this.getName())) &&
          (cust.getDetails().equals(this.getDetails()))&&
          (cust.getPhoto().equals(this.getPhoto())));
  }
}
