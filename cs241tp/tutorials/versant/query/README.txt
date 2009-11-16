
Copyright (c) 2005, Versant Corporation

J/Versant Interface README for 7.0 query examples

Example programs
----------------

Following are demo programs, each can be run individually:
	
runOrderby:
- Some example queries that show the use of the "ORDER BY" clause.

runStringMatch:
- Shows different queries for matching certain strings.

runClassDemo:
- Selects objects that are of a specific class.

runNumericDemo:
- A demonstration of how numerical operations can be used in the WHERE clause.

runSetParam:
- Demonstrates how to use place holders and how to bind values at runtime.

runExistDemo:
- example queries using the "for all" and "exists" conditions

runCheckInDemo:
- example queries using the "in" condition.

runSetDemo:
- Shows how to use set operators.


runSetCandidate:
- This demo shows how to set candidate collection for a query.


runCursor:
- shows how to use the fetch size feature of Versant query.
  It is a backend function which is transparent to customers. 
  The only thing client needs to do is calling Query->setFetchSize() 
  or QueryResult->next(size) to set fetch size to a certain number. 
  In case of large-volume result set, this helps shorten reponse 
  time and improve performance.

runIndexDemo:
- This demo shows how to query with indexed fields. Index is used automatically 
  and transparently when query executing if there is an appropriate index for 
  the specific field compared. This means no extra operation needed for query 
  with indexes. However, indexes should be created before querying by using 
  "dbtool -index -create <class> <attribute> <dbname>" command. 
  Query with proper indexes can help to improve performance.

runRedHonda:
- This demo shows how to solve the red honda problem by using operator 
  "for all/exists". It uses Class Broker, which has a field Broker->dealers, 
  pointing to multiple instances of class Dealer. In turn, class Dealer has 
  a field Dealer->vehicles, pointing to multiple instances of class Vehicle. 
  Class Vehicle has two fields: color and brand. 
  Query 1:
   select selfoid from model.Broker where for all x in dealers as model.Dealer : 
    ( for all y in x->vehicles as model.Vehicle : ( y->color like "red" and y->brand like "Honda"))
   This query tries to find Brokers that all its dealers' vehicles are "red Honda".

  Query 2:
   select selfoid from model.Broker where exists x in dealers as model.Dealer : 
    ( exists y in x->vehicles as model.Vehicle : ( y->color like "red" and y->brand like "Honda"))
   This query tries to find Brokers that have at least one dealer which has at least one "red Honda". 



All of these demos perform operation on the ebook data model except redhonda.  

Set Environment
---------------

Before running the example programs, please make sure you
have set the necessary environment variables for your
JDK installation, Versant ODBMS installation and JVI installation,
check your CLASSPATH properly set or not.

In addition the ant make tool must be installed and all nesseccary environment variables 
must be set. 

To run an example
-----------------
To run the example application change to the <VERSANT_ROOT>/demo/JVI/query directory.

> ant runOrderby
> ant	runStringMatch
> ant	runClassDemo
> ant	runNumericDemo
> ant	runSetParam
> ant	runExistDemo
> ant	runCheckInDemo
> ant	runSetDemo
> ant	runSetCandidate
> ant	runCursor
> ant	runIndexDemo
> ant	runRedHonda

Also use
> ant list
to list all example application there are.

