/*
 * SampleApplication.java
 *
 */

import com.intersys.objects.CacheDatabase;
import com.intersys.objects.Database;
import com.intersys.objects.Id;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SampleApplication
{
    public static void main (String[] args)
    {
        Database dbconnection = null;
        String url = "jdbc:Cache://localhost:1972/SAMPLES";
        String username = "_SYSTEM";
        String password = "SYS";
        Sample.Person person = null;

        try
            {
                // Connect to Cache on the local machine,
                //	in the SAMPLES namespace
                dbconnection =
                    CacheDatabase.getDatabase (url, username, password);

                // Open an instance of Sample.Person,
                // whose ID is read in from the console
                InputStreamReader isr = new InputStreamReader (System.in);
                BufferedReader br = new BufferedReader (isr);
                System.out.print ("Enter ID of Person object to be opened:");
                String strID = br.readLine ();

                // Use the entered strID as an Id and use that Id to
                // to open a Person object with the _open() method inherited
                // from the Persistent class. Since the _open() method returns
                // an instance of Persistent, narrow it to a Person by casting.
                person =
                    (Sample.Person) Sample.Person.open (dbconnection,
                        new Id (strID));

                // Fetch some properties of this object
                System.out.println ("Name: " + person.getName ());
                System.out.println ("City: " + person.getHome ().getCity ());

                // Modify some properties
                person.getHome ().setCity ("Ulan Bator");

                // Save the object to the database
                person.save ();

                // Report the new residence of this person */
                System.out.println ("New City: " + person.getHome ().getCity ());

                /* de-assign the person object */
                dbconnection.closeObject (person.getOref ());
                person = null;

                // Close the connection
                dbconnection.close ();

            }
        catch (Exception ex)
            {
                System.out.println ("Caught exception: "
                                    + ex.getClass ().getName ()
                                    + ": " + ex.getMessage ());
            }
    }
}

/*
 * End-of-file
 *
 */
