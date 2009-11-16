import com.versant.fund.*;
import java.util.*;

/**
 * This example application demonstrates the usage of the one thread per
 * session model: it has three threads and each thread has its own
 * session. None of the threads uses more than one session, and no
 * session is used by more than one thread.
 */

public
class FundStockMarket
{
    static String dbName;

    public static void main (String[] args) throws InterruptedException
    {
        if (args.length < 1) {
            System.out.println ("Usage: FundStockMarket <db>");
            System.exit (-1);
        }
        dbName = args[0];
        FundSession session = new FundSession (dbName, "main");

        AttrString symbol_attr = session.newAttrString ("symbol");
        AttrInt    price_attr  = session.newAttrInt ("price");
        AttrBuilder attrs[] = { session.newAttrBuilder (symbol_attr),
                                session.newAttrBuilder (price_attr) };

	ClassHandle cls = null;
	try {
	    cls = session.withAttrBuilders (attrs).defineClass ("StockQuote");
	} catch (VException vex) {
	    if (vex.getErrno () == 6001) /* SCH_CLASS_DEFINED */ {
	        cls = session.locateClass ("StockQuote");
	    }
	    else throw vex;
	}	  

        for (int i = 0; i < symbols.length; i++) {
	    Handle h = cls.makeObject ();
            h.put (symbol_attr, symbols[i]);
	    h.put (price_attr, Math.abs(random.nextInt()) % 10000);
        }
        session.endSession ();

        Thread[] threads = {
            new StockMarketReader (),
            new StockMarketWriter ()
        };

        for (int i = 0; i < threads.length; i++) {
            threads [i].start ();
        }
        for (int i = 0; i < threads.length; i++) {
            threads [i].join ();
        }
    }

    static String randomSymbol ()
    {
        int index = Math.abs(random.nextInt()) % symbols.length;
        return symbols [index];
    }

    static boolean done = false;
    static Random random = new Random ();
    static String[] symbols = { "vsnt", "sunw", "ibm", "msft", "orcl" };
}

class StockMarketReader extends Thread
{
    public void run ()
    {
        FundSession session = new FundSession (FundStockMarket.dbName, "reader");
        AttrString symbol_attr = session.newAttrString ("symbol");
        AttrInt    price_attr  = session.newAttrInt ("price");

        FundVQLQuery fvql = new FundVQLQuery
	    (session, "select selfoid from StockQuote");

        while ( !FundStockMarket.done ) {
            HandleEnumeration he = fvql.execute
                (0, 0, Constants.IRLOCK, Constants.RLOCK);

            System.out.println ();
            System.out.println ("Stock Market Report");
            while ( he.hasMoreHandles() ) {
                Handle quoteHandle = he.nextHandle ();
                System.out.println (quoteHandle.get (symbol_attr) + ": $" +
				    quoteHandle.get (price_attr) / 100.0);
            }
            System.out.println ();
            session.commit ();

            try { sleep (1000); } catch (InterruptedException ex) {}
        }
        session.endSession ();
    }
}

class StockMarketWriter extends Thread
{
    public void run ()
    {
        FundSession session = new FundSession (FundStockMarket.dbName, "writer");
        AttrString symbol_attr = session.newAttrString ("symbol");
        AttrInt    price_attr  = session.newAttrInt ("price");

        FundVQLQuery fvql = new FundVQLQuery (session,
            "select selfoid from StockQuote where symbol = $1");

        for (int i = 0; i < 200; i++) {
            String symbol = FundStockMarket.randomSymbol ();
            fvql.bind (symbol);

            HandleEnumeration he = fvql.execute
                (0, 0, Constants.IWLOCK, Constants.WLOCK);
            Handle quoteHandle = he.nextHandle ();

            int delta = FundStockMarket.random.nextInt() % 200;
            quoteHandle.put (price_attr, quoteHandle.get (price_attr) + delta);

            System.out.println ("Market update: " + symbol +
                (delta >= 0 ? " up " : " down ") + Math.abs (delta) / 100.0);
            session.commit ();                

            try { sleep (50); } catch (InterruptedException ex) {}
        }

        session.endSession ();
        FundStockMarket.done = true;
    }
}
