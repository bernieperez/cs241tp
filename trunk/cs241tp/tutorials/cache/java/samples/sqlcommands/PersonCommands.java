package sqlcommands;

import com.intersys.objects.CacheException;
import com.intersys.objects.SList;

public class PersonCommands{

	protected com.intersys.jdbc.QuickStatement statement;
	protected java.lang.Object wire;

	protected java.lang.String schema;
	protected java.lang.String table;

	public PersonCommands (java.sql.Connection connection, String schema, String table)
		throws java.sql.SQLException
	{
		this.schema = schema;
		this.table = table;
		try
			{
				com.intersys.jdbc.CacheConnection c = com.intersys.cache.jdbcutil.JDBCAdapter.getCacheConnection (connection);
				statement = c.createQuickStatement ();
				wire = com.intersys.jdbc.SysListProxy.createSysList (c.getConnectionInfo ());
			}
		catch (CacheException e)
			{
				throw new java.sql.SQLException (e.getMessage ());
			}
	}

    public static Object addToBatchInsert (Object batch, java.sql.Connection con, java.sql.Date DOB, java.util.List FavoriteColors, String Name, String SSN, Integer Spouse, String Home_City, String Home_State, String Home_Street, String Home_Zip, String Office_City, String Office_State, String Office_Street, String Office_Zip) throws java.sql.SQLException {
        if (batch == null) {
            if (con != null) {
                try {
                    com.intersys.jdbc.CacheConnection c = com.intersys.cache.jdbcutil.JDBCAdapter.getCacheConnection (con);
                    batch = new com.intersys.jdbc.QuickStatement.Batch (c.getConnectionInfo ());
                } catch (com.intersys.objects.CacheException x) {
                    throw new java.sql.SQLException ("Connection is not a CacheConnection.");
                }
            }
        }
        com.intersys.jdbc.QuickStatement.Batch qbatch = (com.intersys.jdbc.QuickStatement.Batch) batch;
        com.intersys.jdbc.SysListProxy.setInteger (qbatch.list, 17); // number of columns
        com.intersys.jdbc.SysListProxy.setUndefined(qbatch.list); // for Age
        com.intersys.jdbc.SysListProxy.setSQLDate(qbatch.list, DOB);
        com.intersys.jdbc.SysListProxy.setListOfDatatypes(qbatch.list, FavoriteColors);
        com.intersys.jdbc.SysListProxy.setUndefined(qbatch.list); // for Home
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Name);
        com.intersys.jdbc.SysListProxy.setUndefined(qbatch.list); // for Office
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, SSN);
        com.intersys.jdbc.SysListProxy.setIntegerWrapper(qbatch.list, Spouse);
        com.intersys.jdbc.SysListProxy.setUndefined(qbatch.list); // for x__classname
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Home_City);
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Home_State);
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Home_Street);
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Home_Zip);
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Office_City);
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Office_State);
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Office_Street);
        com.intersys.jdbc.SysListProxy.setString(qbatch.list, Office_Zip);
        qbatch.flushRecord ();
        return qbatch;
    }
    
    public static java.util.List executeBatchInsert (java.sql.Connection con, Object batch, int nolock) throws java.sql.SQLException {
        Object ids = com.intersys.jdbc.QuickStatement.Batch.execute ("Sample", "Person", 4, batch, con, nolock);
        return new com.intersys.objects.SList (ids);
        }

    public int insert (java.sql.Date DOB, java.util.List FavoriteColors, String Name, String SSN, Integer Spouse, String Home_City, String Home_State, String Home_Street, String Home_Zip, String Office_City, String Office_State, String Office_Street, String Office_Zip) throws java.sql.SQLException {
        com.intersys.jdbc.SysListProxy.clearList(wire);
        com.intersys.jdbc.SysListProxy.setInteger (wire, 17);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Age
        com.intersys.jdbc.SysListProxy.setSQLDate(wire, DOB);
        com.intersys.jdbc.SysListProxy.setListOfDatatypes(wire, FavoriteColors);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Home
        com.intersys.jdbc.SysListProxy.setString(wire, Name);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office
        com.intersys.jdbc.SysListProxy.setString(wire, SSN);
        com.intersys.jdbc.SysListProxy.setIntegerWrapper(wire, Spouse);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for x__classname
        com.intersys.jdbc.SysListProxy.setString(wire, Home_City);
        com.intersys.jdbc.SysListProxy.setString(wire, Home_State);
        com.intersys.jdbc.SysListProxy.setString(wire, Home_Street);
        com.intersys.jdbc.SysListProxy.setString(wire, Home_Zip);
        com.intersys.jdbc.SysListProxy.setString(wire, Office_City);
        com.intersys.jdbc.SysListProxy.setString(wire, Office_State);
        com.intersys.jdbc.SysListProxy.setString(wire, Office_Street);
        com.intersys.jdbc.SysListProxy.setString(wire, Office_Zip);
        Integer id = (Integer) statement.create (schema, table, java.sql.Types.INTEGER, wire, 1);
        return id.intValue();
    }

    public int insert (String SSN, String Name, java.sql.Date DOB) throws java.sql.SQLException {
        com.intersys.jdbc.SysListProxy.clearList(wire);
        com.intersys.jdbc.SysListProxy.setInteger (wire, 17);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Age
        com.intersys.jdbc.SysListProxy.setSQLDate(wire, DOB);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for FavoriteColors
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Home
        com.intersys.jdbc.SysListProxy.setString(wire, Name);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office
        com.intersys.jdbc.SysListProxy.setString(wire, SSN);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Spouse
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for x__classname
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Home_City
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Home_State
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Home_Street
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Home_Zip
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_City
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_State
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_Street
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_Zip
        Integer id = (Integer) statement.create (schema, table, java.sql.Types.INTEGER, wire, 1);
        return id.intValue();
    }

    public void update (int rowid, String Home_Street, String Home_City, String Home_State, String Home_Zip) throws java.sql.SQLException {
        com.intersys.jdbc.SysListProxy.clearList(wire);
        com.intersys.jdbc.SysListProxy.setInteger (wire, 17);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Age
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for DOB
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for FavoriteColors
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Home
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Name
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for SSN
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Spouse
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for x__classname
        com.intersys.jdbc.SysListProxy.setString(wire, Home_City);
        com.intersys.jdbc.SysListProxy.setString(wire, Home_State);
        com.intersys.jdbc.SysListProxy.setString(wire, Home_Street);
        com.intersys.jdbc.SysListProxy.setString(wire, Home_Zip);
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_City
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_State
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_Street
        com.intersys.jdbc.SysListProxy.setUndefined(wire); // for Office_Zip
        statement.store (schema, table, new Integer(rowid), wire, 1);
    }

    public Object[] loadPerson (int rowid) throws java.sql.SQLException {
        Object list = statement.load (schema, table, new Integer(rowid), 1);
        
        int idloaded = com.intersys.jdbc.SysListProxy.getInteger(list);
        
        if (idloaded != rowid)
            throw new java.sql.SQLException ("Wrong row loaded. Rowid = " + idloaded +
                                        " instead of " + rowid);
        
        Object[] result = new Object[4];
        
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Age
        result[2] = com.intersys.jdbc.QuickStatement.getObject(list, 91);
        result[3] = com.intersys.jdbc.QuickStatement.getArrayList(list, 12);
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home
        result[1] = com.intersys.jdbc.QuickStatement.getObject(list, 12);
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office
        result[0] = com.intersys.jdbc.QuickStatement.getObject(list, 12);
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Spouse
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for x__classname
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home_City
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home_State
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home_Street
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home_Zip
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_City
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_State
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_Street
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_Zip
        return result;
    }

    public Object[] loadWithState (int rowid) throws java.sql.SQLException {
        Object list = statement.load (schema, table, new Integer(rowid), 1);
        
        int idloaded = com.intersys.jdbc.SysListProxy.getInteger(list);
        
        if (idloaded != rowid)
            throw new java.sql.SQLException ("Wrong row loaded. Rowid = " + idloaded +
                                        " instead of " + rowid);
        
        Object[] result = new Object[4];
        
        result[0] = new Integer(idloaded);
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Age
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for DOB
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for FavoriteColors
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home
        result[1] = com.intersys.jdbc.QuickStatement.getObject(list, 12);
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for SSN
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Spouse
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for x__classname
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home_City
        result[2] = com.intersys.jdbc.QuickStatement.getObject(list, 12);
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Home_Street
        result[3] = com.intersys.jdbc.QuickStatement.getObject(list, 12);
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_City
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_State
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_Street
        com.intersys.jdbc.SysListProxy.skip(list, 1); // for Office_Zip
        return result;
    }
}
