
J/Versant Interface README for demo program 'MemoryMngmt'
--------------------------------------------------

This program demonstrates the usage of  various memory management functions.
This program works on a class named MemoryMngmt. 

How the program works
---------------------

The user must specify the name of the database.
An object 'prop' of class Properties is used to set the options for instantiating 
TransSession.Here we use the object 'prop' for specifying the database.
An object of class Capability 'capability' is used for starting a secure session.

We can begin a session by instantiating FundSession.

Using methods locateClass() and dropClass() together to drop the class 'Human' if it
is existing.

With an  object attrs of AttrBuilder[] class define attributes 'name' and 'weight'
of the class 'Human'. Register the class 'Human' with defineClass() function invoked
by the FundSession object.

Define two objects of 'Human' using Handles h1 and h2.The transaction is 
commited using commit().

cacheFreeMaxKb() returns the size in kilobytes of the largest free contiguous 
space of object memory cache. 

cacheFreeKb() Returns the size of object memory cache that is free (0 to 100). 

cacheFreePercent() Returns the percentage of object memory cache that is free (0 to 100). 

The above 3 methods are present in the class FundSession.

The method releaseObjects() immediately releases the memory space used by the objects 
in this vector.We can specify various options as arguments to releseObjects() method.
 options parameters are: 
0-> Release all objects. 
Constants.SKIP_NEW 
Constants.SKIP_DIRTY
This method is present in the class HandleVector.


groupWriteObjects
-----------------
groupWriteObjects(int options)

Writes objects as a group. 

Alternatives for options are: 


0 Null option. 

Constants.FREE_OBJECTS Release the objects in this vector from memory if they are
 not pinned in another pin region. Handles to the specified objects remain valid.
 
Constants.CLEAN_CODS Immediately clear the cached object descriptor table of all 
entries except those for class objects, dirty objects, and pinned objects. 

commitAndCleanCod
--------------------
Commits transactions and clears cache.
 
This method commits all persistent object modifications made within the session, 
releases locks held on all objects, releases all COD entries from the VERSANT 
front-end object memory cache

ZapObj
-------
This example uses the zapObjects().

The method zapObjects() releases the objects in this vector from memory and makes
their cached object descriptors available for re-use. It also releases cached object
descriptors and invalidates handles to the corresponding objects.Handles in this vector 
should not be re-used after invocation of this method.This method present in the class
HandleVector.

Set Environment
---------------

Before running the example programs, please make sure you
have set the necessary environment variables for your
JDK installation, Versant ODBMS installation and JVI installation,
check your CLASSPATH properly set or not.

In addition the ant make tool must be installed and all nesseccary environment variables 
must be set. 


To run the program
-----------------
To run the example application change to the <VERSANT_ROOT>/demo/JVI/MemoryManagement directory.

To run the examples type:
> ant

