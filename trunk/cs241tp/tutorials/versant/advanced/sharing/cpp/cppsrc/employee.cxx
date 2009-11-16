#include <stdio.h>
#include "employee.h"


void Employee::print () {
  printf ("Employee: %s %20s   %s %5d\n",
	  "Name:", (char *)name, "Id:", id);
}


LinkVstr<Employee> Employee::findAll ()
{
  return PClassObject<Employee>::Object().select (NULL,
						  FALSE,
						  NULL_PREDICATE);
}
