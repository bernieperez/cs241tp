package clientObjects;

import Sample.Address;
import Sample.Customer;
import com.intersys.cache.metadata.MetadataFactory;
import com.intersys.cache.serial.CacheMetadataFactory;
import com.intersys.cache.serial.SerialObjectFactory;
import com.intersys.classes.SerialObject;
import com.intersys.jdbc.SysListProxy;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.Oid;
import com.intersys.objects.ObjectServerInfo;

import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StaticClientObjects
{
	static PrintStream testOut = System.out;

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

        Customer customer = (client) ? (Customer)Customer.createClientObject (db) : new Customer(db);
        List addresses = customer.getMailingAddresses ();
        Address addr;
        addr = newAddress(db, client);
        addr.setStreet ("768 Beacon St.");
        addr.setCity ("Newton");
        addr.setState ("MA");
        addr.setZip ("02459");
        addresses.add (addr);

        addr = newAddress(db, client);
        addr.setStreet ("1124 Commonwealth Ave.");
        addr.setCity ("Brookline");
        addr.setState ("MA");
        addr.setZip ("02446");
        addresses.add (addr);

        Map addressAray = customer.getAddresses ();

        addr = newAddress(db, client);
        addr.setStreet ("7722 Main St.");
        addr.setCity ("Detroit");
        addr.setState ("MI");
        addr.setZip ("48224");
        addressAray.put ("Detroit", addr);

        addr = newAddress(db, client);
        addr.setStreet ("825 Third Ave.");
        addr.setCity ("New York");
        addr.setState ("NY");
        addr.setZip ("10022");
        addressAray.put ("New York", addr);

        addr = customer.getMyAddress ();
        addr.setStreet ("25 Glen Ave.");
        addr.setCity ("Newton");
        addr.setState ("MA");
        addr.setZip ("02459");

        customer.setName("Ian Blair Sr.");
        customer.setTS(new java.sql.Timestamp(System.currentTimeMillis()));
        customer.setStatus("Prospect");

        testOut.println( "Now Customer values: ============");
        printCustomer (customer);

        Object serial = customer.serialize();
        
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
        statement.setObject (2, null);
        statement.execute ();
        str = statement.getString (1);
        testOut.println ("Null Object Converted to String by Stored Procedure:");
        testOut.println (str);

        testOut.println ();

        if (!client)
            testOut.println (customer.getOref());
        str = Customer.ToStringWrapper (db, SysListProxy.getBinaryData(serial));
        testOut.println ("Converted to String by running method:");
        testOut.println (str);

        if (amIConnectedTo51)
            {
                testOut.println ();
                testOut.println( "Now creating detached Customer: ============");
                SerialObjectFactory sdb = new SerialObjectFactory ();
                MetadataFactory mf = new CacheMetadataFactory (sdb, db);
                sdb.setMetadataFactory (mf);

                Oid oid = new Oid (serial);
                customer = (Customer) SerialObject._open (sdb, oid);
                printCustomer (customer);
            }
	}

    private static Address newAddress (Database db, boolean client)
        throws CacheException
    {
        if (client)
            return (Address)Address.createClientObject (db);
        return new Address (db);
    }

    public static void printCustomer (Customer customer)
        throws Exception
    {
        testOut.println("\tName: " + customer.getName ());
        printList (customer.getMailingAddresses ());
        printMap  (customer.getAddresses ());
        testOut.println("\tStatus = " + customer.getStatus ());
        testOut.println("My Address:");
        printAddress ("\t", customer.getMyAddress());
    }

    protected static void printList (List addresses)
        throws Exception
    {
        testOut.println ("\tThere " + addresses.size() + " addresses in list");

        for (Iterator it = addresses.iterator(); it.hasNext();)
            {
                Address addr = (Address)it.next();
                printAddress ("\t\t", addr);
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
                Address addr = (Address)e.getValue();
                testOut.println( "\t\t " + key);
                printAddress ("\t\t", addr);
            }
    }

    protected static void printAddress (String prefix, Address addr)
        throws Exception
    {
        testOut.println( prefix + "Street: " + addr.getStreet() );
        testOut.println( prefix + "City:   " + addr.getCity() );
        testOut.println( prefix + "State:  " + addr.getState() );
        testOut.println( prefix + "Zip:    " + addr.getZip() );
        testOut.println( prefix + "============");
    }

}

