#ifndef _floor_plan_h
#define _floor_plan_h 1

#include <cxxcls/pobject.h>
#include <cxxcls/vdiction.h>
#include "employee.h"


class FloorPlan : public PObject {

protected:

  PString name;
  VEIDictionary<o_4b,Employee> cubesAssigned;

public:

  FloorPlan (PString aName) {
    VPP_CLASS_CONSTRUCTOR1 (FloorPlan);
    name = aName;
  }
  
  virtual ~FloorPlan () {};

  virtual void print ();

  void addEmployee (o_4b cubeNo, Employee* emp);

  static LinkVstr<FloorPlan>  findAll ();
};
#endif
