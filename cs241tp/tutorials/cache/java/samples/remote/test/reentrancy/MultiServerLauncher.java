package remote.test.reentrancy;

import com.intersys.gateway.JavaGateway;

/**
 * Copyright (c) 2004 InterSystems, Corp.
 * Cambridge, Massachusetts, U.S.A.  All rights reserved.
 * Confidential, unpublished property of InterSystems.
 * <p/>
 *
 * @author <a href="mailto:mbouzin@intersystems.com">Michael A Bouzinier</a>
 *         <p/>
 *         Date: 27-Jan-2005
 *         Time: 22:45:40
 */
public class MultiServerLauncher
{
    private static int[] ports =
        {
            51111,
            52222,
            53333,
            54444,
            55555,
            56666,
            57777,
            58888,
            59999
        };

    public static void main (String[] argv)
        throws InterruptedException
    {
        int n = Integer.parseInt (argv[0]);

        Thread[] gateways = new Thread[n];

        for (int i = 0; i < n; i++)
            {
                gateways[i] = new JavaGatewayRunner (ports[i]);
            }

        for (int i = 0; i < n; i++)
            {
                gateways[i].start();
            }

        for (int i = 0; i < n; i++)
            {
                gateways[i].join();
            }
    }

    private static class JavaGatewayRunner extends Thread
    {
        int port;
        public JavaGatewayRunner (int p)
        {
            super (new ThreadGroup ("JavaGateway:" + p), "JGW Starter " + p);
            port = p;
        }

        public void run ()
        {
            synchronized (JavaGatewayRunner.class)
                {
                    System.out.println ("Starting: " + getName());
                }

            String[] argv = {String.valueOf(port), "jgw-" + port + ".log"};
            //String[] argv = {String.valueOf(port)};
            try
                {
                    JavaGateway.main (argv);
                }
            catch (Exception e)
                {
                    throw new RuntimeException (e);
                }

            synchronized (JavaGatewayRunner.class)
                {
                    System.out.println ("Finished: " + getName());
                }
        }
    }
}
