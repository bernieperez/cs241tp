Java Versant Interface README for examples using JVI utility APIs.
------------------------------------------------------------------

Following are demo programs:

1. CreateDBs :  
   ---------
   This example helps the user to create a new database.

2. CreateUser : 
   ---------
   This example helps the DBA to create and delete users for a database.
   
3. ClassCompDB : 
   ---------
   This example compares the object of class student in the two databases.It is speceified using the -class option.

4. CompDB : 
   ------
   This example compares two databases and display all objects which are not identical.

5. CopyDB : 
   -------
   This example copies the contents of one database to an empty database.
   
6. DBComp : 
   -------
   This example shows how we can compare two databases using CompareDBResult method.

7. DBInformation : 
   -------------
   This example provides details about the databases like
   .Details about databases in the machine specified.
   .Details about databases owned by a particular user.
   .Details about the database specified.
   .Details about databases in the VersantDB directory.
   .Details about users of database.
   .DBEnviornment Information.
   .DataBase mode

8. BackupDB : 
   ---------
   This example helps the user to backup a database.
	
9. RestoreDB : 
   ---------
   This example helps the user to restore the database.

10. DBStatistics : 
   --------
    This example demonstrates the usage of some of the methods in Statistics class.


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
To run the example application change to the <VERSANT_ROOT>/demo/JVI/DataBaseUtility directory.

To run the examples type:
> ant

