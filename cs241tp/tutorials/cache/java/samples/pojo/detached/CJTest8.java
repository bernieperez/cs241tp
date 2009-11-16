package pojo.detached;
/*
 * CJTest.java -- test Cache Java
 *
 */

import Sample.IAddress;
import Sample.ICompany;
import Sample.IEmployee;
import Sample.IPerson;
import Sample.PEmployee;

import java.util.List;
import java.util.Date;
import java.io.BufferedReader;
import java.io.PrintWriter;

import com.jalapeno.ObjectManager;
import com.jalapeno.ApplicationContext;

/**
 * This is a console program to test out Cache' Java 2.0
 *
 */
public class CJTest8 {
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
        String namespace = "SAMPLES";
        IEmployee        employee = null;

        String id = "101";
        String connectiontype = null;
        String mode = null;

        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];
            else if (args[i].equals("-namespace"))
                namespace = args[++i];
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
                String           url="jdbc:Cache://" + host + ":1972/" + namespace;
                objectManager = ApplicationContext.createObjectManager (url,
                    username, password, mode);
            } else
                objectManager = CJTest2.connect (connectiontype, host, username, password);

            employee =  (IEmployee) objectManager.openById (PEmployee.class, id);

            System.out.println( "Class: " + employee.getClass ().getName () );
            System.out.println( "ID: " + objectManager.getId(employee) );
            System.out.println( "Name: " + employee.getName() );
            System.out.println( "SSN:  " + employee.getSSN() );
            System.out.println( "DOB:  " + employee.getDOB() );
            System.out.println( "Age:  " + employee.getAge() );

            /* Attempt to bring in an embedded object */
            IAddress addr = employee.getHome();
            System.out.println( "Street: " + addr.getStreet() );
            System.out.println( "City:   " + addr.getCity() );
            System.out.println( "State:  " + addr.getState() );
            System.out.println( "Zip:    " + addr.getZip() );

            IPerson spouse = employee.getSpouse();
            if (spouse != null)
                System.out.println( "Spouse Name: " + spouse.getName() );

            ICompany company = employee.getCompany ();

            System.out.println("Works at: " + company.getName ());
            System.out.println("Title:  " + employee.getTitle());
            System.out.println("Salary: " + employee.getSalary());
            System.out.println("Notes: ");
            if (employee.getNotesIn() != null){
                BufferedReader notesReader = new BufferedReader (employee.getNotesIn());
                while (notesReader.ready ()){
                    String line = notesReader.readLine ();
                    if (line == null)
                        break;
                    System.out.println (line);
                }
            }
            new PrintWriter(employee.getNotesOut (), true).println ("Reviewed on " + new Date());


            List colleagues = company.getEmployees ();
            System.out.println("Colleagues: ");
            for (java.util.Iterator it = colleagues.iterator ();
                 it.hasNext();)
                System.out.println("\t" + ((IPerson)it.next()).getName());

            objectManager.save (employee, false);
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

