
import com.versant.fund.*;
import java.util.Properties;
import java.lang.Math;

/**
 * This	example	programs demonstrate usage of some of the methods in HandleVector
 * that	operate	on a group of database objects.	 Group operation helps reduce
 * network overhead	and	improve	performance.
 */
public class GroupApiExample
{

public static void main	(String[] args)
{
    int i;
    
    if (args.length < 1) {
        System.out.println ("Usage: GroupApiExample <db>");
        System.exit(-1);
    }

    // begin session by	instantiating FundSession
	String db= args[0];	 
	Capability capability= new NewSessionCapability();
	Properties properties =	new	Properties();
	properties.put("database", db);
	FundSession session = new FundSession(properties, capability);

    // instantiate 100 Student objects in the database, then commit
    // to flush the object cache.
    int count = 100;
	populate (session, count);
	System.out.println ("Finished populating Student and Course objects, committing ...");
	session.commit();
	
	// select all Student objects and place their handles in a HandleVector
	ClassHandle studentClass = session.locateClass ("Student");
	HandleVector allStudents = session.newHandleVector (studentClass.select());

    // select the Course named "Economics"
    ClassHandle courseClass = session.locateClass ("Course");
    Predicate pred = session.newAttrString("name").eq("Economics");
    Handle[] resultArray = courseClass.select(pred).asArray();
    Handle econCourse = (resultArray.length > 0) ? resultArray[0] : null;
    
    // bring all Student instances into object cache with group read, 
    // set read lock on each student object
	allStudents.groupReadObjects (db, 0, Constants.RLOCK);

	// delete some Students using group delete; this is faster
	// than deleting an object at a time
	HandleVector toDelete = session.newHandleVector();
	for (i = 0; i < allStudents.size(); i ++) {
	    if (i % 3 == 0)
	        toDelete.addHandle(allStudents.handleAt(i));
	}
	System.out.println ("Group deleting " + toDelete.size() + " Student objects");
	toDelete.groupDeleteObjects (db);
	
	
	// modify some students, then write them to database using
	// group write with option to release these objects from
	// the object cache
	HandleVector toModify = session.newHandleVector();
	AttrHandleArray courses = session.newAttrHandleArray("courses");
	    
	for (i = 0; i < allStudents.size(); i ++) {
	    if (i % 3 == 1) {
	        Handle studentHandle = allStudents.handleAt(i);
	        toModify.addHandle (studentHandle);
	        // the student now takes one course
	        Handle[] courseArray = { econCourse };
	        studentHandle.put (courses, courseArray);
	    } //if
	} //for
	System.out.println ("Group writing " + toModify.size() + " Student objects");
	toModify.groupWriteObjects (Constants.FREE_OBJECTS);

	
	// release the rest of Student objects from object cache 
	// since we won't work on them any more
	HandleVector toRelease = session.newHandleVector(); 
        for (i = 0; i < allStudents.size(); i++) {
            if (i % 3 == 2)
                toRelease.addHandle (allStudents.handleAt(i));
        }
	System.out.println ("Releasing " + toRelease.size() + 
	                    " Student objects from VERSANT object cache");
	toRelease.releaseObjects (0);
	
	
	// calculate objects that are reachable from a starting-point object;
        // note that the HandleVector returned by getClosure() also includes
        // the starting-point object.
	HandleVector toGetClosure = session.newHandleVector ();
	toGetClosure.addHandle (allStudents.handleAt(1));
	HandleVector shallowClosure = toGetClosure.getClosure 
                                      (db, 1, false, Constants.RLOCK);
	HandleVector deepClosure = toGetClosure.getClosure
                                      (db, -1, false, Constants.RLOCK);
	System.out.print ("Student(id = 1) references " + 
                        (shallowClosure.size()-1) + " object(s) directly" );
	System.out.println (" and " + (deepClosure.size()-1) + 
                        " object(s) altogether");
	
        // commit the transaction, and end the session
	session.endSession();
	
} //main


/** helper method to instantiate a specified number of student objects
 *  and one Course object
 */
static void populate (FundSession session, int num)
{
	int i;
	ClassHandle	studentCls;
	AttrInt id = session.newAttrInt("id");
	AttrHandleArray courses = session.newAttrHandleArray("courses");
	
	// define schema for Student 
	try	{
		studentCls	= session.locateClass ("Student");
	}
	catch (VException vex) {
	    if (vex.getErrno() == 6002)	{ // Class Undefined
			AttrBuilder	attrs[]	= {
				session.newAttrBuilder(id),
				session.newAttrBuilder(courses)
			};
		    studentCls	= session.withAttrBuilders(attrs).defineClass ("Student");
			System.out.println ("Defining Student class");
		} //if
		else
			throw vex;
	} // catch
		
	// create some Student objects
	System.out.println ("Creating Student objects");
	for	(i=0; i<num; i++) {
	    Handle h = studentCls.makeObject();
		h.put (id, i);
	}
	
	// define schema for Course
	ClassHandle courseCls;
	AttrString name = session.newAttrString ("name");
	AttrString description = session.newAttrString ("description");
	AttrHandle prerequisite = session.newAttrHandle ("prerequisite");
	
	try	{
		courseCls	= session.locateClass ("Course");
	}
	catch (VException vex) {
	    if (vex.getErrno() == 6002)	{ // Class Undefined
			AttrBuilder	attrs[]	= {
				session.newAttrBuilder(name),
				session.newAttrBuilder(description),
                                session.newAttrBuilder(prerequisite)
			};
		    courseCls	= session.withAttrBuilders(attrs).defineClass ("Course");
			System.out.println ("Defining Student class");
		} //if
		else
			throw vex;
	} // catch
	
	// instantiate two Course objects
	Handle acct = courseCls.makeObject();
	acct.put (name, "Accounting");
	
	Handle econ = courseCls.makeObject();
	econ.put (name, "Economics");
	econ.put (prerequisite, acct);
	

} // populate


} //class

