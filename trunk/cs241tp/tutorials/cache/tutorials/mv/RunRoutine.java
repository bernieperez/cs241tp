/*
 * RunRoutine.java
 *
 * Created on April 27, 2007, 2:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mvtutorial;
import MVExample.*;
import com.intersys.objects.*;

public class RunRoutine {
    
    /** Creates a new instance of RunRoutine */
    public RunRoutine() {
    }
     
    public static void main (String[] args)
    {
        try {    
          Database db =
                  CacheDatabase.getDatabase("jdbc:Cache://localhost:1972/MYACCOUNT");
          String value = Search.GetValue(db, "3", "NAME","1");
          System.out.printf("Value: %s", value);
        } catch (CacheException ex) {
            ex.printStackTrace();
        }    
        
    }
    
}
