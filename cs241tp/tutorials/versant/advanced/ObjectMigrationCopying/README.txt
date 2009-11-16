
J/Versant Interface README for demo programs 'Migrate & CopyObj'
----------------------------------------------------------------

The program named as Migrate demonstrates how we can migrate an object from one 
database to another.

The program CopyObj demonstrates how we can copy objects from one 
database to another.

The program Migrate works on a class Migate and CopyObj uses class CopyObj.

 
How the program 'Migrate' works
-------------------------------

The user must specifies the names of the source and destination databases.
An object 'prop' of class Properties is used to set the options for instantiating 
TransSession.Here we use the object 'prop' for specifying the database.
We can begin a session by instantiating FundSession.

Using methods locateClass() and dropClass() together to drop the class 'Human' if it
is existing.

With an  object attrs of AttrBuilder[] class we defines attributes 'name' and 'weight'
of the class 'Human'.Then we  register the class 'Human'with defineClass() function invoked
by the FundSession object.

Then we define two objects of 'Human' using Handles h1 and h2.The transaction is 
commited using commit().

Using  connectDatabase() method we establish a connection between source and destination
databases.

migrateObjects() method Physically moves the objects in this vector from database sourcedb
to database destdb.

How the program 'CopyObj' works
-------------------------------

The 'CopyObj' program works in a way similar to 'Migrate', the only defference is that 
instead of migrateObjects() method we use copyObjects() method.

copyObjects()  Without changing object identifiers, copies the objects in this 
vector from one sourcedb to destdb.

Set Environment
---------------

Before running the example programs, please make sure you
have set the necessary environment variables for your
JDK installation, Versant ODBMS installation and JVI installation,
check your CLASSPATH properly set or not.

In addition the ant make tool must be installed and all nesseccary environment variables 
must be set. 


To run the program
------------------
To run the example application change to the <VERSANT_ROOT>/demo/JVI/ObjectMigrationCopying directory.

To run the examples type:
> ant

