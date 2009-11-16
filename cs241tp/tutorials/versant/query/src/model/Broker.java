package model;

import java.util.*;

public class Broker {

  String name;
  ArrayList dealers;


  public Broker() {
    this.name = new String("");
    this.dealers = new ArrayList();
  }

  public Broker(String name) {
    this.name = name;
    this.dealers = new ArrayList();
  }

  public void addDealer(Dealer dealer) {
    if (!dealers.contains(dealer))
      dealers.add(dealer) ;
  }

  public String toString() {
    String str = "";
    Dealer d;	
    str +=   name + "\n" ; 

    for (int i = 0; i < dealers.size(); i ++) {
      d	= (Dealer)dealers.get(i); 
      str +=  " Dealer: " + d + "\n"; 
    }	
    return str;	
  }
}
