#include <stdlib.h>
#include <string.h>
#include "vehicle.h"


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
      Vehicle *v = O_NEW_PERSISTENT (Vehicle) ("Nissan",
					       "Maxima",
					       1998,
					       24886.80);
      v->print ();
    }
    
    else {
      LinkVstr<Vehicle> vcsr = Vehicle::findAll ();
      for (o_u4b i = 0; i < vcsr.size (); i++) {
	printf ("Found: ");
	vcsr[i]->print ();
      }
      vcsr.release ();
    }
    
    dom->commit ();
    dom->endsession ();
  } catch (PError& err) {
    err.print ();
    exit (1);
  }
  
  return 0;
}
