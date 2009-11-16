/*
 * AddPerson.java
 *
 * Created on April 26, 2007, 10:02 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mvtutorial;
import MVFILE.*;
import java.sql.*;
import com.intersys.objects.*;
import java.util.*;


public class AddPerson {
    
    /** Creates a new instance of AddPerson */
    public AddPerson() {
    }
    
    public static void main (String[] args)
    {
        try {
            Database db =
                CacheDatabase.getDatabase("jdbc:Cache://localhost:1972/MYACCOUNT");
                
            PERSON person = new PERSON(db);
            person.setItemId("10");
            person.setNAME("DOE,JOHN");
            person.setAGE("55");
            person.setHAIR("BLOND");
            List phoneList = person.getPHONE();
            phoneList.add("111-222-3333");
            phoneList.add("222-333-4444");
            person.save();
            
            db.close();
             
        } catch (CacheException ex) {
            ex.printStackTrace();
        }     
        
    }
    
}
