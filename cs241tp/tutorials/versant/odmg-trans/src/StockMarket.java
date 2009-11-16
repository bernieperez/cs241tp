import com.versant.fund.*;
import com.versant.trans.*;
import java.util.*;

public class StockMarket
{
    public static void main (String[] args) throws InterruptedException
    {
        if (args.length != 1) {
            System.out.println ("Usage: java StockMarket <database>");
            System.exit (1);
        }
        database = args [0];

        TransSession session = new TransSession (database);
        for (int i = 0; i < symbols.length; i++) {
            StockQuote quote = new StockQuote ();
            quote.symbol = symbols [i];
            quote.price  = Math.abs(random.nextInt()) % 10000;
            session.makePersistent (quote);
        }
        session.commit ();

        new StockMarketReader (session).start ();
        new StockMarketWriter (session).start ();
    }

    static String randomSymbol ()
    {
        int index = Math.abs(random.nextInt()) % symbols.length;
        return symbols [index];
    }

    static String   database;
    static boolean  done    = false;
    static Random   random  = new Random ();
    static String[] symbols = { "vsnt", "sunw", "ibm", "msft", "orcl",
                                "hwp", "cpq", "dell", "sgi", "ifmx" };
}

class StockMarketReader extends Thread
{
    TransSession session;

    StockMarketReader (TransSession s)
    {
        session = s;
    }

    public void run ()
    {
        session.setSession ();
        VQLQuery vql =
	             new VQLQuery (session, "select selfoid from StockQuote");

        while (! StockMarket.done ) {
            Enumeration e =
                        vql.execute (0, 0, Constants.IRLOCK, Constants.RLOCK);

            synchronized (session) {
                System.out.println ();
                System.out.println ("Stock Market Report");
                while ( e.hasMoreElements() ) {
                    StockQuote quote = (StockQuote) e.nextElement ();
                    System.out.println (quote.symbol + ": $" +
                                        quote.price / 100.0);
                }
                System.out.println ();
                try { session.wait (); } catch (InterruptedException ex) {}
            }
        }

        session.leaveSession ();
    }
}

class StockMarketWriter extends Thread
{
    TransSession session;

    StockMarketWriter (TransSession s)
    {
        session = s;
    }

    public void run ()
    {
        session.setSession ();

        VQLQuery vql = new VQLQuery (session,
            "select selfoid from StockQuote where symbol = $1");

        for (int i = 0; i < 20; i++) {
            String symbol = StockMarket.randomSymbol ();
            vql.bind (symbol);

            Enumeration e = vql.execute
                (0, 0, Constants.IWLOCK, Constants.WLOCK);
            StockQuote quote = (StockQuote) e.nextElement ();

            int delta = StockMarket.random.nextInt() % 200;

            synchronized (session) {
                quote.price += delta;
                System.out.println ("Market update: " + symbol +
                    (delta >= 0 ? " up " : " down ") +
                    Math.abs (delta) / 100.0);
                session.commit ();
                session.notify ();
            }
            try { sleep (2000); } catch (InterruptedException ex) {}
        }

        StockMarket.done = true;
        session.leaveSession ();

        synchronized (session) {
            session.notify ();
        }
    }
}

class StockQuote
{
    String symbol;
    int price;
}
