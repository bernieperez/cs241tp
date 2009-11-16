/*
 * RunQuery.java
 *
 * Created on April 30, 2007, 10:28 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package mvtutorial;
import java.sql.*;


public class RunQuery {
    
    public RunQuery() {
    }
    public static void main(String[] args) {
        try { 
            //Create the connection
            Class.forName("com.intersys.jdbc.CacheDriver");       
            String url="jdbc:Cache://localhost:1972/MYACCOUNT";
            Connection conn = DriverManager.getConnection(url);
            //Execute the query
            String query = "Select * from MVFile.Person";
            Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(query);
            //Retrieve and display the results
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i=1; i<=colnum; i++) {
                    System.out.print(rs.getString(i) + "  ");
                }
                System.out.println();
            } 
            //Close the result set and database connection
            rs.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }                   
        
    }
    
}
