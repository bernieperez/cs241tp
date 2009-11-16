/*
 * RunUpdate.java
 *
 * Created on April 30, 2007, 11:36 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mvtutorial;
import java.sql.*;
import com.intersys.objects.*;
public class RunUpdate {
    
    public RunUpdate() {
    }
    
    public static void main (String[] args)
    {
        try {
         //Create the connection
        Class.forName("com.intersys.jdbc.CacheDriver");
        String url="jdbc:Cache://localhost:1972/MyAccount";
        Connection conn = DriverManager.getConnection(url);
           
         //created the prepared statement  
        String sql = "UPDATE MVFILE.PERSON (AGE,PHONE) VALUES" +
                " ('45','111-111-1111,222-222-2222') WHERE NAME='IDLE,JIM'";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
         //execute the update
        int status = pstmt.executeUpdate();
        System.out.printf("Status %s",status);
           
        conn.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
                 
    }
    
}
