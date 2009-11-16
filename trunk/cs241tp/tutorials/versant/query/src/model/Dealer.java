package model;

import java.util.*;

public class Dealer {

  String name ;
  ArrayList vehicles;


  public Dealer() {
    this.name = "";
    this.vehicles= new ArrayList();
  }

  public Dealer(String name) {
    this.name = name;
    this.vehicles = new ArrayList();
  }

  public void addVehicle(Vehicle vehicle) {
    if (!vehicles.contains(vehicle)) 
      vehicles.add(vehicle);
  }

  public String toString ( ) {
    String str = "";
    Vehicle v;

    str += name + "\n\tVehicles: " ;

    for (int i = 0; i < vehicles.size(); i ++) 
      str += (Vehicle) vehicles.get(i) + ","; 
    
    return str;
  }
}

