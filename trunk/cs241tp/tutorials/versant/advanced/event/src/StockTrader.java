import model.*;
//import com.versant.fund.*;
import com.versant.trans.*;
//import com.versant.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StockTrader extends Frame
{
    public static
    void main (String[] args) throws Throwable
    {
        if (args.length < 1) {
            System.out.println ("Usage: java StockTrader <database>");
            System.exit (-1);
        }

        String database = args [0];
        StockQuote.initDatabase(database);
        new StockTrader (database);
    }

    void submitTrade (String stock, boolean buy, int quantity)
    {
        // In GUI applications, the event-handler thread is sometimes
        // different than that of the main thread (at least it is in
        // the Swing GUI toolkit).  Hence, in each GUI callback, we need
        // to set the session of the thread.
        session.setSession ();

        // Use a database query to select the named StockQuote object
        String query = "select selfoid from model.StockQuote where symbolName = $1";
        VQLQuery vql = new VQLQuery (session, query);
        vql.bind (stock);

        // Here we assume that there is exactly one object matching the
        // query.  This code will fail if that assumption is wrong!
        Enumeration e     = vql.execute ();
        StockQuote  quote = (StockQuote) e.nextElement ();

        // Adjust the price of the stock based on the quantity bought or sold.
        quote.adjustPrice (buy ? quantity : -quantity);

        // Commit this change to the database
        session.commit ();

        // Since we set the session at the beginning of this method, we
        // should leave it at the end.  This prevents the GUI event-handler
        // thread from "hanging on" to the session, which makes it
        // difficult to end the session properly.
        session.leaveSession ();
    }

    TransSession  session;

    Button        buttonSubmit;
    Button        buttonQuit;
    CheckboxGroup stockGroup;
    Checkbox      radioVSNT;
    Checkbox      radioMSFT;
    Checkbox      radioSUNW;
    Checkbox      radioORCL;
    TextField     textQuantity;
    Label         labelQuantity;
    CheckboxGroup tradeGroup;
    Checkbox      radioBuy;
    Checkbox      radioSell;

    // Used for addNotify check.
    boolean fComponentsAdjusted = false;

    public
    StockTrader (String database)
    {
        session = new TransSession (database);
        session.leaveSession ();

        setTitle("Stock Trades");
        setLayout(null);
        setSize(535,351);
        setBackground(new Color(12632256));
        buttonSubmit = new Button();
        buttonSubmit.setLabel("Submit Trade");
        buttonSubmit.setBounds(132,276,113,42);
        buttonSubmit.setFont(new Font("Dialog", Font.PLAIN, 18));
        buttonSubmit.setForeground(new Color(16776960));
        buttonSubmit.setBackground(new Color(8421504));
        add(buttonSubmit);
        buttonQuit = new Button();
        buttonQuit.setLabel("Quit");
        buttonQuit.setBounds(300,276,110,38);
        buttonQuit.setFont(new Font("Dialog", Font.PLAIN, 18));
        buttonQuit.setForeground(new Color(16776960));
        buttonQuit.setBackground(new Color(8421504));
        add(buttonQuit);
        stockGroup = new CheckboxGroup();
        radioVSNT = new Checkbox("VSNT", stockGroup, true);
        radioVSNT.setBounds(228,12,87,30);
        radioVSNT.setFont(new Font("Dialog", Font.PLAIN, 18));
        radioVSNT.setForeground(new Color(32896));
        add(radioVSNT);
        radioMSFT = new Checkbox("MSFT", stockGroup, false);
        radioMSFT.setBounds(228,48,87,30);
        radioMSFT.setFont(new Font("Dialog", Font.PLAIN, 18));
        radioMSFT.setForeground(new Color(32896));
        add(radioMSFT);
        radioSUNW = new Checkbox("SUNW", stockGroup, false);
        radioSUNW.setBounds(228,84,87,30);
        radioSUNW.setFont(new Font("Dialog", Font.PLAIN, 18));
        radioSUNW.setForeground(new Color(32896));
        add(radioSUNW);
        radioORCL = new Checkbox("ORCL", stockGroup, false);
        radioORCL.setBounds(228,120,87,30);
        radioORCL.setFont(new Font("Dialog", Font.PLAIN, 18));
        radioORCL.setForeground(new Color(32896));
        add(radioORCL);
        textQuantity = new TextField();
        textQuantity.setBounds(72,192,96,33);
        add(textQuantity);
        labelQuantity = new Label("Quantity");
        labelQuantity.setBounds(84,156,72,24);
        labelQuantity.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(labelQuantity);
        tradeGroup = new CheckboxGroup();
        radioBuy = new Checkbox("Buy", tradeGroup, true);
        radioBuy.setBounds(264,192,60,30);
        radioBuy.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(radioBuy);
        radioSell = new Checkbox("Sell", tradeGroup, false);
        radioSell.setBounds(360,192,60,30);
        radioSell.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(radioSell);
        setVisible (true);

        ActionListener submitListener = new ActionListener ()
        {
            public void actionPerformed (ActionEvent event)
            {
                Checkbox selectedStock  = stockGroup.getSelectedCheckbox ();
                String   stock          = selectedStock.getLabel ();
                Checkbox selectedTrade  = tradeGroup.getSelectedCheckbox ();
                boolean  buy            = selectedTrade == radioBuy;
                String   quantityString = textQuantity.getText ();
                int      quantity       = Integer.parseInt (quantityString);

                submitTrade (stock, buy, quantity);
            }
        };
        buttonSubmit.addActionListener (submitListener);

        ActionListener quitListener = new ActionListener ()
        {
            public void actionPerformed (ActionEvent event)
            {
                quit ();
            }
        };
        buttonQuit.addActionListener (quitListener);

        WindowListener closeListener = new WindowAdapter ()
        {
            public void windowClosing (WindowEvent event)
            {
                quit ();
            }
        };
        addWindowListener (closeListener);
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

    void quit ()
    {
        session.setSession ();
        session.endSession ();
        System.exit (0);
    }
}
