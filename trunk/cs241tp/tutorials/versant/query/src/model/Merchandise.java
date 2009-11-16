
/** Merchendise is the abstract entity that holds the common attributes
 *  of something sold by this store
 */
package model;
import java.io.*;


public  class Merchandise implements Serializable {
  private long merchid;
  static private long nextmerchid = Math.round(Math.random() * 1000000) * 100;
  public String title;
  public int price;  // Price is maintained as integer because Verification programs based
                     // on price calculation will not get into float comparison problem
  public int copiesSold;
  public int copiesSupplied;
  public Supplier supplier;     // Association

  
  public Merchandise(String title, int price) {
    this.title = title;
    this.price = price;
    merchid = nextmerchid++;
  }

  
  public Merchandise() {
    merchid = nextmerchid++;
  }

  
  public String getTitle() {
    return this.title;
  }
  
  
  public int getPrice() {
    return this.price;
  }
  
  
  public Supplier getSupplier() {
    return this.supplier;
  }
  
  
  public int getCopiesSold() {
    return this.copiesSold;
  }
  
  
  public void setCopiesSold(int copiesSold) {
    this.copiesSold = copiesSold;
  }
  
  
  public int getCopiesSupplied() {
    return this.copiesSupplied;
  }

  
  public String toString() {
    return("Title : " + this.title + "   " + "Price : " + this.price + "  " + 
        " Supplier : " + this.supplier.toString());
  }

  
  public void setSupplier(Supplier s)  {
    if (this.supplier != null) 
      return;
    
    this.supplier = s;
  }

  
  /** Reserve copies of the merchadise.
   *  Increase the number of copies in sold
   * @return false if requested copies are not in stock
   */
  public synchronized boolean reserveCopies(int n) {
    if (n < 0) 
      throw new IllegalArgumentException("Can not reserve " + n + 
                                         " copies to stock of " + this);  
    int copiesInStock = this.copiesSupplied - this.copiesSold;
    
    if (n <= copiesInStock) {
      this.copiesSold += n;
      return true;
    }
    
    return false;
  }
  
  
  /** 
   * Adds copies to supplied copies
   */
  public int addToStock(int n) throws IllegalArgumentException {
    if (n < 0) 
      throw new IllegalArgumentException("Can not add " + n + 
          " copies to stock of " + this);
    
    this.copiesSupplied += n;
    return this.copiesSupplied;
  }




}

