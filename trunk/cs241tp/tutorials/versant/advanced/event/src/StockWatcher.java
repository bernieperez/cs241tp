import model.*;
import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public
class StockWatcher extends Frame implements VersantEventListener
{
    public static
    void main (String[] args) throws Throwable
    {
        if (args.length < 5) {
            System.out.println ("Usage: java StockWatcher <database> <daemon host> <daemon port> <client host> <client port>");
            System.exit (-1);
        }

        String database   = args [0];
        String daemonHost = args [1];
        int    daemonPort = Integer.parseInt (args [2]);
        String clientHost = args [3];
        int    clientPort = Integer.parseInt (args [4]);

        StockQuote.initDatabase (database);
        new StockWatcher (database, daemonHost, daemonPort, clientHost, clientPort);
    }

    public
    StockWatcher ( String database,
                   String daemonHost,
                   int    daemonPort,
                   String clientHost,
                   int    clientPort ) throws Throwable
    {
        // Start a session in which to access the persistent StockQuote
        // objects.  The transaction associated with this session will
        // never commit or rollback, so we don't want to hold locks on
        // the persistent objects.  Therefore we set the default lock
        // mode to NOLOCK.  Also, we never modify any objects, so we can
        // declare this to be a read-only session.
        Properties prop = new Properties ();
        prop.put ("database", database);
        prop.put ("lockmode", Constants.NOLOCK + "");
        prop.put ("options",  Constants.READ_ACCESS + "");
        session = new TransSession (prop);

        // Read in the initial prices of each of the stocks.  Subsequently
        // we will only re-read the prices when we are notified of a
        // change in the price.
        double[] prices = new double [StockQuote.stocks.length];
        for (int i = 0; i < StockQuote.stocks.length; i++) {
            StockQuote quote = (StockQuote) 
                session.findRoot (StockQuote.stocks [i]);
            prices [i] = quote.price;
        }

        // The main thread will no longer be used to access the database.
        // So we remove the association between the thread and the
        // session.  The session will remain open, however, so that the
        // callback can access the persistent StockQuote objects.  This
        // callback will be invoked in a separate, event-handler thread.
        session.leaveSession ();

        // Create the event client, connecting to the event daemon.
        EventClient client = new EventClient
            (daemonHost, daemonPort, clientHost, clientPort, database);    

        // If an exception occurs in the JVI event-handler thread, we
        // can receive notification of this error by adding an
        // ExceptionListener to the client.  This kind of exception
        // typically occurs when the daemon quits abnormally, while
        // there are still clients listening for events.
        ExceptionListener exception_listener = new ExceptionListener ()
        {
            // When the event-handler thread calls exceptionOccurred,
            // the EventClient automatically shuts down.  There is no
            // no way to restart it; however, a new EventClient could
            // be created.
            public void exceptionOccurred (Throwable exception)
            {
                // In this example, we simply print out the stack
                // trace and exit.
                exception.printStackTrace ();
                quit ();
            }
        };
        client.addExceptionListener (exception_listener);

        // If some other StockWatcher has already created an event
        // channel named "stock", then get it.  Otherwise this call
        // will return null.
        EventChannel channel = client.getChannel ("stock");

        // If the channel did not already exist, then create a new one.
        if (channel == null) {
            // The "stock" channel will be class-based, listening for
            // changes to instances of the StockQuote class.
            ClassChannelBuilder builder =
                new ClassChannelBuilder ("model.StockQuote");

            // Create a new channel using the channel builder.  After
            // this, any event client can receive events on the "stock"
            // channel.
            channel = client.newChannel ("stock", builder);
        }

        // A ClassEventListener is required to receive events on a
        // class-based channel.
        ClassEventListener event_listener = new ClassEventListener ()
        {
            // We are interested only in modifications to StockQuote
            // objects.
            public void instanceModified (VersantEventObject event)
            {
                // Since the JVI event-handler thread is not the same
                // as the main thread, we need to set the session of
                // the thread before doing any database activity,
                // including getting the persistent raiser object.
                session.setSession ();

                // The raiser is the object that caused the event to
                // be generated - in this case the StockQuote object
                // that was modified.
                StockQuote quote = (StockQuote) event.getRaiser (session);

                // The event tells us that the StockQuote object was
                // modified in some other transaction.  This is only
                // possible because the transaction associated with this
                // session is not holding any locks on the object.
                // Therefore the instance in this session is "out-of-date."
                // We can solve that by refreshing it.  We still don't
                // want to keep the object locked, though, so we use
                // NOLOCK.
                Object[] array = { quote };
                session.refreshObjects
                    (array, session.database(), Constants.NOLOCK);

                // Update the display of the symbol in the GUI panel.
                updateSymbol (quote);

                // We shouldn't leave the event-handler thread associated
                // with this session.  If we did, then we could have
                // trouble ending the session later on, because there would
                // still be a thread associated with it.
                session.leaveSession ();
            }

            public void instanceCreated (VersantEventObject event) {}
            public void instanceDeleted (VersantEventObject event) {}
        };

        // Add the event listener object to the "stock" channel.  Now
        // the instanceModified method will be invoked whenever a
        // StockQuote object is modified.
        channel.addVersantEventListener (event_listener);

        setTitle("Stock Quotes");
        setLayout(null);
        setVisible(false);
        setSize(321,230);
        setBackground(new Color(12368073));

        labelStock = new Label("Stock");
        labelStock.setBounds(36,12,60,24);
        labelStock.setFont(new Font("Dialog", Font.BOLD|Font.ITALIC, 18));
        labelStock.setForeground(new Color(16711935));
        add(labelStock);
        labelStockVSNT = new Label("VSNT");
        labelStockVSNT.setBounds(36,60,60,24);
        labelStockVSNT.setFont(new Font("Dialog", Font.BOLD, 18));
        add(labelStockVSNT);
        labelStockMSFT = new Label("MSFT");
        labelStockMSFT.setBounds(36,96,60,24);
        labelStockMSFT.setFont(new Font("Dialog", Font.BOLD, 18));
        add(labelStockMSFT);
        labelStockSUNW = new Label("SUNW");
        labelStockSUNW.setBounds(36,132,60,24);
        labelStockSUNW.setFont(new Font("Dialog", Font.BOLD, 18));
        add(labelStockSUNW);
        labelStockORCL = new Label("ORCL");
        labelStockORCL.setBounds(36,168,60,24);
        labelStockORCL.setFont(new Font("Dialog", Font.BOLD, 18));
        add(labelStockORCL);

        labelPrice = new Label("Price");
        labelPrice.setBounds(216,12,48,24);
        labelPrice.setFont(new Font("Dialog", Font.BOLD|Font.ITALIC, 18));
        labelPrice.setForeground(new Color(16711935));
        add(labelPrice);
        String priceString = getPriceFormatted (prices[0]);
        labelVSNT = new Label("" + priceString,Label.RIGHT);
        labelVSNT.setBounds(180,60,84,24);
        labelVSNT.setFont(new Font("Dialog", Font.PLAIN, 18));
        labelVSNT.setForeground(new Color(255));
        add(labelVSNT);
        priceString = getPriceFormatted (prices[1]);
        labelMSFT = new Label("" + priceString,Label.RIGHT);
        labelMSFT.setBounds(180,96,84,24);
        labelMSFT.setFont(new Font("Dialog", Font.PLAIN, 18));
        labelMSFT.setForeground(new Color(255));
        add(labelMSFT);
        priceString = getPriceFormatted (prices[2]);
        labelSUNW = new Label("" + priceString,Label.RIGHT);
        labelSUNW.setBounds(180,132,84,24);
        labelSUNW.setFont(new Font("Dialog", Font.PLAIN, 18));
        labelSUNW.setForeground(new Color(255));
        add(labelSUNW);
        priceString = getPriceFormatted (prices[3]);
        labelORCL = new Label("" + priceString,Label.RIGHT);
        labelORCL.setBounds(180,168,84,24);
        labelORCL.setFont(new Font("Dialog", Font.PLAIN, 18));
        labelORCL.setForeground(new Color(255));
        add(labelORCL);
        setVisible (true);

        WindowListener closeListener = new WindowAdapter ()
        {
            public void windowClosing (WindowEvent event)
            {
                quit ();
            }
        };
        addWindowListener (closeListener);
    }

    void quit ()
    {
        session.setSession ();
        session.endSession ();
        System.exit (0);
    }

    public void updateSymbol (StockQuote quote)
    {
        String price = getPriceFormatted (quote.price);
        Label  label = null;

        if (quote.symbolName.equals ("VSNT"))
            label = labelVSNT;
        else if (quote.symbolName.equals ("MSFT"))
            label = labelMSFT;
        else if (quote.symbolName.equals ("SUNW"))
            label = labelSUNW;
        else if (quote.symbolName.equals ("ORCL"))
            label = labelORCL;

        label.setText (price);
        label.repaint ();
        repaint ();
    }

    public void addNotify ()
    {
        // Record the size of the window prior to calling parents addNotify.
        Dimension d = getSize();

        super.addNotify();
	
        if (fComponentsAdjusted)
            return;
	
        // Adjust components according to the insets
        setSize(getInsets().left + getInsets().right + d.width,
                getInsets().top + getInsets().bottom + d.height);
        Component components[] = getComponents();
        for (int i = 0; i < components.length; i++) {
            Point p = components[i].getLocation();
            p.translate(getInsets().left, getInsets().top);
            components[i].setLocation(p);
        }
        fComponentsAdjusted = true;
    }


    String getPriceFormatted (double price)
    {
        NumberFormat numFormat = NumberFormat.getInstance ();
        numFormat.setMaximumFractionDigits (2);
        return numFormat.format (price);
    }

    TransSession session;

    // Used for addNotify check.
    boolean fComponentsAdjusted = false;
	
    Label labelStock;
    Label labelStockVSNT;
    Label labelStockMSFT;
    Label labelStockSUNW;
    Label labelStockORCL;
    Label labelPrice;
    Label labelVSNT;
    Label labelMSFT;
    Label labelSUNW;
    Label labelORCL;
}
