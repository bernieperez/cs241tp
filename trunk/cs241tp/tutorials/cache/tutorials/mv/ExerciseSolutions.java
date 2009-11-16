/*
 * ExerciseSolutions.java
 *
 * Created on May 18, 2007, 12:00 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mvtutorial;
import MVFILE.*;
import java.sql.*;
import com.intersys.objects.*;
import java.util.*;
import java.sql.*;

public class ExerciseSolutions {
    static String url="jdbc:Cache://localhost:1972/MYACCOUNT";
    static Database db;
    static String newNumber = "999-888-7777";
  
    public static void connect() throws CacheException
    {
        
       db = CacheDatabase.getDatabase(url);
        
    }
    
    public static void addPerson() throws CacheException, SQLException
    {
        
            String sql = "INSERT INTO MVFILE.Person (ItemId,AGE,HAIR,NAME,PHONE) VALUES ('12','44','BRUNETTE','SMITH,ROGER','911-111-2222')";
            PreparedStatement pstmt = db.prepareStatement(sql);
            int status = pstmt.executeUpdate();
            System.out.printf("Status %s",status);
       
    }
    
    
    public static void addPhone()  throws CacheException
    {
            String sql="NAME %STARTSWITH ?";
            String[] qargs={"SMITH"};
            
          
           for (Iterator iter = db.openByQuery("MVFILE.PERSON",sql,qargs); iter.hasNext();)
           {
              MVFILE.PERSON person = (MVFILE.PERSON)iter.next();
              System.out.printf("Updatding %s",person.getNAME());
              person.getPHONE().add(newNumber);
              int status = person.save();
              System.out.printf("Status %s \n", status);                
           }
                 
        
    }
    
    public static void removePhone() throws CacheException
    {
         String sql="NAME %STARTSWITH ?";
            String[] qargs={"SMITH"};
            
            
           for (Iterator iter = db.openByQuery("MVFILE.PERSON",sql,qargs); iter.hasNext();)
           {
              MVFILE.PERSON person = (MVFILE.PERSON)iter.next();
              person.getPHONE().remove(newNumber);
              int status = person.save();
              System.out.printf("Status %s \n", status);                
           }
        
        
    }
    
    public static void RunRoutine(String id, String attr, String subValue, String value) throws CacheException
    {
        MVExercise.Update.SetValue(db,id,attr,subValue,value);
        
        
    }
    
    public static void main (String[] args)
    {
        try {
            ExerciseSolutions.connect();
            ExerciseSolutions.addPerson();
            ExerciseSolutions.addPhone();
            ExerciseSolutions.removePhone();
            
            
         ExerciseSolutions.RunRoutine("1","PHONE", "2", "999-999-9999");
            
        } catch (CacheException ex) {
            ex.printStackTrace();
        } 
       catch (SQLException ex){
            ex.printStackTrace();
        }
        finally{
            
            try{
            db.close();
            }
            catch (CacheException cex){}
        } 
              
    }
    
    
      
}
