/* $Id: ListDemo.java,v 1.3 2006/12/19 20:26:28 gerd Exp $ */

import java.sql.CallableStatement;
import java.sql.SQLException;

import JavaDemo.JavaListSample;

import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.objects.SList;
import com.intersys.objects.SysListHolder;


/**
 * 
 * This is a little sample program that demonstrates how to send information in $list format 
 * back and forth. 
 * 
 * We use only com.intersys.objects.SList as client representation of a $list. For a reference
 * parameter we use SysListHolder.
 * 
 * 
 * 
 * @author gnachtsheim
 * @version $Revision: 1.3 $
 */
public class ListDemo
{
	static String PORT = "1972";
	
	private Database m_db = null;
	
	
	public ListDemo() throws Exception
	{
		m_db = CacheDatabase.getDatabase("jdbc:Cache://localhost:" + PORT + "/SAMPLES","_SYSTEM","SYS");
	}
	
	public static void main(String [] args) throws Exception
	{
		new ListDemo().run();
	}
	
	void run() throws Exception
	{
		int ids[] = new int [] {1,3,4,5,6,7 };
		jdbcListDemo(ids);
		factoryListDemo(ids);
	}
	
	/**
	 * demo how to use $list with Java binding
	 * 
	 * @param ids
	 * @throws CacheException
	 */
	private void factoryListDemo(int ids[]) throws CacheException
    {
    	out("start Java binding demo");
    	
    	// use regular method call with GetListOfNames returng an SList
    	SList inlist = intArrayToSList(ids);   	
	    SList outlist = JavaListSample.GetListOfNames(m_db,inlist);
	    showSList(outlist,"factory with GetListOfNames(...)");
	    
	    // use byref parameter
	    inlist = intArrayToSList(ids);
	    SysListHolder slh = new SysListHolder(inlist);
	    Integer count = JavaListSample.GetListOfNamesByRef(m_db, slh);
	    
	    outlist = slh.value;
	    showSList(outlist,"factory with GetListOfNamesByRef(...)");
	    out("count = " + count.intValue() + "\n");
	    out("end Java binding demo");
    }

	/**
	 * demo how to use $list with JDBC
	 * 
	 * 
	 * @param ids
	 * @throws CacheException 
	 * @throws SQLException 
	 */
	private void jdbcListDemo(int ids[]) throws CacheException, SQLException
    {
	    
    	out("start JDBC demo");
    	SList sl = null;
    	// get list as return value
		CallableStatement cstmt = m_db.prepareCall("? = call JavaDemo.GetListOfNames(?)");
		cstmt.registerOutParameter(1, java.sql.Types.BINARY);
		cstmt.setObject(2,intArrayToSList(ids).getData());
		cstmt.execute();
		sl = createSListFromArray(cstmt.getBytes(1));		
		showSList(sl,"[JDBC] at the end of GetListOfNames()");
		
		cstmt = m_db.prepareCall("? = call JavaDemo.GetListOfNamesByRef(?)");
		cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
		cstmt.registerOutParameter(2, java.sql.Types.BINARY);
		
		
		cstmt.setObject(2,intArrayToSList(ids).getData());
		cstmt.execute();
		int count = cstmt.getInt(1);
		sl = createSListFromArray(cstmt.getBytes(2));		
		showSList(sl,"[JDBC] at the end of GetListOfNamesByRef()");
		out("count = " + count+ "\n");
		
		out("end JDBC demo");
    }
	/**
	 * show the content of a list
	 * 
	 * @param sl
	 * @param text
	 */
	static void showSList(SList sl, String text)
	{
		out("\n" + text);
		for (int i=0; i < sl.size(); i++)
			{
				out("List element #" + i + " = " + sl.get(i));
			}
		out("\n");
	}
	
	/**
	 * transform an array of int values into an SList
	 * 
	 * @param ids
	 * @return
	 */
	static SList intArrayToSList(int ids[])
	{
		SList sl = new SList();
                for (int i=0; i < ids.length ; i++)
			{
                              sl.add(new Integer(ids[i]));
			}
		return sl;
	}
	
	/**
	 * simple helper for creating a SList from a byte[] array
	 * 
	 * @param raw
	 * @return
	 * @throws CacheException
	 */
	static SList createSListFromArray(byte raw[]) throws CacheException
	{
		SList sl = new SList();
		sl.add(raw);
		sl = (SList) sl.getAs(0,SList.class);
		return sl;
	}
	
	/**
	 * lazyman's print(s) ;)
	 * 
	 * @param o
	 */
	static void out(Object o) { System.out.println(o); }
}
