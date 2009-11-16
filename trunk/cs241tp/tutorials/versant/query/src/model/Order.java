
/** Order is a collection of Items.
 *  Total price of the order is the price of individual item.
 *  Order is shipped to an address.
 *  Data of order placement and shippment are known to the order.
 */
package model;

import java.util.ArrayList;
import java.sql.Time;
import java.sql.Timestamp;

import java.io.*;

public class Order implements Serializable {
  private long orderid;
  private static long nextorderid = Math.round(Math.random() * 1000000);
  protected long totalPrice;       // Price of Merchandise is integer not float
  protected Time shippedOn;
  protected Timestamp placedOn;
  protected Address shippingAddress;
  protected int status;
  protected Customer placedBy;           // Association
  protected ArrayList items;              // Aggregation

  public static final int NEW = 0;
  public static final int PENDING = -1;
  public static final int SHIPPABLE = 1;
  public static final int SHIPPED = 2;

  public Customer getPlacedBy() {
    return this.placedBy;
  }

  public Order(Customer customer, ArrayList items) {
    this.placedBy = customer;
    this.placedOn = new Timestamp(System.currentTimeMillis());
    this.shippingAddress = customer.getAddress();
    this.status = NEW;
    this.items = new ArrayList(items);
    this.totalPrice = calculateTotalPrice();
    orderid = nextorderid++;
  }

  public Order() {
    orderid = nextorderid++;
  }

  public ArrayList getItems() {
    return this.items;
  }

  public void setItems(ArrayList items) {
    this.items = items;
  }
  
  private long calculateTotalPrice() {
    long price = 0;
    for (int i = 0; i < this.items.size(); i++) {
      Item item = (Item) this.items.get(i);
      try {
        price += item.getMerchandise().getPrice() * item.getQuantity();
      }
      catch(NullPointerException e) { 
        System.out.println("Exception : " + e); 
      }
    }

    return price;
  }
  
  public long getTotalPrice() {
    return this.totalPrice;
  }
 
  private void setStatus(int newStatus) throws IllegalStateException {
    switch (this.status) {
      case Order.NEW:
        break;
      case Order.PENDING:
        if (newStatus == NEW) 
          throw new IllegalStateException("PENDING Order can not be transited to NEW");
        break;
      case Order.SHIPPED:
        if (newStatus == NEW || newStatus == PENDING) 
          throw new IllegalStateException("SHIPPED Order can not be transited to NEW or PENDING");
        break;
      default:
        throw new IllegalStateException("Unidentified Order status " + newStatus);
    }
    this.status = newStatus;
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public void ship() {
    this.status = SHIPPABLE;
    
    for (int i = 0; i < this.items.size(); i++) {
      Item item = (Item)this.items.get(i);
      Merchandise m = item.getMerchandise();
      int q = item.getQuantity();

      if (m.reserveCopies(q)) 
        this.items.remove(item);
      else {        
        Supplier supplier = m.getSupplier();
        supplier.requestForSupply(m, q);
        this.status = PENDING;
      }

    }
    if (this.status == SHIPPABLE) {
      this.shippedOn = new Time(System.currentTimeMillis());
      this.status = SHIPPED;
      this.items.clear();
    }
  }

  public String toString() {
    return ("Total price of Order : "+ this.totalPrice );
  }

  public boolean equals(Object obj) {     
    if (obj == this)
      return true;

    if(obj == null || getClass() != obj.getClass())
      return false;

    Order order = (Order)obj;
    if (order == null)
      return false;


    return ((order.totalPrice == this.totalPrice) && 
        (order.shippedOn.equals(this.shippedOn)) && 
        (order.placedOn.equals(this.placedOn)) && 
        (order.shippingAddress.equals(this.shippingAddress)) && 
        (order.status == this.status) && 
        (order.placedBy.equals(this.placedBy)) && 
        (order.items.equals(this.items))); 
  }
  
}

