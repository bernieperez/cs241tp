package clientObjects;

import com.intersys.objects.*;
import com.intersys.objects.reflect.CacheClass;
import com.intersys.objects.reflect.CacheMethod;
import com.intersys.objects.reflect.CacheField;
import com.intersys.classes.*;
import com.intersys.jdbc.SysListProxy;

import java.io.PrintStream;
import java.util.*;
import java.sql.*;

public class DynamicClientObjects
{
	static PrintStream testOut = System.out;
    private static CacheClass addressClass;
    private static CacheClass customerClass;

    private static CacheField streetField;
    private static CacheField cityField;
    private static CacheField stateField;
    private static CacheField zipField;

	public static void main(String[] args)
        throws Exception
	{
        boolean client = true;//false;//true;
        String url = "jdbc:Cache://localhost:1972/SAMPLES/jdbc.log";
        Database db = CacheDatabase.getDatabase(url, "_SYSTEM", "SYS");
        ObjectServerInfo connectionMetaData = db.getServerInfo ();
        boolean amIConnectedTo51;
        if (connectionMetaData.getMajorObjectVersion () < 5)
            {
                throw new Exception ("Will not work with old servers.");
            }
        else if (connectionMetaData.getMinorObjectVersion () < 1)
            {
                amIConnectedTo51 = false;
            }
        else
            {
                amIConnectedTo51 = true;
            }

        initMetadata (db);

        Object customer = createInstance ("Ian Blair Sr.", "Prospect", client);

        testOut.println( "Customer: ============");
        printAList (customer);

        Object serial = ((SerialObject)customer).serialize();
        
        testOut.println ("Serial State:");
        testOut.println (serial);
        testOut.println ();

        CallableStatement statement;
        if (amIConnectedTo51)
            {
                statement = db.prepareCall ("{ ? = call Sample.Customer_ToString ( ? ) }");
                statement.setObject (2, customer);
            }
        else
            {
                statement = db.prepareCall ("{ ? = call Sample.Customer_ToStringWrapper ( ? ) }");
                statement.setObject (2, serial);
            }

        statement.registerOutParameter (1, Types.VARCHAR);
        statement.execute ();
        String str = statement.getString (1);
        testOut.println ("Converted to String by Stored Procedure:");
        testOut.println (str);

        testOut.println ();

        if (!client)
            testOut.println (((RegisteredObject)customer).getOref());

        testOut.println ("Converted to String by running method:");
        CacheMethod showMethod = customerClass.getMethod ("ToStringWrapper");
        str = (String) showMethod.invoke (null, new Object[]{SysListProxy.getBinaryData(serial)});
        testOut.println (str);

	}

    private static Object createInstance (String name, String status,
                                          boolean client)
        throws CacheException
    {
        Object addr;

        Object customer = newInstance (customerClass, client);
        List addresses = (List) customerClass.getField ("MailingAddresses").get(customer);

        addr = createAddressInstance ("768 Beacon St.", "Newton", "MA", "02459", client);
        addresses.add (addr);

        addr = createAddressInstance ("1124 Commonwealth Ave.", "Brookline", "MA", "02446", client);
        addresses.add (addr);

        Map addressAray = (Map) customerClass.getField ("Addresses").get(customer);

        addr = createAddressInstance ("7722 Main St.", "Detroit", "MI", "48224", client);
        addressAray.put ("Residence", addr);

        addr = createAddressInstance ("825 Third Ave.", "New York", "NY", "10022", client);
        addressAray.put ("Business", addr);

        addr = customerClass.getField ("MyAddress").get(customer);
        streetField.set(addr, "25 Glen Ave.");
        cityField.set(addr, "Newton");
        stateField.set(addr, "MA");
        zipField.set(addr, "02459");

        customerClass.getField ("Name").set(customer, name);
        customerClass.getField ("TS").set(customer, new Timestamp(System.currentTimeMillis()));
        customerClass.getField ("Status").set(customer, status);

        return customer;
    }

    private static Object createAddressInstance (String street, String city,
                                                    String state, String zip,
                                                    boolean client)
        throws CacheException
    {
        Object addr = newInstance (addressClass, client);

        streetField.set(addr, street);
        cityField.set(addr, city);
        stateField.set(addr, state);
        zipField.set(addr, zip);

        return addr;
    }

    private static void initMetadata(Database db)
        throws CacheException
    {
        customerClass = db.getCacheClass ("Sample.Customer");
        addressClass = db.getCacheClass ("Sample.Address");

        streetField = addressClass.getField("Street");
        cityField = addressClass.getField("City");
        stateField = addressClass.getField("State");
        zipField = addressClass.getField("Zip");

    }

    // Below are helper methods:

    private static Object newInstance (CacheClass cls, boolean client)
        throws CacheException
    {
        if (client)
            return cls.createClientObject ();
        return cls.newInstance ("");
    }

    public static void printAList (Object alist)
        throws Exception
    {
        testOut.println("\tName: " + customerClass.getField ("Name").get(alist));
        printList ((List) customerClass.getField ("MailingAddresses").get(alist));
        printMap  ((Map) customerClass.getField ("Addresses").get(alist));
        testOut.println("\tStatus: " + customerClass.getField ("Status").get(alist));
        testOut.println ("\tTimeStamp = " + customerClass.getField ("TS").get(alist));
        testOut.println("My Address:");
        printAddress ("\t", customerClass.getField ("MyAddress").get(alist));
    }

    protected static void printList (List addresses)
        throws Exception
    {
        testOut.println ("\tThere " + addresses.size() + " addresses in list");

        for (Iterator it = addresses.iterator(); it.hasNext();)
            {
                printAddress ("\t\t", it.next());
            }
    }

    protected static void printMap (Map addresses)
        throws Exception
    {
        testOut.println ("\tThere " + addresses.size() + " addresses in map");

        for (Iterator it = addresses.entrySet().iterator(); it.hasNext();)
            {
                Map.Entry e = (Map.Entry)it.next();
                String key = (String)e.getKey();
                Object addr = e.getValue();
                testOut.println( "\t\t " + key);
                printAddress ("\t\t", addr);
            }
    }

    protected static void printAddress (String prefix, Object addr)
        throws Exception
    {
        testOut.println( prefix + "Street: " + streetField.get(addr));
        testOut.println( prefix + "City:   " + cityField.get(addr));
        testOut.println( prefix + "State:  " + stateField.get(addr));
        testOut.println( prefix + "Zip:    " + zipField.get(addr));
        testOut.println( prefix + "============");
    }
}

