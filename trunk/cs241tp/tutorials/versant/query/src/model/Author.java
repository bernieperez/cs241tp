package model;

import java.util.ArrayList;
import java.sql.Date;
import java.io.*;

public class Author extends AbstractPerson implements Serializable {
  protected ArrayList books;
  
  public Author(String name, Date dob, String gender) {
    super(name, dob, gender);
    this.books = new ArrayList();
  }
  
  /** Adds a book to this author
   *  Ignores silently if the book is already written by this author.
   */
  public void addBook(Book b) {
    if (!this.books.contains(b)) 
      this.books.add(b);
  }
  
  public ArrayList getBooks() {
    return this.books;
  }
  
  public String toString() {
    return "Author:" + this.name + "  " + "Date of Birth : " + this.dateOfBirth;
  }

  public boolean equals(Object obj) {     
    if (obj == this)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;

    Author auth = (Author)obj;
    return auth.getName().equals(this.getName()); 
  }

}
