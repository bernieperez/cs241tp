/*
 * JDBCQuery.java 
 *
 */

import java.sql.*;

public class JDBCQuery{
    private static final int DEFAULT_PORT = 1972;

    public static void main(String[] args){
 		try {
            int port = determinePort();

            String url = "jdbc:Cache://127.0.0.1:" + port + "/SAMPLES";
            String user = "_SYSTEM";
            String password = "SYS";
            String stQuery = "Select * from Sample.Person";

 			Class.forName ("com.intersys.jdbc.CacheDriver");
 			Connection dbconnection = DriverManager.getConnection(url,user,password);
 			Statement stmt = dbconnection.createStatement();
 			java.sql.ResultSet rs = stmt.executeQuery(stQuery);
 			ResultSetMetaData rsmd = rs.getMetaData();

 			int colnum = rsmd.getColumnCount();
 			while (rs.next()) {
 				for (int i=1; i<=colnum; i++) {
 					System.out.print(rs.getString(i) + "  ");
 				}
 				System.out.println();
 			}

 			dbconnection.close();
 		} catch (Exception ex) {
 			System.out.println("Caught exception: " +
                               ex.getClass().getName()
                               + ": " + ex.getMessage());
 		}
 	}

    private final static int determinePort()
        throws Exception
    {
        String pt = System.getProperty ("com.intersys.port");
        if (pt == null)
            {
                return DEFAULT_PORT;
            }
        
        int port = 0;
        try
            {
                port = Integer.parseInt(pt);
            }
        catch (NumberFormatException x)
            {
                throw new Exception ("Invalid default port specified in " + 
                                          "system properties: "+ pt);
            }

        return port;
    }
    
}

/*
 * End-of-file
 *
 */
