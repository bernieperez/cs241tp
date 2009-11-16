/*
 * CJTest.java -- test Cache Java
 *
 */

import com.intersys.objects.*;


/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest2 {
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


        boolean isQuick = false;
        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-quick"))
                isQuick = true;
            else if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];

        String           url="jdbc:Cache://" + host + ":1972/SAMPLES";

        try {
            /* Connect to this machine, in the SAMPLES namespace */
            if (isQuick)
                dbconnection = CacheDatabase.getLightDatabase (url, username, password);
            else
                dbconnection = CacheDatabase.getDatabase (url, username, password);


            Sample.Person.checkAllFieldsValid(dbconnection);

            /* Create a new instance of Sample.Person */
            person = new Sample.Person( dbconnection );

            /* Set some properties */
            person.setName("Doe, Joe A");
            person.setSSN(generateSSN ());

            /* Fetch some properties */
            System.out.println( "Name: " + person.getName() );
            System.out.println( "SSN:  " + person.getSSN() );
            // empty field
            System.out.println( "DOB:  " + person.getDOB() );

            // Save instance of Person
            person.save();

            System.out.println ("Saved id: " + person.getId());

            /* de-assign the person object */
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

    private static java.util.Random random;
    private static String generateSSN ()
    {
        if (random == null)
            random = new java.util.Random ();

        StringBuffer sb = new StringBuffer ();
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append('-');
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append('-');
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        
        return sb.toString();
    }
}

/*
 * End-of-file
 *
 */

