
/** Typical example of an SCO.
 *  This class is maintained to control the size of an instance by loading
 *  arbitrary size of an image
 */
package model;

import java.util.*;
import java.io.Serializable;

public class Image implements Serializable {

  private long imageid;
  private static long nextimageid = Math.round(Math.random() * 1000000);

  Byte[] data;
  String format;

  public Image() {
    this("GIF", 20480);
  }

  public Image(int size) {
    this("GIF", size);
  }

  public Image(String format, int size) {
    this.format = format;
    this.data = new Byte[size];
    imageid = nextimageid++;
  }

  public void setData(int size) {
    this.data = new Byte[size];
  }

  public void setFormat(String format) {
    this.format=format;
  }

  public boolean equals(Object obj) {     
    if (obj == this)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;

    Image image = (Image)obj;

    return ((Arrays.equals(image.data, this.data)) &&
        (image.format.equals(this.format)));
  }


}

