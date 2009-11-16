Copyright (c) 1999, Versant Corporation

J/Versant Interface README file for examples using the ODMG
or Transparent package

Example programs
----------------

The following example programs can be run individually:

        ListExample1
        ListExample2
        NLGames
        ODMGExample1
        ODMGExample2
        ODMGExample3
        ODMGExample4
        OLExample
        StockMarket
        TransExample1
        VHashtableExample
        VqlCollOdmg
        VqlCollTrans
        VqlTrans
        VVectorExample
	SecurityDemo
	VAT/TransVATDemo
	


To set environment
------------------

Before running the example programs, please make sure you
have set the necessary environment variables for your
JDK installation, Versant ODBMS installation and JVI installation,
check your CLASSPATH properly set or not.

In addition the ant make tool must be installed and all nesseccary environment variables 
must be set. 



For solaris platforms please refer to section "Modify the environment
variables to run JVI after the installation" of the Versant Release
Notes for details.

For HP-UNIX make sure the SHLIB_PATH variable has been set for the
database server. You could do this by setting the SHLIB_PATH in a
shell script and invoke this script from the /etc/inetd.conf,
instead of the ss.d daemon.  The ss.d daemon will then need to be
invoked from this script.
For example: If you installed Versant in /usr/local/versant and
create the script /usr/local/versant/start_versant.
Your /etc/inetd.conf file should change to look like this:
oscssd stream tcp nowait root /usr/local/versant/start_versant in.oscssd
Snippet of the shell-script :
"/usr/local/versant/start_versant"

#!/bin/sh -f
VERSANT_ROOT=/usr/local/versant
SHLIB_PATH=/usr/local/versant/lib
export VERSANT_ROOT  SHLIB_PATH
#invoke the ss.d daemon
exec ${VERSANT_ROOT}/bin/ss.d

For RHL8.0, to run VAT demo, set environment variable LANG to en_US.
For C shell, the command is:
setenv LANG en_US
For K shell, the command is:
export LANG=en_US 


To run the program
------------------
To run the example applications change to the <VERSANT_ROOT>/demo/JVI/odmg-trans directory.

To run the examples type:
> ant

