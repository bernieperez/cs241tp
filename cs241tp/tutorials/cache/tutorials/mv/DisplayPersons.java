/*
 * DisplayPersons.java
 *
 * Created on April 26, 2007, 9:32 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mvtutorial;
import MVFILE.*;
import java.sql.*;
import com.intersys.objects.*;
import java.util.*;

public class DisplayPersons {
    
    /** Creates a new instance of DisplayPersons */
    public DisplayPersons() {
    }
    
    public static void main(String[] args) {
        try {
            
            Database db =
                    CacheDatabase.getDatabase("jdbc:Cache://localhost:1972/MYACCOUNT");
            CacheQuery extent = PERSON.query_Extent(db);
            java.sql.ResultSet rs = extent.execute();          
           
            while (rs.next()){
                
                String id = rs.getString("ID");
                PERSON person = (PERSON)PERSON.open(db, new Id(id));
                System.out.printf("Name: %s %n",person.getNAME());
                System.out.printf("Age: %s %n",person.getAGE());
                System.out.printf("Hair: %s %n",person.getHAIR());
                
                System.out.println("Phones: ");
                List phoneList = person.getPHONE();
                for (Object number: phoneList)
                    System.out.println(number);
                
                System.out.println("###################################");
            }
            
            db.close();
            
        } catch (CacheException ex) {
            ex.printStackTrace();
        } catch (SQLException ex){
            ex.printStackTrace();
            
        }
        
        
    }
    
}
