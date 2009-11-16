#ifndef _car_h
#define _car_h 1

#include "vehicle.h"

class Car : public Vehicle {

protected:

  o_4b doors;
  o_4b cylinders;
  o_u4b id;

public:

  Car (PString aManufacturer,
       PString aModel,
       o_4b anYear,
       o_double aRetailCost,
       o_4b numDoors,
       o_4b numCyl,
       o_u4b anId)
	   : Vehicle (aManufacturer, aModel, anYear, aRetailCost) {
    VPP_CLASS_CONSTRUCTOR1 (Car);
    doors = numDoors;
    cylinders = numCyl;
    id = anId;
  }
  
  virtual ~Car (){}; 

  virtual void print ();
 
  static LinkVstr<Car>  findAll ();
};
#endif
