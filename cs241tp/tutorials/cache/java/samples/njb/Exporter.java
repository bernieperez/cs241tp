package njb;


import com.jalapeno.tools.sql.Importer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * Copyright (c) 2004 InterSystems, Corp.
 * Cambridge, Massachusetts, U.S.A.  All rights reserved.
 * Confidential, unpublished property of InterSystems.
 * <p/>
 *
 * @author <a href="mailto:mbouzin@intersystems.com">Michael A Bouzinier</a>
 *         <p/>
 *         Date: 06-Nov-2004
 *         Time: 15:36:57
 *
 * Modification History: (most recent first)
 *
 * JMM015 12/16/04 Jeff Miller, Pass db type to JavaGateway^%qarDDLExport
 */
public class Exporter
{
    public static void main (String[] argv)
        throws Exception
    {
        Class.forName ("com.intersys.jdbc.CacheDriver");

        // String cacheURL =   "jdbc:Cache://localhost:1972/SAMPLES";
        String cacheUser =  "_SYSTEM";
        String cachePassword = "SYS";
        String host = "localhost";
        String thost = "localhost";
        String port = "1972";
        String namespace = "SAMPLES";

        Connection targetConnection;

        String[] classes;
        String targetSchema = "Sample";
        String targetTable = "Generic_Table";
        String type = "mysql";
        String dbType = ""; // JMM015
        if (argv.length == 0)
            {
                classes = new String[]{"Sample.Person","Sample.Employee"};
                targetSchema = "Sample";
                targetTable = "Person_Base";
            }
        else
            {
                ArrayList cl = new ArrayList ();
                for (int i = 0; i < argv.length; i++)
                    {
                        if (argv[i].equalsIgnoreCase ("-target"))
                            targetTable = argv[++i];
                        else if (argv[i].equalsIgnoreCase ("-schema"))
                            targetSchema = argv[++i];
                        else if (argv[i].equals("-type"))
                            type = argv[++i];
                        else if (argv[i].equals("-user"))
                            cacheUser = argv[++i];
                        else if (argv[i].equals("-password"))
                            cachePassword = argv[++i];
                        else if (argv[i].equals("-host"))
                            host = argv[++i];
                        else if (argv[i].equals("-targethost"))
                            thost = argv[++i];
                        else if (argv[i].equals("-port"))
                            port = argv[++i];
                        else if (argv[i].equals("-namespace"))
                            namespace = argv[++i];
                        else
                            cl.add(argv[i]);
                    }
                classes = (String[]) cl.toArray (new String[cl.size ()]);
                for (int i = 0; i < classes.length; i++)
                    {
                        if (classes[i].indexOf ('.') < 0)
                            classes[i] = targetSchema + "." + classes[i];
                    }
            }

        if (type.equalsIgnoreCase ("mysql"))
            {
                dbType = "mysql"; // JMM015
                Class.forName ("com.mysql.jdbc.Driver");
                targetConnection = DriverManager.getConnection
                    ("jdbc:mysql://" + thost + "/Sample",
                        "_SYSTEM","SYS");
            }
        else if (type.equalsIgnoreCase ("mssql"))
            {
                dbType = "mssql"; // JMM015
                Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
                targetConnection = DriverManager.getConnection
                    ("jdbc:microsoft:sqlserver://" + thost + ":1433","_SYSTEM","SYS");
            }
        else if (type.equalsIgnoreCase ("oracle"))
            {
                dbType = "oracle"; // JMM015
                Class.forName ("oracle.jdbc.driver.OracleDriver");
                targetConnection = DriverManager.getConnection
                    ("jdbc:oracle:thin:@barbados.iscinternal.com:1521:barbados", "CACHE_SYS", "SYS");
            }
        else
            {
                throw new Exception ("Unknown connection type: " + type);
            }

        String cacheURL="jdbc:Cache://" + host + ":" + port + "/" + namespace;// + "/jdbc.log";
        Importer importer = new Importer (cacheURL, cacheUser, cachePassword);

        importer.exportDDL (classes, targetSchema, targetTable, dbType); // JMM015

        importer.importAll (targetConnection);
    }

}
