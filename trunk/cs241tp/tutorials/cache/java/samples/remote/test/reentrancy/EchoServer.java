package remote.test.reentrancy;

import com.intersys.cache.util.Console;
import com.intersys.cache.util.ConsoleOutputStream;
import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;

import java.io.PrintStream;

/**
 * Copyright (c) 2004 InterSystems, Corp.
 * Cambridge, Massachusetts, U.S.A.  All rights reserved.
 * Confidential, unpublished property of InterSystems.
 * <p/>
 *
 * @author <a href="mailto:mbouzin@intersystems.com">Michael A Bouzinier</a>
 *         <p/>
 *         Date: 27-Jan-2005
 *         Time: 21:17:57
 */
public class EchoServer
{
    public Database	cacheDatabase = null;
    private static int initialDepth = -1;
    private int indent;
    private PrintStream localOutput;
    private PrintStream cacheOutput;

    public static void main (String[] args)
        throws Exception
    {
        int numConnections = Integer.parseInt (args[0]);
        int depth = Integer.parseInt (args[1]);

        String url = "jdbc:Cache://localhost:1972/USER/jdbc.log";
        String username = "_SYSTEM";
        String password = "SYS";

        Database db = CacheDatabase.getDatabase (url, username, password);

        ReentrancyTest.EchoTest (db, numConnections, depth);
    }

    public static void setupOutput(int depth)
    {
        initialDepth = depth;
        Console.setDefaultTitle ("Cache Server Output");
    }

    public EchoServer ()
    {
        cacheOutput = new PrintStream(new ConsoleOutputStream(), true);
        localOutput = System.out;
    }

    public String echo (String str)
    {
        println (str, indent);
        return (new StringBuffer (str)).reverse ().toString ();
    }

    public String nestedEcho (String str, String oref, String target, int depth)
        throws CacheException
    {
        setupServerOutput ();

        indent = initialDepth - depth;

        if (depth == 0)
            return echo (str);
        depth--;

        println ("Received for nested call: " + str, indent);

        ReentrancyTest proxy = ReentrancyTest.GetMe (cacheDatabase, oref);

        String wrapped = "{" + str + "}";
        println (wrapped + " --->>> " + target + "(" + depth + ") ", indent);
        String response = proxy.CallNestedEcho (target, depth, wrapped);
        println ("<<<---- (" + depth + ") " + response, indent);

        return response;
    }

    private void println (String str, int indent)
    {
        String name = Thread.currentThread ().getThreadGroup ().getName ();
        if (name.equals("main"))
            name = "Java";

        localOutput.print (name + ": ");
        for (int i = 0; i < indent; i++)
            localOutput.print ("  ");
        localOutput.println (str);
    }

    private void setupServerOutput()
    {
        cacheDatabase.setConsoleOutput (cacheOutput);
    }
}
