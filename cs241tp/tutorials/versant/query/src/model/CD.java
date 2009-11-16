/** CD is also an merchendise.
 *  CD is identified by an alphanumeric ID.
 *  CD has a title.
 *  CD has one singer.
 *  CD contains one or more songs.
 *  CD may have an Image for its cover.
 */
package model;

import java.util.Vector;
import java.io.*;


public class CD extends Merchandise implements Serializable {
  public String id;                // Key
  public Singer singer;           // Association
  public Image coverImage;       // Aggregation


  public CD(String id, String title, int price) {
    this(id, title, price, null);
  }

  
  public CD(String id, String title, int price, Image coverImage) {
    super(title, price);
    this.id = id;
    this.coverImage = coverImage;
  }

  
  public void setSinger(Singer s) {
    if (this.singer != null)
      return;

    this.singer = s;
  }

  
  public String toString() {
    return "CD:" + this.id + " " + this.singer;
  }

  
  public String getId() {
    return this.id;
  }

  
  public boolean equals(Object obj) {         
    if(obj == this)
      return true;

    if(obj == null || getClass() != obj.getClass())
      return false;

    CD cd = (CD)obj;
    return cd.getId().equals(this.getId());   
  }
}
