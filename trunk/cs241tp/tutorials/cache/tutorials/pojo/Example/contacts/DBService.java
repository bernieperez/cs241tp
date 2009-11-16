/*
 * DBService.java
 *
 * Created on August 4, 2006, 3:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package contacts;

import com.jalapeno.ApplicationContext;
import com.jalapeno.ObjectManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;

public class DBService {
    
        ObjectManager objectManager;

    public DBService ()
        throws Exception
    {
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for defaul

        String           url="jdbc:Cache://" + host + ":1972/USER";

        Class.forName ("com.intersys.jdbc.CacheDriver");
        Connection connection =  DriverManager.getConnection (url, username, password);
        objectManager = ApplicationContext.createObjectManager (connection);
    }
    
  protected void saveContact(Contact contact )
    throws Exception
  {
        objectManager.save(contact, true);
       
   }
    
  protected Iterator allContacts ()
  throws Exception
    {
      
         return objectManager.openByQuery (Contact.class, null, null);
      
    }
 
  public Contact contactsByName (String name) 
  throws Exception
    {
        
            String query = "name = ? Order By name";
            Iterator it = objectManager.openByQuery (Contact.class, query, new Object[]{name});
            if (it.hasNext ())
                return (Contact) it.next ();
            throw new Exception ("Contact not found.");
        
    }
 
  
  public Iterator phonesByName(String name)
  throws Exception
  {
      String query = "Select Distinct contacts.PhoneNumber.%ID," +
          " contacts.PhoneNumber.number, contacts.PhoneNumber.PhoneNumberType" + 
          " From contacts.phoneNumber Where contacts.phoneNumber.owner ->" +
          " name %Startswith ?";
      return objectManager.openByQuery(query, new Object[]{name});
                  
  }
    
   
}
