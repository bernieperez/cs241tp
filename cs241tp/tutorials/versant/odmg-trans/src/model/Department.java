/* Copyright (c) 1997-2005 Versant Corporation. All Rights Reserved */

package model;

import java.lang.*;
import com.versant.odmg3.DList;
import com.versant.odmg3.ListOfObject;


public class Department {

  String name;
  DList   staff;

  /**
   * the ctor
   */
  public Department (String aName) {
    name = aName;
    staff = new ListOfObject();
  }

  public Department () {
    name = "no name"; 
    staff = new ListOfObject();
  }

  public String name() {
    return name;
  }

  public DList staff () {
    return staff;
  }

  /**
   * returns the Department information
   */
  public String toString () {
    return (new String ("\n Name: " + name + "\n Staff: " + staff));
  }
}
