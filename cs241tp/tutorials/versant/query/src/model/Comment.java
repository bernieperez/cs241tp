
/** Comment is an example of an association class. A book gets associated
 *  to a Customer via a comment i.e. when a Customer comments on a book.
 */
package model;
import java.io.*;

public class Comment implements Serializable {
  protected long commid;
  protected short rating;
  protected StringBuffer comment;
  protected Book commentedOn;      // Association
  protected Customer commentedBy;      // Customer who made this

  public Comment(short rating, StringBuffer commentText, 
                 Book book, Customer customer) {
    this.rating = rating;
    this.comment = commentText;
    this.commentedOn = book;
    this.commentedBy = customer;
  }
}

