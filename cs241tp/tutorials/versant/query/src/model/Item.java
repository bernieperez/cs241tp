
/** Items are short-lived representation of a merchendise
 *  (not by inheritence but by delegation) and also has a
 *  number of quantity of the merchendise.
 *  They live as long as an Order is NEW or PENDING and destroyed when
 *  SHIPPED.
 */
package model;
import java.io.*;

public class Item implements Serializable {
  private long itemid;
  private static long nextitemid = Math.round(Math.random() * 1000000);
  protected int quantity;
  protected Merchandise item;

  public Item(Merchandise item, int quantity) {
    this.item  = item;
    this.quantity = quantity;
    this.itemid = nextitemid++;
  }

  public Merchandise getMerchandise() {
    return this.item;
  }

  public int getQuantity() {
    return this.quantity;
  }
}

