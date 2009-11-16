package model;

import java.util.Vector;
import java.util.ArrayList;
import java.io.*;

public class Book extends Merchandise implements Serializable {
  protected String isbn;              // Primary Key
  protected ArrayList authors;           // Association
  protected Vector chapters;          // Aggregation
  protected Image coverImage;        // Aggregation

  public String getISBN() {
    return this.isbn;
  }

  public Book(String isbn, String title, int price, 
              int nChapter, Image coverImage) {
    super(title, price);
    
    this.isbn = isbn;
    this.authors = new ArrayList();
    this.chapters = new Vector(nChapter);
    
    for (int i = 0; i < nChapter; i++) 
      this.chapters.addElement(new Chapter(64, "Chapter " + i));
    
    this.coverImage = coverImage;
  }

  public void addAuthor(Author a) {
    if (!this.authors.contains(a)) 
      this.authors.add(a);
  }
  
  public String toString() {
    return "Book:" + this.isbn;
  }

  public boolean equals(Object obj) {
    if (obj == this)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;

    Book book = (Book)obj;
    return (book.getISBN().equals(this.getISBN()));   

  }
}
