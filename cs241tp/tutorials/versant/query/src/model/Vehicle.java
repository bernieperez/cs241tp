package model;

import java.util.*;

public class Vehicle {

  String color;
  String brand;


  public Vehicle() {
    color = "";
    brand = "";
  }

  
  public Vehicle(String col, String brd) {
    color = col;
    brand = brd;
  }

  
  public String getColor() {
    return color ;
  }

  public String getBrand() {
    return brand ;
  }

  
  public String toString() {
    String str = "";
    str	+=  brand + "(" + color + ")" ;

    return str ;
  }
}
