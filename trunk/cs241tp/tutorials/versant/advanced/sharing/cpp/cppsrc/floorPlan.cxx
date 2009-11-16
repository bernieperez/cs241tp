#include <stdio.h>
#include "floorPlan.h"


void FloorPlan::print () {
  printf ("FloorPlan: %s\n", (char *)name);
  int numCubes = cubesAssigned.size ();
  for (int i = 0; i < numCubes; i++) {
	Employee &emp = cubesAssigned.index_value (i);
	printf ("Cube No: %2d ", cubesAssigned.index_key (i)); 
	emp.print ();
  }
  printf ("\n");
}


void FloorPlan::addEmployee (o_4b cubeNo, Employee *anEmp)
{
  cubesAssigned.add (cubeNo, *anEmp);
}


LinkVstr<FloorPlan> FloorPlan::findAll ()
{
  return PClassObject<FloorPlan>::Object().select (NULL,
						   FALSE,
						   NULL_PREDICATE);
}
