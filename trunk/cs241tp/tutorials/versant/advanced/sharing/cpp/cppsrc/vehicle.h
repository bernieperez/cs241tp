#ifndef _vehicle_h
#define _vehicle_h 1

#include <cxxcls/pobject.h>

class Vehicle : public PObject {

protected:

  PString   manufacturer;
  PString   model;
  o_4b      year;
  o_double  retailCost;

public:

  Vehicle (PString aManufacturer,
	   PString aModel,
	   o_4b anYear,
	   o_double aRetailCost) {
    VPP_CLASS_CONSTRUCTOR1 (Vehicle);
    manufacturer = aManufacturer;
    model = aModel;
    year = anYear;
    retailCost = aRetailCost;
  }
  
  virtual ~Vehicle () {};

  virtual void print ();
 
  static LinkVstr<Vehicle>  findAll ();
};


#endif
