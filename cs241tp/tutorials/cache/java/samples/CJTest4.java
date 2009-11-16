/*
 * CJTest4.java -- test Cache Java
 *
 */

import com.intersys.objects.*;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest4 {
    /**
     * The main entry point for the test program
     *
     * @param args Array of parameters passed to the application
     * via the command line.
     */
    public static void main( String[] args ) {
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
            else
                dbconnection = CacheDatabase.getDatabase (url, username, password);

            System.out.println( "Connected." );

            //Sample.Person.checkAllFieldsValid(dbconnection);

            /* Open an existing instance of Sample.Person */
            if (id == null)
                id = new Id( 1 );
            
            person = (Sample.Person) Sample.Person._open( dbconnection, id);

            /* Fetch some properties */
            System.out.println( "Name: " + person.getName() );
            System.out.println( "SSN:  " + person.getSSN() );

            /* Display existing values */
            System.out.println( "Existing Address:" );
            addr = person.getHome();
            addr = person.getHome();
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            /* Modify some values */
            addr.setStreet( "One Memorial Drive" );
            addr.setCity( "Cambridge" );
            addr.setState( "MA" );
            addr.setZip( "02142" );

            /* Display the new values */
            System.out.println( "New Address:" );
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            /* Save the changes we made to this object instance.

               If there is a problem with the save, such as a key value not
               being unique, then an exception will be thrown and caught by the
               catch() block.
            */
            person.save();

            /* de-assign the person object */
            dbconnection.closeObject(person.getZRef());

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

