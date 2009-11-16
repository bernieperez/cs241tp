package sqlcommands;

import com.intersys.objects.Database;
import com.intersys.objects.CacheDatabase;
import com.intersys.codegenerator.sql.Generator;

/**
 * Copyright (c) 2005 InterSystems, Corp. Cambridge, Massachusetts, U.S.A.  All
 * rights reserved. Confidential, unpublished property of InterSystems.
 * <p/>
 *
 * @author <a href="mailto:mbouzin@intersystems.com">Michael A Bouzinier</a>
 *         <p/>
 *         Date: 12-Jul-2005 Time: 19:27:58
 */
public class GenerateSomeCommands
{
    public static void main (String[] args)
        throws Exception
    {
        String           host = "localhost";
        String           username="_SYSTEM";  // null for default
        String           password="SYS";  // null for default
        String           port = "1972";
        String           namespace = "SAMPLES";
        String           srcDir = System.getProperty ("user.dir", ".");
        String           cacheClassName = "Sample.Person";
        String           outClassName = "sqlcommands.PersonCommands";

        for (int i = 0; i < args.length; i++)
            if (args[i].equalsIgnoreCase("-user"))
                username = args[++i];
            else if (args[i].equalsIgnoreCase("-password"))
                password = args[++i];
            else if (args[i].equalsIgnoreCase("-host"))
                host = args[++i];
            else if (args[i].equalsIgnoreCase("-port"))
                port = args[++i];
            else if (args[i].equalsIgnoreCase("-namespace"))
                namespace = args[++i];
            else if (args[i].equalsIgnoreCase("-srcdir"))
                srcDir = args[++i];
            else if (args[i].equalsIgnoreCase("-outclass"))
                outClassName = args[++i];
            else if (args[i].equalsIgnoreCase("-cacheclass"))
                cacheClassName = args[++i];
            else {
                System.out.println ("Unknown option: " + args[i]);
                return;
            }

        String  url="jdbc:Cache://" + host + ":" + port + "/" + namespace;
        Database db = CacheDatabase.getDatabase (url, username, password);

        Generator gen = new Generator (db, cacheClassName, srcDir, outClassName);
        System.out.println ("Creating class: " + outClassName + " in " + srcDir);
        gen.generateBatchInsert ();
        gen.generateSingleInsert (null);
        gen.generateSingleInsert (new String[]{"SSN", "Name", "DOB"});
        gen.generateUpdate (new String[]{"Home_Street", "Home_City",
                                         "Home_State", "Home_Zip"});
        gen.generateLoad ("loadPerson", new String[]{"SSN", "Name", "DOB", "FavoriteColors"});
        gen.generateLoad ("loadWithState", new String[]{"ID", "Name", "Home_State", "Home_Zip"});
        gen.flush ();
    }

}

