#include <stdlib.h>
#include <string.h>
#include "floorPlan.h"
#include "employee.h"


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
      Employee *emp1 = O_NEW_PERSISTENT (Employee) ("John Davis",
						    182);
      Employee *emp2 = O_NEW_PERSISTENT (Employee) ("Beth Sterns",
						    206);
      Employee *emp3 = O_NEW_PERSISTENT (Employee) ("Steve Harmon",
						    336);
      Employee *emp4 = O_NEW_PERSISTENT (Employee) ("Seth Goldman",
						    688);
      FloorPlan *fp1 = O_NEW_PERSISTENT (FloorPlan) ("Acounting");
      fp1->addEmployee (1, emp1);
      fp1->addEmployee (3, emp2);
      fp1->addEmployee (11, emp3);
      fp1->addEmployee (18, emp4);

      fp1->print ();
    }
    
    else {
      LinkVstr<FloorPlan> fpcsr = FloorPlan::findAll ();
      for (o_u4b i = 0; i < fpcsr.size (); i++) {
	printf ("Found: ");
	fpcsr[i]->print ();
      }
      fpcsr.release ();
    }
    
    dom->commit ();
    dom->endsession ();
  } catch (PError& err) {
    err.print ();
    exit (1);
  }
  
  return 0;
}
