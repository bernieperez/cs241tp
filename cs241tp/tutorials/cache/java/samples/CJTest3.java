/*
 * CJTest.java -- test Cache Java
 *
 */

import com.intersys.objects.*;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest3 {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args )
        throws Exception
    {
        Database         dbconnection = null;
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        Sample.Person    person = null;
        Sample.IAddress   addr = null;
        Id              id = null;

        boolean isQuick = false;
        boolean isReadOnly = false;
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-quick"))
                isQuick = true;
            else if (args[i].equals("-ro"))
                isReadOnly = true;
            else if (args[i].equals("-id"))
                id = new Id (args[++i]);
            else if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }


        String           url="jdbc:Cache://" + host + ":1972/SAMPLES";

        try {
            /* Connect to this machine, in the SAMPLES namespace */
            if (isQuick)
                dbconnection = CacheDatabase.getLightDatabase (url, username, password);
            else if (isReadOnly)
                dbconnection = CacheDatabase.getReadOnlyDatabase (url, username, password);
            else
                dbconnection = CacheDatabase.getDatabase (url, username, password);


            System.out.println( "Connected." );

            Sample.Person.checkAllFieldsValid(dbconnection);

            /* Open an instance of the Sample.Person object */
            if (id == null)
                id = new Id( 1 );

            if (!(Sample.Person.exists (dbconnection, id)))
                {
                    System.out.println ("There is no Person with id " + 
                                        id + " in the database.");
                    dbconnection.close();
                    return;
                }

            person = (Sample.Person) Sample.Person._open( dbconnection, id );

            /* Fetch some properties */
            System.out.println( "ID: " + person.getId() );
            if (!isReadOnly && !isQuick)
                {
                    String[] internalValues = person.dumpZobjVal ();
                    System.out.print ("[");
                    for (int i = 0; i < internalValues.length; i++)
                        {
                            System.out.print ("<" + internalValues[i] + ">");
                        }
                    System.out.println ("]");
                }
            System.out.println( "Name: " + person.getName() );
            System.out.println( "SSN:  " + person.getSSN() );
            System.out.println( "DOB:  " + person.getDOB() );
            System.out.println( "Age:  " + person.getAge() );

            /* Attempt to bring in an embedded object */
            addr = person.getHome();
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            Sample.IPerson spouse = person.getSpouse();
            if (spouse != null)
                System.out.println( "Spouse Name: " + spouse.getName() );

            if (!isReadOnly){
                person.setName(person.getName());
                person.save();
            }

            /* un-assign the person object */
            dbconnection.closeObject(person.getOref());
            person = null;

            /* Close the connection */
            dbconnection.close();

        } catch (CacheException ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName() + ": " + ex.getMessage() );
            ex.printFullTrace(System.out);
        } catch (Exception ex) {
            System.out.println( "Caught exception: " + ex.getClass().getName() + ": " + ex.getMessage() );
            ex.printStackTrace();
        }
    }
}

/*
 * End-of-file
 *
 */

