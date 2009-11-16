/** Chapter is a specialized content with a heading, a number (which
 *  is alphanumeric string)
 *  Chapter belongs to a Book. The reference is only maintained by the Book.
 *  Chapter is an examplar for SCO
 */
package model;

import java.io.Serializable;

public class Chapter implements Serializable {
  private long chapid;
  private static long nextchapid = Math.round(Math.random() * 1000000);
  public String heading;
  public StringBuffer text;

  public Chapter(int size, String heading) {
    this.heading = heading;
    this.text = new StringBuffer(size);
    this.chapid = nextchapid++;
  }
}

