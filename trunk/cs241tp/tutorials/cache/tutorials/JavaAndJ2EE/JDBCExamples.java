import java.util.*;
import java.sql.*;
import com.intersys.objects.*;
import JavaTutorial.*;
//import com.intersys.classes.*;


public class JDBCExamples {

	public static Database getDatabase() throws CacheException{
		String url="jdbc:Cache://localhost:1972/USER";
		String username="_SYSTEM";
		String pwd="SYS";
		Database db = CacheDatabase.getDatabase(url, username, pwd);
		return db;

	}

	public static void printContactNamesDB() throws CacheException, SQLException{

		Database db = JDBCExamples.getDatabase();
		Statement stmt = db.createStatement();
		String query = "SELECT Name, ContactType FROM JavaTutorial.Contact";
		java.sql.ResultSet rs = stmt.executeQuery(query);
		while (rs.next()){
			String name = rs.getString(1);
			String type = rs.getString(2);
			System.out.println("Name: " + name + " Type: " + type);

		}
		rs.close();
	}

	public static Connection createConnection() throws SQLException, ClassNotFoundException{
		String url="jdbc:Cache://localhost:1972/USER";
		String username="_SYSTEM";
		String password="SYS";
		Class.forName ("com.intersys.jdbc.CacheDriver");
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static void printContactNames() throws SQLException, ClassNotFoundException{

		Connection conn = JDBCExamples.createConnection();
		Statement stmt = conn.createStatement();
		String query = "SELECT Name, ContactType FROM JavaTutorial.Contact";
		ResultSet rs=stmt.executeQuery(query);
		while (rs.next()){
			String name = rs.getString(1);
			String type = rs.getString(2);
			System.out.println("Name: " + name + " Type: " + type);

		}
		rs.close();
	}

	public static void insertPhoneNumber(String contactId, String number, String type) throws SQLException, ClassNotFoundException{

		Connection conn = JDBCExamples.createConnection();
		String sql =
		  "INSERT INTO JavaTutorial.PhoneNumber (Contact, Number, PhoneNumberType)"+
		  "VALUES (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, contactId);
		pstmt.setString(2, number);
		pstmt.setString(3, type);
		pstmt.executeUpdate();

	}

	public static void printContactByType(String type) throws SQLException, ClassNotFoundException{

		Connection conn = JDBCExamples.createConnection();
		CallableStatement cs = conn.prepareCall("{call JavaTutorial.Contact_RetrieveByContactType(?)}");
		cs.setString(1,type);
		ResultSet rs = cs.executeQuery();
		while (rs.next()){
			System.out.println(rs.getString(1));
		}

		rs.close();

	}

	public static void printPhoneNumbersByNameAndType(String name, String type) throws SQLException, ClassNotFoundException {

		Connection conn = JDBCExamples.createConnection();
		String sql =
		"SELECT Number FROM JavaTutorial.PhoneNumber "+
		"WHERE PhoneNumberType=? AND Contact->Name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, type);
		pstmt.setString(2,name);
		ResultSet rs=pstmt.executeQuery();
		System.out.println("Name: " + name + " Type: " + type);
		while (rs.next()){
			System.out.println(rs.getString(1));
		}

		rs.close();
	}

public static void main(String args[]) {

	try{
		System.out.println("Testing Create Using CacheDatabase");
		Database db=JDBCExamples.getDatabase();
		if (db !=null){System.out.println("db Not Null");}
		else {System.out.println("db is Null");}
		//Removes existing Contact and PhoneNumber instances from database.
		Contact.sys_KillExtent(db);
		PhoneNumber.sys_KillExtent(db);

		//Populates the Contact and PhoneNumber tables in the database
		Contact.Populate(db,new Integer(5), new Integer(1));
		PhoneNumber.Populate(db,new Integer(15), new Integer(1));
		Contact contact=(Contact)(Contact._open(db,new Id(1)));
		System.out.println("Testing printContactNamesDB");

		JDBCExamples.printContactNamesDB();

		System.out.println("Testing Create Using Standard Approach");
		Connection conn=JDBCExamples.createConnection();
		if (conn !=null){System.out.println("Conn Not Null");}
		else {System.out.println("Conn is Null");}

		System.out.println("Testing printContactNames Standard");

		JDBCExamples.printContactNames();

		System.out.println("Testing insertPhoneNumber");
		JDBCExamples.insertPhoneNumber("5","922","Home");

		System.out.println("Testing printContactByType");
		JDBCExamples.printContactByType("Personal");

		System.out.println("Testing printPhoneNumbersByNameAndType");
		JDBCExamples.printPhoneNumbersByNameAndType(contact.getName(), "Home");

	}

	catch(CacheException e){System.out.println(e.getMessage());}
	catch(SQLException e){System.out.println(e.getMessage());}
	catch(ClassNotFoundException e){System.out.println(e.getMessage());}
}

}