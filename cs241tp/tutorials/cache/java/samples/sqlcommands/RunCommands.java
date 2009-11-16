package sqlcommands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;

/**
 * Copyright (c) 2005 InterSystems, Corp. Cambridge, Massachusetts, U.S.A.  All
 * rights reserved. Confidential, unpublished property of InterSystems.
 * <p/>
 *
 * @author <a href="mailto:mbouzin@intersystems.com">Michael A Bouzinier</a>
 *         <p/>
 *         Date: 08-Jul-2005 Time: 22:37:35
 */
public class RunCommands
{
    public static void main (String[] args)
        throws Exception
    {
        Class.forName ("com.intersys.jdbc.CacheDriver");
        String   url="jdbc:Cache://localhost:1972/SAMPLES";
        Connection connection = DriverManager.getConnection (url, "_SYSTEM", "SYS");
        PersonCommands cmd = new PersonCommands (connection, "Sample", "Person");

        int id = cmd.insert (generateSSN (), "Born by Command", new Date(System.currentTimeMillis ()));

        Object[] person = cmd.loadPerson (id);
        printPerson (person);
        person = cmd.loadWithState (id);
        printPerson (person);

        cmd.update (id, "One Memorial Dr.", "Cambridge", "MA", "02142");

        person = cmd.loadPerson (id);
        printPerson (person);
        person = cmd.loadWithState (id);
        printPerson (person);
    }

    private static void printPerson (Object[] p)
    {
        System.out.println("Person: ");
        for (int i = 0; i < p.length; i++)
            System.out.println ("\t" + p[i]);
    }

    private static java.util.Random random;
    private static String generateSSN ()
    {
        if (random == null)
            random = new java.util.Random ();

        StringBuffer sb = new StringBuffer ();
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append('-');
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append('-');
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));
        sb.append(random.nextInt (10));

        return sb.toString();
    }

}
