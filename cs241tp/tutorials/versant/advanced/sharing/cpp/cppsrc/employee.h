#ifndef _employee_h
#define _employee_h 1

#include <cxxcls/pobject.h>

class Employee : public PObject {

protected:

  PString name;
  o_4b id;

public:

  Employee (PString aName,
	    o_4b anId) {
    VPP_CLASS_CONSTRUCTOR1 (Employee);
    name = aName;
    id = anId;
  }
  
  virtual ~Employee () {};

  virtual void print ();
 
  static LinkVstr<Employee>  findAll ();
};
#endif
