
J/Versant Interface README for demo programs performing User authentication operations
--------------------------------------------------------------------------------------


AddUser
-------

We can use the AddUser program to add a new user to the userlist of a particular database.     
Database name, username, password and access mode must be specified.

addDBUser() method of DBUtility class is the method used for creating a user.



StudAccess
--------

The program 'StudAccess' shows how we can check the validity of a user when he tries to access a
database.

The user must specify a database and his username and password for accessing that database
at the time of executing the program.If the username and password is not present in the 
userlist of database an exception will be raised.

To set environment
------------------

Before running the example programs, please make sure you
have set the necessary environment variables for your
JDK installation, Versant ODBMS installation and JVI installation,
check your CLASSPATH properly set or not.

In addition the ant make tool must be installed and all nesseccary environment variables 
must be set. 


To run the program
------------------
To run the example applications change to the <VERSANT_ROOT>/demo/JVI/odmg-trans directory.

To run the examples type:
> ant

