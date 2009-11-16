#include <stdio.h>
#include "vehicle.h"


void Vehicle::print () {
  printf ("Vehicle:\n\t %14s %s\n\t %14s %s\n\t %14s %d\n\t %14s %.2f\n",
	  "Manufacturer:", (char *)manufacturer,
	  "Model:", (char *)model,
	  "Year:", year,
	  "Retail Cost:", retailCost);

}


LinkVstr<Vehicle> Vehicle::findAll ()
{
  return PClassObject<Vehicle>::Object().select (NULL,
						 FALSE,
						 NULL_PREDICATE);
}
