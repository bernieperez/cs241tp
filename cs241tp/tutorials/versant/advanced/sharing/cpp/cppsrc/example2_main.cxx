#include <stdlib.h>
#include <string.h>
#include "car.h"


int main (int argc, char** argv)
{
  if (argc < 2) {
    printf ("Usage: %s <dbname> [create]\n", argv[0]);
    return 1;
  }
  
  try {
    ::dom= new PDOM;
    ::dom->beginsession (argv[1], NULL);
    
    if ((argc > 2) && !(strcmp (argv[2], "create"))) {
      Car *c = O_NEW_PERSISTENT (Car) ("Volvo",
				       "860",
				       1996,
				       27660.00,
				       4,
				       6,
				       187828);
      c->print ();
    }
    
    else {
      LinkVstr<Car> ccsr = Car::findAll ();
      for (o_u4b i = 0; i < ccsr.size (); i++) {
	printf ("Found: ");
	ccsr[i]->print ();
      }
      ccsr.release ();
    }
    
    dom->commit ();
    dom->endsession ();
  } catch (PError& err) {
    err.print ();
    exit (1);
  }
  
  return 0;
}
