package pojo.detached;
/*
 * CJTest.java -- test Cache Java
 *
 */

import Sample.IAddress;
import Sample.IPerson;
import Sample.PPerson;


import java.util.List;

import com.jalapeno.ObjectManager;
import com.jalapeno.ApplicationContext;

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
    public static void main( String[] args ) {
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        IPerson    person = null;

        boolean noModify = false;
        String id = "1";

        String connectiontype = null;
        String mode = null;

        for (int i = 0; i < args.length; i++)
            if (args[i].equalsIgnoreCase("-user"))
                username = args[++i];
            else if (args[i].equalsIgnoreCase("-password"))
                password = args[++i];
            else if (args[i].equalsIgnoreCase("-host"))
                host = args[++i];
            else if (args[i].equalsIgnoreCase("-noModify"))
                noModify = true;
            else if (args[i].equalsIgnoreCase("-id"))
                id = args[++i];
            else if (args[i].equalsIgnoreCase("-connectiontype"))
                connectiontype = args[++i];
            else if (args[i].equalsIgnoreCase("-mode"))
                mode = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        try {
            /* Connect to this machine, in the SAMPLES namespace */

            ObjectManager objectManager;

            if (connectiontype == null || connectiontype.equalsIgnoreCase ("cache")){
                String  url="jdbc:Cache://" + host + ":1972/SAMPLES";
                objectManager = ApplicationContext.createObjectManager (url,
                    username, password, mode);
            } else
                objectManager = CJTest2.connect (connectiontype, host, username, password);

            /* Create a new instance of Sample.Person */
                person = (IPerson) objectManager.openById (PPerson.class, id);

            System.out.println( "Class: " + person.getClass ().getName () );
            System.out.println( "ID: "   + objectManager.getId(person) );
            System.out.println( "Name: " + person.getName() );
            System.out.println( "SSN:  " + person.getSSN() );
            System.out.println( "DOB:  " + person.getDOB() );
            System.out.println( "Age:  " + person.getAge() );

            /* Attempt to bring in an embedded object */
            IAddress addr = person.getHome();
            System.out.println( "Class:  " + addr.getClass ().getName () );
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            if (!noModify)
                {
                    System.out.println( "Modifying 'Home' ..." );
                    if ("One Memorial Drive".equals (addr.getStreet()))
                        {
                            addr.setStreet ("70 Tangier Lane");
                            addr.setCity ("Eton, Windsor");
                            addr.setState ("BE");
                            addr.setZip ("SL46B");
                        }
                    else
                        {
                            addr.setStreet ("One Memorial Drive");
                            addr.setCity ("Cambridge");
                            addr.setState ("MA");
                            addr.setZip ("02142");
                        }

                    System.out.println( "Street: " + addr.getStreet() );
                    System.out.println( "City:   " + addr.getCity() );
                    System.out.println( "State:  " + addr.getState() );
                    System.out.println( "Zip:    " + addr.getZip() );
                }

            IPerson spouse = person.getSpouse();
            if (spouse != null)
                System.out.println( "Spouse Name: " + spouse.getName() );


            // Now we will retrieve collection:
            System.out.println("Now we will retrieve collection: ");

            List colors = person.getFavoriteColors();
            for (int r = 0; r < colors.size(); r++) {
                System.out.println("   Element #" + r + " -> " + colors.get(r));
            }

            if (!noModify)
                {
                    System.out.println( "Modifying 'FavoriteColors' ..." );

                    /* Remove the first element */
                    String colorToAdd;
                    if (!colors.contains ("Red"))
                        colorToAdd = "Red";
                    else if (!colors.contains ("Blue"))
                        colorToAdd = "Blue";
                    else
                        colorToAdd = "Green";

                    if (colors.size() > 0)
                        colors.remove(0);

                    colors.add(colorToAdd);

                    /* Show the changes to the collection */
                    colors = person.getFavoriteColors();
                    for (int r = 0; r < colors.size(); r++) {
                        System.out.println("   Element #" + r + " -> " + colors.get(r));
                    }
                }

            objectManager.save (person, false);

            // Now we open the same object using SSN
            {
                System.out.println("Now we open the same object using SSN");
                String ssn = person.getSSN();

                ObjectManager objectManager2;

                if (connectiontype == null || connectiontype.equalsIgnoreCase ("cache")){
                    String  url="jdbc:Cache://" + host + ":1972/SAMPLES";
                    objectManager2 = ApplicationContext.createObjectManager (url,
                        username, password);
                } else
                    objectManager2 = CJTest2.connect (connectiontype, host, username, password);

                IPerson person2 = (IPerson) objectManager2.openByPrimaryKey (PPerson.class, ssn);
                System.out.println( "Class: " + person2.getClass ().getName () );
                System.out.println( "ID: "   + objectManager2.getId(person) );
                System.out.println( "Name: " + person2.getName() );
                System.out.println( "SSN:  " + person2.getSSN() );
                System.out.println( "DOB:  " + person2.getDOB() );

                IPerson person3 = (IPerson) objectManager.openByPrimaryKey (PPerson.class, ssn);
                System.out.println("Would we get the same object? " +
                                   person.equals (person3));
            }

            objectManager.close ();
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

