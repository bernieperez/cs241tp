/* Copyright (c) 1997-2005 Versant Corporation. All Rights Reserved */

package model;

import java.lang.*;
import com.versant.odmg3.*;

public class DepartmentWithDList 
extends Department {

  DList temps;

  /**
   * the ctor
   */
  public DepartmentWithDList (String aName) {
    name = aName;
    temps = new ListOfObject ();
  }

  public DList temps () {
    return temps;
  }

  /**
   * returns the Department information
   */
  public String toString () {
    return (new String ("\n Name: " + name + "\n Staff: " + staff +
			"\n Temps: " + temps));
  }
}
