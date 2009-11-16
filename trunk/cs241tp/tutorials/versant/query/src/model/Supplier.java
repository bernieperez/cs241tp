
package model;

import java.util.Iterator;
import java.util.HashMap;
import java.io.*;

public class Supplier implements Serializable {
  protected String name;  // Key
  protected transient HashMap pendingRequest;  // List of Merchandise to be supplied (Key:Merchandise Value:Quantity)

  public Supplier(String name) {
    this.name = name;
    this.pendingRequest = new HashMap();
  }

  public void requestForSupply(Merchandise m, int quantity) {
    int request = 0;
    if (null != this.pendingRequest.get(m)) 
      request = ((Integer)this.pendingRequest.get(m)).intValue();

    this.pendingRequest.put(m, new Integer(quantity + request));
  }

  public void supply() {
    Iterator iter = this.pendingRequest.keySet().iterator();
    while (iter.hasNext()) {
      Merchandise m = (Merchandise)iter.next();
      int q = ((Integer)this.pendingRequest.get(m)).intValue();
      m.addToStock(q);
    }
  }

  public String toString() {
    return "Name :" + this.name;
  }
}

