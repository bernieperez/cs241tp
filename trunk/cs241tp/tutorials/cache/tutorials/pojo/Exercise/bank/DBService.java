package bank;

import com.jalapeno.ApplicationContext;
import com.jalapeno.ObjectManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;

/**
 * Copyright (c) 2005 InterSystems, Corp. Cambridge, Massachusetts, U.S.A.  All
 * rights reserved.
 * <p/>  Demo for JavaPolis 2005
 *
 * @author <a href="mailto:mbouzin@intersystems.com">Michael A Bouzinier</a>
 *         <p/>
 *         Date: 13-Sep-2005 Time: 00:53:59
 */
public class DBService 
{
    ObjectManager objectManager;

    public DBService (String[] args)
        throws Exception
    {
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default


        for (int i = 0; i < args.length; i++)
            if (args[i].equals("-user"))
                username = args[++i];
            else if (args[i].equals("-password"))
                password = args[++i];
            else if (args[i].equals("-host"))
                host = args[++i];

        String           url="jdbc:Cache://" + host + ":1972/USER";

        Class.forName ("com.intersys.jdbc.CacheDriver");
        Connection connection =  DriverManager.getConnection (url, username, password);
        objectManager = ApplicationContext.createObjectManager (connection);
    }
  
    protected void saveCustomer (Customer customer)
        throws Exception
    {
        objectManager.save (customer, true);
    }

    protected Iterator allCustomers ()
        throws Exception
    {
        return objectManager.openByQuery (Customer.class, null, null);
    }

    public Customer customerByName (String name)
        throws Exception
    {
        String query = "name = ?";
        return customerByQuery (query, name);
    }

    public Customer customerBySSN (String ssn)
        throws Exception
    {
        return (Customer) objectManager.openByPrimaryKey (Customer.class, ssn);
        
    }

    private Customer customerByQuery (String query, String arg)
        throws Exception
    {
        Iterator it = objectManager.openByQuery (Customer.class, query, new Object[]{arg});
        if (it.hasNext ())
            return (Customer) it.next ();
        throw new Exception ("Customer not found.");
    }
}
