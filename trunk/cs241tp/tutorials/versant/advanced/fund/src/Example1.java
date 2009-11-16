
import com.versant.fund.*;
import java.util.Properties;

/** 
 * This simple example program demonstrates usage of FundSession in the fundamental  
 * layer.  It starts database session by creating a FundSession object, defines
 * the schema for Person class, creates and commits two instances of Person, then
 * performs a simple query to access one of the Person instance.
 */
public class Example1 {

public static void main( String[] argv ) 
{
    if (argv.length < 1) {
        System.out.println ("Usage: Example1 <db>");
        System.exit(-1);
    }
    
    try {

	/*
	* start the session
	*/

	// the database name is the first argument
	String db= argv[0];  

	// a capability is required to begin session
	Capability capability= new NewSessionCapability();

	// Properties is used to set options for beginning a session
	Properties properties = new Properties();
	properties.put("database", db);

	// begin session by instantiating FundSession
	FundSession s= new FundSession(properties, capability);


	/*
	*  Define database class "Person"
	*/

	// use Attr objects to describe the attributes
	AttrString name= s.newAttrString("name");
	AttrFloat weight= s.newAttrFloat("weight");

	// an array of AttrBuilders will be required
	AttrBuilder[] attrs= {
		s.newAttrBuilder(name).withBTreeIndex(),
		s.newAttrBuilder(weight) 
	};

	// define the class Person to the database
	System.out.println("Defining Person class to database...");
	ClassHandle	person = s.withAttrBuilders(attrs).defineClass("Person");
	
	// make a Person, using Attr objects to put attribute values 
	Handle janos= person.makeObject();
	janos.put( name, "Janos" );
	janos.put( weight, (float)14.0 );

	// make another Person
	Handle edina= person.makeObject();
	edina.put( name, "Edina" );
	edina.put( weight, (float)12.0 );

	// commit the transaction
	System.out.println("Creating two Person objects in database...");
	s.commit();

	/* 
	* perform a query 
	*   SELECT  FROM Person WHERE name starts with "J"
	*/

	// use the Attr object "name" to create a predicate
	Predicate p= name.matches( "J*" );

	// do the select, put result into an array of Handle
	System.out.println("Looking for Persons whose names start with 'J'...");
	Handle[] r= person.select(p).asArray();

	// print the results, using Attr objects to get attribute values
	for (int i=0; i<r.length; i++ ) {
		System.out.println("Found: " + r[i].get(name) + 
							" with weight " + r[i].get(weight)  +
							",  LOID=" + r[i].toString() ); 
	}


	/*
	* Drop the Peron class from database
	*/

	person.dropClass();
	System.out.println("Dropping Person class and its instances...");
		
	/*
	*  End the session
	*/

	s.endSession();

    } catch ( VException vex ) {

		// catch and print errors, and exit with bad status
		System.out.println("Caught exception: " + vex.toString() );
		System.exit(255);

    } // end try

  } // end main

} // end Demo1
