#include <stdio.h>
#include "car.h"


void Car::print () {
  printf ("Car:\n\t %14s %s\n\t %14s %s\n\t %14s %d\n\t %14s %.2f\n\t %14s %d\n\t %14s %d\n\t %14s %d\n",
	  "Manufacturer:",(char *)manufacturer,
	  "Model:", (char *)model,
	  "Year:", year,
	  "Retail Cost:", retailCost,
	  "Doors:", doors,
	  "Cylinders:", cylinders,
	  "Id:", id);
}


LinkVstr<Car> Car::findAll ()
{
  return PClassObject<Car>::Object().select (NULL,
					     FALSE,
					     NULL_PREDICATE);
}
