Copyright (c) 1999, Versant Corporation

J/Versant Interface README file for examples using the
Event Notification package


Example programs
----------------

There are two Event Notification demo programs:
    StockTrader
    StockWatcher

Although these programs can be run individually, they work together
using the event notification mechanism.  The StockTrader application
modifies StockQuote objects in the database, resulting in events
being generated.  The StockWatcher responds to these events by updating
its display of current stock quotes.


To set environment
------------------

Before running the example programs, please make sure you
have set the necessary environment variables for your
JDK installation, Versant ODBMS installation and JVI installation.

For solaris please refer to section "Modify the environment
variables to run JVI after the installation" of the Versant Release
Notes for details.


To compile and enhance example programs
---------------------------------------

 - run the ant build tool


To run an individual example
----------------------------
Note : Please make sure to run the examples with new database/s.

1. Make sure that the current directory is in your CLASSPATH
   environment variable. 

2. Create the database needed by the example.

        makedb <database name>
        createdb <database name>

3. Use the "sch2db" utility to store schema objects in the database.

    For Unix platforms:
        sch2db -d <database name> -y `oscp -p`/lib/channel.sch `oscp -p`/lib/vedsechn.sch

   For Windows platforms:
       sch2db -d <database name> -y  %VERSANT_ROOT%/lib/channel.sch  %VERSANT_ROOT%/lib/vedsechn.sch
        

4. Start the event daemon.  Note: Since this process does not
   automatically run in the background, you may want to run the program
   in a separate command window. 

        veddriver <database name> config.ved

   
   Note: the config file for veddriver can also be specified by setting the
   environment variable VED_CONFIGFILE to the name of the config file. So an
   alternate use for veddriver would follow these steps:

   For Unix platforms:
   setenv VED_CONFIGFILE config.ved

   For Windows platforms:
   set VED_CONFIGFILE=config.ved

   start up the event daemon using the command:
   veddriver <database name>

5. Run the StockTrader application.  Note: You can start multiple
   instances of this application.

       java StockTrader <database name>

6. Run the StockWatcher application.  Note: You can start multiple
   instances of this application.  However, each instance must have a
   unique client port.  For example, a second instance could use port
   3201, a third instance 3202, etc.

       java StockWatcher <database name> <hostname> 4000 <hostname> 3200

7. Make changes in the StockTrader application, and observe the changes
   in the StockWatcher application.

