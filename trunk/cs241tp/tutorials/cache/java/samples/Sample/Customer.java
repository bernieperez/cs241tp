package Sample;
import com.intersys.cache.*;
import com.intersys.objects.*;
import com.intersys.objects.reflect.*;
import com.intersys.classes.*;
import java.sql.Date;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import Sample.Address;


/**
 * Cache' Java Class Generated for class Sample.Customer on version Cache for Windows (Intel/P4) 5.2 (Build 129U) Wed Jun 29 2005 16:45:54 EDT.<br>
 *
 * @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer</A>
**/

public class Customer extends SerialObject implements java.io.Serializable {
    private static String CACHE_CLASS_NAME = "Sample.Customer";
    /**
           <p>NB: DO NOT USE IN APPLICATION(!!!).
           <br>Use <code>Sample.Customer.open</code> instead!</br></p>
           <p>
           Used to construct a Java object, corresponding to existing object
           in Cache database.
           </p>
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
    */
    public Customer (CacheObject ref) throws CacheException {
        super (ref);
    }
    public Customer (Database db, String initstr) throws CacheException {
        super (((SysDatabase)db).newCacheObject (CACHE_CLASS_NAME,initstr));
    }
    public static RegisteredObject createClientObject (Database db)throws CacheException {
        CacheObject cobj = (((SysDatabase)db).newClientObject(CACHE_CLASS_NAME));
        return new Customer (cobj);
    }
    public static SerialObject open (Database db, byte[] serialState) throws CacheException {
         CacheObject cobj = ((SysDatabase)db).deserializeObject (CACHE_CLASS_NAME, serialState);
         return (SerialObject) cobj.newJavaInstance();
    }
    /**
       Creates a new instance of object "Sample.Customer" in Cache
       database and corresponding object of class
       <code>Sample.Customer</code>.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @throws CacheException in case of error.

              @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
              @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
     */
    public Customer (Database db) throws CacheException {
        super (((SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }
    /**
       Runs method <code> %Open </code> in Cache to open an object
       from Cache database and creates corresponding object of class
       <code>Sample.Customer</code>.

       @return <code> RegisteredObject </code>, corresponding to opened
       object. This object may be of <code>Sample.Customer</code> or of
      any of its subclasses. Cast to <code>Sample.Customer</code> is
      guaranteed to pass without <code>ClassCastException</code> exception.

       @param db <code>Database</code> object used for connection with
       Cache database.
       @param oid Object ID as specified in Cache. represented as
      <code>Oid</code>.


       @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #Customer(com.intersys.objects.Database)
     */
    public static RegisteredObject _open (Database db, Oid oid) throws CacheException {
        return open(db, oid);
    }
    /**
       Runs method <code> %Open </code> in Cache to open an object
       from Cache database and creates corresponding object of class
       <code>Sample.Customer</code>.

       @return <code> RegisteredObject </code>, corresponding to opened
       object. This object may be of <code>Sample.Customer</code> or of
      any of its subclasses. Cast to <code>Sample.Customer</code> is
      guaranteed to pass without <code>ClassCastException</code> exception.

       @param db <code>Database</code> object used for connection with
       Cache database.
       @param oid Object ID as specified in Cache. represented as
      <code>Oid</code>.


       @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #Customer(com.intersys.objects.Database)
     */
    public static RegisteredObject open (Database db, Oid oid) throws CacheException {
        CacheObject cobj = (((SysDatabase)db).openCacheObject(CACHE_CLASS_NAME, oid.getData()));
        return (RegisteredObject)(cobj.newJavaInstance());
    }
    /**
       Runs method <code> %Open </code> in Cache to open an object
       from Cache database and creates corresponding object of class
       <code>Sample.Customer</code>.

       @return <code> RegisteredObject </code>, corresponding to opened
       object. This object may be of <code>Sample.Customer</code> or of
      any of its subclasses. Cast to <code>Sample.Customer</code> is
      guaranteed to pass without <code>ClassCastException</code> exception.

       @param db <code>Database</code> object used for connection with
       Cache database.
       @param oid Object ID as specified in Cache. represented as
      <code>Oid</code>.
      @param concurrency Concurrency level.  represented as
      <code>Concurrency</code>.

      Here are concurrency values, see Object Concurrency Options in your on-line Cache' documentation for more information.
      @see <a href = "http://mishaws:8973/csp/documatic/DocBook.UI.Page.cls?KEY=GOBJ_concurrency"> Object Concurrency Options.</A>

      <TABLE border="1"
      summary="Object Concurrency Options.">
      <CAPTION><EM>Object Concurrency Options</EM></CAPTION>
      <TR><TD>-1 </TD><TD>Default concurrency</TD></TR>
      <TR><TD>0 </TD><TD>No locking, no locks are used</TD></TR>
      <TR><TD>1 </TD><TD>Atomic</TD></TR>
      <TR><TD>2 </TD><TD>Shared</TD></TR>
      <TR><TD>3 </TD><TD>Shared/Retained</TD></TR>
      <TR><TD>4 </TD><TD>Exclusive</TD></TR>
      </TABLE>

      @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #Customer(com.intersys.objects.Database)
     */
    public static RegisteredObject _open (Database db, Oid oid, int concurrency) throws CacheException {
        return open(db, oid, concurrency);
    }
    /**
       Runs method <code> %Open </code> in Cache to open an object
       from Cache database and creates corresponding object of class
       <code>Sample.Customer</code>.

       @return <code> RegisteredObject </code>, corresponding to opened
       object. This object may be of <code>Sample.Customer</code> or of
      any of its subclasses. Cast to <code>Sample.Customer</code> is
      guaranteed to pass without <code>ClassCastException</code> exception.

       @param db <code>Database</code> object used for connection with
       Cache database.
       @param oid Object ID as specified in Cache. represented as
      <code>Oid</code>.
      @param concurrency Concurrency level.  represented as
      <code>Concurrency</code>.

      Here are concurrency values, see Object Concurrency Options in your on-line Cache' documentation for more information.
      @see <a href = "http://mishaws:8973/csp/documatic/DocBook.UI.Page.cls?KEY=GOBJ_concurrency"> Object Concurrency Options.</A>

      <TABLE border="1"
      summary="Object Concurrency Options.">
      <CAPTION><EM>Object Concurrency Options</EM></CAPTION>
      <TR><TD>-1 </TD><TD>Default concurrency</TD></TR>
      <TR><TD>0 </TD><TD>No locking, no locks are used</TD></TR>
      <TR><TD>1 </TD><TD>Atomic</TD></TR>
      <TR><TD>2 </TD><TD>Shared</TD></TR>
      <TR><TD>3 </TD><TD>Shared/Retained</TD></TR>
      <TR><TD>4 </TD><TD>Exclusive</TD></TR>
      </TABLE>

      @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #Customer(com.intersys.objects.Database)
     */
    public static RegisteredObject open (Database db, Oid oid, int concurrency) throws CacheException {
        CacheObject cobj = (((SysDatabase)db).openCacheObject(CACHE_CLASS_NAME, oid.getData(), concurrency));
        return (RegisteredObject)(cobj.newJavaInstance());
    }
    /**
       Runs method <code> %Delete </code> in Cache to delete an object
       from Cache database.

       Deletes the stored version of the object with OID <var>oid</var> from the database.
       It does not remove any in-memory versions of the object that may be present.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @param id ID as specified in Cache represented as
       <code>Id</code>.

       @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_deleteId(com.intersys.objects.Database, com.intersys.objects.Id)
           @see #Customer
     */
    public static void delete (Database db, Id id) throws CacheException {
        ((SysDatabase)db).deleteObject(CACHE_CLASS_NAME, id);
    }
    /**
       Runs method <code> %Delete </code> in Cache to delete an object
       from Cache database.

       Deletes the stored version of the object with OID <var>oid</var> from the database.
       It does not remove any in-memory versions of the object that may be present.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @param id ID as specified in Cache represented as
       <code>Id</code>.
       @param concurrency Concurrency level.  represented as
       <code>Concurrency</code>.

      Here are concurrency values, see Object Concurrency Options in your on-line Cache' documentation for more information.
      @see <a href = "http://mishaws:8973/csp/documatic/DocBook.UI.Page.cls?KEY=GOBJ_concurrency"> Object Concurrency Options.</A>

      <TABLE border="1"
      summary="Object Concurrency Options.">
      <CAPTION><EM>Object Concurrency Options</EM></CAPTION>
      <TR><TD>-1 </TD><TD>Default concurrency</TD></TR>
      <TR><TD>0 </TD><TD>No locking, no locks are used</TD></TR>
      <TR><TD>1 </TD><TD>Atomic</TD></TR>
      <TR><TD>2 </TD><TD>Shared</TD></TR>
      <TR><TD>3 </TD><TD>Shared/Retained</TD></TR>
      <TR><TD>4 </TD><TD>Exclusive</TD></TR>
      </TABLE>


       @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_deleteId(com.intersys.objects.Database, com.intersys.objects.Id)
           @see #Customer
     */
    public static void delete (Database db, Id id, int concurrency) throws CacheException {
        ((SysDatabase)db).deleteObject(CACHE_CLASS_NAME, id, concurrency);
    }
    /**
       Runs method <code> %Delete </code> in Cache to delete an object
       from Cache database.

       Deletes the stored version of the object with OID <var>oid</var> from the database.
       It does not remove any in-memory versions of the object that may be present.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @param id ID as specified in Cache represented as
       <code>Id</code>.

       @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_deleteId(com.intersys.objects.Database, com.intersys.objects.Id)
           @see #Customer
     */
    public static void _deleteId (Database db, Id id) throws CacheException {
        delete(db, id);
    }
    /**
       Runs method <code> %Delete </code> in Cache to delete an object
       from Cache database.

       Deletes the stored version of the object with OID <var>oid</var> from the database.
       It does not remove any in-memory versions of the object that may be present.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @param id ID as specified in Cache represented as
       <code>Id</code>.
       @param concurrency Concurrency level.  represented as
       <code>Concurrency</code>.

      Here are concurrency values, see Object Concurrency Options in your on-line Cache' documentation for more information.
      @see <a href = "http://mishaws:8973/csp/documatic/DocBook.UI.Page.cls?KEY=GOBJ_concurrency"> Object Concurrency Options.</A>

      <TABLE border="1"
      summary="Object Concurrency Options.">
      <CAPTION><EM>Object Concurrency Options</EM></CAPTION>
      <TR><TD>-1 </TD><TD>Default concurrency</TD></TR>
      <TR><TD>0 </TD><TD>No locking, no locks are used</TD></TR>
      <TR><TD>1 </TD><TD>Atomic</TD></TR>
      <TR><TD>2 </TD><TD>Shared</TD></TR>
      <TR><TD>3 </TD><TD>Shared/Retained</TD></TR>
      <TR><TD>4 </TD><TD>Exclusive</TD></TR>
      </TABLE>


       @throws CacheException in case of error.
      @see java.lang.ClassCastException
           @see #_deleteId(com.intersys.objects.Database, com.intersys.objects.Id)
           @see #Customer
     */
    public static void _deleteId (Database db, Id id, int concurrency) throws CacheException {
        delete(db, id, concurrency);
    }
    /**
       Returns class name of the class Sample.Customer as it is in
      Cache Database. Note, that this is a static method, so no
      object specific information can be returned. Use
      <code>getCacheClass().geName()</code> to get the class name
      for specific object.
       @return Cache class name as a <code>String</code>
      @see #getCacheClass()
      @see com.intersys.objects.reflect.CacheClass#getName()
     */
    public static String getCacheClassName( ) {
        return CACHE_CLASS_NAME;
    }

   /**
           Allows access metadata information about type of this object
           in Cache database. Also can be used for dynamic binding (accessing
           properties and calling methods without particular class known).

           @return <code>CacheClass</code> object for this object type.
   */
    public CacheClass getCacheClass( ) throws CacheException {
        return mInternal.getCacheClass();
    }

    /**
       Verifies that all fields from Cache class are exposed with
       accessor methods in Java class and that values for indexes in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p>But if there is any inconsistency in zObjVal indexes this is fatal and class can not work correctly and must be regenerated

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see com.intersys.objects.InvalidPropertyException

     */
    public static void checkAllFieldsValid(Database db ) throws CacheException {
        checkAllFieldsValid(db, CACHE_CLASS_NAME, Customer.class);
    }

    /**
       Verifies that all fields from Cache class are exposed with
       accessor methods in Java class and that values for indexes in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p>But if there is any inconsistency in zObjVal indexes this is fatal and class can not work correctly and must be regenerated

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see com.intersys.objects.InvalidPropertyException

     */
    public static void checkAllMethods(Database db ) throws CacheException {
        checkAllMethods(db, CACHE_CLASS_NAME, Customer.class);
    }
    private static int ii_Addresses = 2;
    private static int jj_Addresses = 0;
    private static int kk_Addresses = 1;
    /**
       Verifies that indexes for property <code>Addresses</code> in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p> Please note, that if there is any inconsistency in zObjVal
       indexes this is fatal and class can not work correctly and must
       be regenerated.

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see #checkAllFieldsValid

     */
    public static void checkAddressesValid (Database db) throws CacheException {
        checkZobjValid(db, CACHE_CLASS_NAME, "Addresses",ii_Addresses, jj_Addresses, kk_Addresses);
    }
    /**
       Returns value of property <code>Addresses</code>.
       <Description>
       @return current value of <code>Addresses</code> represented as
       <code>java.util.Map</code>

       @throws CacheException if any error occurred during value retrieval.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#Addresses"> Addresses</A>
    */
    public java.util.Map getAddresses() throws CacheException {
        Dataholder dh = mInternal.getProperty(ii_Addresses,
                                                jj_Addresses,
                                                Database.RET_OBJECT,
                                                "Addresses");
        CacheObject cobj = dh.getCacheObject();
        if (cobj == null)
            return null;
        return (java.util.Map)(cobj.newJavaInstance());
    }

    private static int ii_MailingAddresses = 4;
    private static int jj_MailingAddresses = 0;
    private static int kk_MailingAddresses = 3;
    /**
       Verifies that indexes for property <code>MailingAddresses</code> in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p> Please note, that if there is any inconsistency in zObjVal
       indexes this is fatal and class can not work correctly and must
       be regenerated.

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see #checkAllFieldsValid

     */
    public static void checkMailingAddressesValid (Database db) throws CacheException {
        checkZobjValid(db, CACHE_CLASS_NAME, "MailingAddresses",ii_MailingAddresses, jj_MailingAddresses, kk_MailingAddresses);
    }
    /**
       Returns value of property <code>MailingAddresses</code>.
       <Description>
       @return current value of <code>MailingAddresses</code> represented as
       <code>java.util.List</code>

       @throws CacheException if any error occurred during value retrieval.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#MailingAddresses"> MailingAddresses</A>
    */
    public java.util.List getMailingAddresses() throws CacheException {
        Dataholder dh = mInternal.getProperty(ii_MailingAddresses,
                                                jj_MailingAddresses,
                                                Database.RET_OBJECT,
                                                "MailingAddresses");
        CacheObject cobj = dh.getCacheObject();
        if (cobj == null)
            return null;
        return (java.util.List)(cobj.newJavaInstance());
    }

    private static int ii_MyAddress = 6;
    private static int jj_MyAddress = 0;
    private static int kk_MyAddress = 5;
    /**
       Verifies that indexes for property <code>MyAddress</code> in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p> Please note, that if there is any inconsistency in zObjVal
       indexes this is fatal and class can not work correctly and must
       be regenerated.

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see #checkAllFieldsValid

     */
    public static void checkMyAddressValid (Database db) throws CacheException {
        checkZobjValid(db, CACHE_CLASS_NAME, "MyAddress",ii_MyAddress, jj_MyAddress, kk_MyAddress);
    }
    /**
       Returns value of property <code>MyAddress</code>.
       <Description>
       @return current value of <code>MyAddress</code> represented as
       <code>Sample.Address</code>

       @throws CacheException if any error occurred during value retrieval.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#MyAddress"> MyAddress</A>
    */
    public Sample.Address getMyAddress() throws CacheException {
        Dataholder dh = mInternal.getProperty(ii_MyAddress,
                                                jj_MyAddress,
                                                Database.RET_OBJECT,
                                                "MyAddress");
        CacheObject cobj = dh.getCacheObject();
        if (cobj == null)
            return null;
        return (Sample.Address)(cobj.newJavaInstance());
    }

    /**
       Sets new value for <code>MyAddress</code>.
       <Description>
       @param value new value to be set represented as
       <code>Sample.Address</code>.
       @throws CacheException if any error occurred during value setting.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#MyAddress"> MyAddress</A>
    */
    public void setMyAddress(Sample.Address value) throws CacheException {
        Dataholder dh = new Dataholder (value);
        mInternal.setProperty(ii_MyAddress, jj_MyAddress,kk_MyAddress, Database.RET_OBJECT, "MyAddress", dh);
        return;
    }

    private static int ii_Name = 7;
    private static int jj_Name = 0;
    private static int kk_Name = 7;
    /**
       Verifies that indexes for property <code>Name</code> in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p> Please note, that if there is any inconsistency in zObjVal
       indexes this is fatal and class can not work correctly and must
       be regenerated.

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see #checkAllFieldsValid

     */
    public static void checkNameValid (Database db) throws CacheException {
        checkZobjValid(db, CACHE_CLASS_NAME, "Name",ii_Name, jj_Name, kk_Name);
    }
    /**
       Returns value of property <code>Name</code>.
       <Description>
       @return current value of <code>Name</code> represented as
       <code>String</code>

       @throws CacheException if any error occurred during value retrieval.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#Name"> Name</A>
    */
    public String getName() throws CacheException {
        Dataholder dh = mInternal.getProperty(ii_Name,
                                                jj_Name,
                                                Database.RET_PRIM,
                                                "Name");
       return dh.getString();
    }

    /**
       Sets new value for <code>Name</code>.
       <Description>
       @param value new value to be set represented as
       <code>String</code>.
       @throws CacheException if any error occurred during value setting.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#Name"> Name</A>
    */
    public void setName(String value) throws CacheException {
        Dataholder dh = new Dataholder (value);
        mInternal.setProperty(ii_Name, jj_Name,kk_Name, Database.RET_PRIM, "Name", dh);
        return;
    }

    private static int ii_Status = 8;
    private static int jj_Status = 0;
    private static int kk_Status = 8;
    /**
       Verifies that indexes for property <code>Status</code> in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p> Please note, that if there is any inconsistency in zObjVal
       indexes this is fatal and class can not work correctly and must
       be regenerated.

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see #checkAllFieldsValid

     */
    public static void checkStatusValid (Database db) throws CacheException {
        checkZobjValid(db, CACHE_CLASS_NAME, "Status",ii_Status, jj_Status, kk_Status);
    }
    /**
       Returns value of property <code>Status</code>.
       <Description>
       @return current value of <code>Status</code> represented as
       <code>String</code>

       @throws CacheException if any error occurred during value retrieval.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#Status"> Status</A>
    */
    public String getStatus() throws CacheException {
        Dataholder dh = mInternal.getProperty(ii_Status,
                                                jj_Status,
                                                Database.RET_PRIM,
                                                "Status");
       return dh.getString();
    }

    /**
       Sets new value for <code>Status</code>.
       <Description>
       @param value new value to be set represented as
       <code>String</code>.
       @throws CacheException if any error occurred during value setting.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#Status"> Status</A>
    */
    public void setStatus(String value) throws CacheException {
        Dataholder dh = new Dataholder (value);
        mInternal.setProperty(ii_Status, jj_Status,kk_Status, Database.RET_PRIM, "Status", dh);
        return;
    }

    private static int ii_TS = 9;
    private static int jj_TS = 0;
    private static int kk_TS = 9;
    /**
       Verifies that indexes for property <code>TS</code> in
       zObjVal are the same as in Cache. It does not return anything
       but it throws an exception in case of inconsistency.

       <p> Please note, that if there is any inconsistency in zObjVal
       indexes this is fatal and class can not work correctly and must
       be regenerated.

       @param db Database used for connection. Note that if you are
       using multiple databases the class can be consistent with one
       and inconsistent with another.
       @throws InvalidClassException if any inconsistency is found.
       @throws CacheException if any error occurred during
       verification, e.g. communication error with Database.
       @see #checkAllFieldsValid

     */
    public static void checkTSValid (Database db) throws CacheException {
        checkZobjValid(db, CACHE_CLASS_NAME, "TS",ii_TS, jj_TS, kk_TS);
    }
    /**
       Returns value of property <code>TS</code>.
       <Description>
       @return current value of <code>TS</code> represented as
       <code>Timestamp</code>

       @throws CacheException if any error occurred during value retrieval.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#TS"> TS</A>
    */
    public Timestamp getTS() throws CacheException {
        Dataholder dh = mInternal.getProperty(ii_TS,
                                                jj_TS,
                                                Database.RET_PRIM,
                                                "TS");
       return dh.getTimestamp();
    }

    /**
       Sets new value for <code>TS</code>.
       <Description>
       @param value new value to be set represented as
       <code>Timestamp</code>.
       @throws CacheException if any error occurred during value setting.
       @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#TS"> TS</A>
    */
    public void setTS(Timestamp value) throws CacheException {
        Dataholder dh = new Dataholder (value);
        mInternal.setProperty(ii_TS, jj_TS,kk_TS, Database.RET_PRIM, "TS", dh);
        return;
    }

    /**
     <p>Runs method sys_ClassName in Cache.</p>
     <p>Description: Returns the object's class name. The <var>fullname</var> determines how the
class name is represented. If it is 1 then it returns the full class name
including any package qualifier. If it is 0 (the default) then it returns the
name of the class without the package, this is mainly for backward compatibility
with the pre-package behaviour of %ClassName.</p>
     @param db represented as Database
     @param fullname represented as java.lang.Boolean
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%ClassName"> Method %ClassName</A>
    */
    public static java.lang.String sys_ClassName (Database db, java.lang.Boolean fullname) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(fullname);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%ClassName",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method sys_Delete in Cache.</p>
     <p>Description: Deletes streams referenced by this object and calls %Delete on any embedded objects

Refer to <LINK href=/AboutConcurrency.html>About Concurrency</LINK> for more details 
on the optional <var>concurrency</var> argument.

<p>Returns a <CLASS>%Status</CLASS> value indicating success or failure.
</p>
     @param db represented as Database
     default argument oid set to ""
     default argument concurrency set to -1
     @throws CacheException if any error occured while running the method.
     @see #sys_Delete(Database,com.intersys.objects.Oid,java.lang.Integer)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%Delete"> Method %Delete</A>
    */
    public static void sys_Delete (Database db) throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%Delete",args,Database.RET_PRIM);
        db.parseStatus(res);
        return;
    }
    /**
     <p>Runs method sys_Delete in Cache.</p>
     <p>Description: Deletes streams referenced by this object and calls %Delete on any embedded objects

Refer to <LINK href=/AboutConcurrency.html>About Concurrency</LINK> for more details 
on the optional <var>concurrency</var> argument.

<p>Returns a <CLASS>%Status</CLASS> value indicating success or failure.
</p>
     @param db represented as Database
     @param oid represented as com.intersys.objects.Oid
     default argument concurrency set to -1
     @throws CacheException if any error occured while running the method.
     @see #sys_Delete(Database,com.intersys.objects.Oid,java.lang.Integer)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%Delete"> Method %Delete</A>
    */
    public static void sys_Delete (Database db, com.intersys.objects.Oid oid) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(oid);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%Delete",args,Database.RET_PRIM);
        db.parseStatus(res);
        return;
    }
    /**
     <p>Runs method sys_Delete in Cache.</p>
     <p>Description: Deletes streams referenced by this object and calls %Delete on any embedded objects

Refer to <LINK href=/AboutConcurrency.html>About Concurrency</LINK> for more details 
on the optional <var>concurrency</var> argument.

<p>Returns a <CLASS>%Status</CLASS> value indicating success or failure.
</p>
     @param db represented as Database
     @param oid represented as com.intersys.objects.Oid
     @param concurrency represented as java.lang.Integer
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%Delete"> Method %Delete</A>
    */
    public static void sys_Delete (Database db, com.intersys.objects.Oid oid, java.lang.Integer concurrency) throws CacheException {
        Dataholder[] args = new Dataholder[2];
        args[0] = new Dataholder(oid);
        args[1] = new Dataholder(concurrency);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%Delete",args,Database.RET_PRIM);
        db.parseStatus(res);
        return;
    }
    /**
     <p>Runs method sys_DeleteId in Cache.</p>
     <p>Description: Deletes the stored version of the object with ID <var>id</var> from the database. 

<p><METHOD>%DeleteId</METHOD> is identical in operation to the <METHOD>%Delete</METHOD> method except 
that it uses and Id value instead of an OID value to find an object.

Refer to <LINK href=/AboutConcurrency.html>About Concurrency</LINK> for more details 
on the optional <var>concurrency</var> argument.</p>
     @param db represented as Database
     @param id represented as java.lang.String
     default argument concurrency set to -1
     @throws CacheException if any error occured while running the method.
     @see #sys_DeleteId(Database,java.lang.String,java.lang.Integer)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%DeleteId"> Method %DeleteId</A>
    */
    public static void sys_DeleteId (Database db, java.lang.String id) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(id);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%DeleteId",args,Database.RET_PRIM);
        db.parseStatus(res);
        return;
    }
    /**
     <p>Runs method sys_DeleteId in Cache.</p>
     <p>Description: Deletes the stored version of the object with ID <var>id</var> from the database. 

<p><METHOD>%DeleteId</METHOD> is identical in operation to the <METHOD>%Delete</METHOD> method except 
that it uses and Id value instead of an OID value to find an object.

Refer to <LINK href=/AboutConcurrency.html>About Concurrency</LINK> for more details 
on the optional <var>concurrency</var> argument.</p>
     @param db represented as Database
     @param id represented as java.lang.String
     @param concurrency represented as java.lang.Integer
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%DeleteId"> Method %DeleteId</A>
    */
    public static void sys_DeleteId (Database db, java.lang.String id, java.lang.Integer concurrency) throws CacheException {
        Dataholder[] args = new Dataholder[2];
        args[0] = new Dataholder(id);
        args[1] = new Dataholder(concurrency);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%DeleteId",args,Database.RET_PRIM);
        db.parseStatus(res);
        return;
    }
    /**
     <p>Runs method sys_Extends in Cache.</p>
     <p>Description: Returns true (1) if this class is inherited either via primary or secondary inheritance from 'isclass'.</p>
     @param db represented as Database
     @param isclass represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%Extends"> Method %Extends</A>
    */
    public static java.lang.Integer sys_Extends (Database db, java.lang.String isclass) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(isclass);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%Extends",args,Database.RET_PRIM);
        return res.getInteger();
    }
    /**
     <p>Runs method sys_GetParameter in Cache.</p>
     <p>Description: This method returns the value of a class parameter at runtime</p>
     @param db represented as Database
     default argument paramname set to ""
     @throws CacheException if any error occured while running the method.
     @see #sys_GetParameter(Database,java.lang.String)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%GetParameter"> Method %GetParameter</A>
    */
    public static java.lang.String sys_GetParameter (Database db) throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%GetParameter",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method sys_GetParameter in Cache.</p>
     <p>Description: This method returns the value of a class parameter at runtime</p>
     @param db represented as Database
     @param paramname represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%GetParameter"> Method %GetParameter</A>
    */
    public static java.lang.String sys_GetParameter (Database db, java.lang.String paramname) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(paramname);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%GetParameter",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method sys_IsA in Cache.</p>
     <p>Description: Returns true (1) if instances of this class are also instances of the isclass parameter.
That is 'isclass' is a primary superclass of this object.</p>
     @param db represented as Database
     @param isclass represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%IsA"> Method %IsA</A>
    */
    public static java.lang.Integer sys_IsA (Database db, java.lang.String isclass) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(isclass);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%IsA",args,Database.RET_PRIM);
        return res.getInteger();
    }
    /**
     <p>Runs method sys_OnBeforeAddToSync in Cache.</p>
     <p>Description: Should be overridden in classes using filtering for synchronization</p>
     @param db represented as Database
     @param guid represented as java.lang.String
     @param filterObject represented as com.intersys.classes.ObjectHandle
     @param filingType represented as java.lang.Integer
     @param filter represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%OnBeforeAddToSync"> Method %OnBeforeAddToSync</A>
    */
    public static java.lang.Boolean sys_OnBeforeAddToSync (Database db, java.lang.String guid, com.intersys.classes.ObjectHandle filterObject, java.lang.Integer filingType, java.lang.String filter) throws CacheException {
        Dataholder[] args = new Dataholder[4];
        args[0] = new Dataholder(guid);
        args[1] = new Dataholder(filterObject);
        args[2] = new Dataholder(filingType);
        args[3] = new Dataholder(filter);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%OnBeforeAddToSync",args,Database.RET_PRIM);
        return res.getBoolean();
    }
    /**
     <p>Runs method sys_PackageName in Cache.</p>
     <p>Description: Returns the object's package name.</p>
     @param db represented as Database
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#%PackageName"> Method %PackageName</A>
    */
    public static java.lang.String sys_PackageName (Database db) throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%PackageName",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method LogicalToOdbc in Cache.</p>
     @param db represented as Database
     default argument val set to ""
     @throws CacheException if any error occured while running the method.
     @see #LogicalToOdbc(Database,java.lang.String)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#LogicalToOdbc"> Method LogicalToOdbc</A>
    */
    public static java.lang.String LogicalToOdbc (Database db) throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"LogicalToOdbc",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method LogicalToOdbc in Cache.</p>
     @param db represented as Database
     @param val represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#LogicalToOdbc"> Method LogicalToOdbc</A>
    */
    public static java.lang.String LogicalToOdbc (Database db, java.lang.String val) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(val);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"LogicalToOdbc",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method MyAddressGetObjectId in Cache.</p>
     @param force represented as java.lang.Integer
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#MyAddressGetObjectId"> Method MyAddressGetObjectId</A>
    */
    public java.lang.String MyAddressGetObjectId (java.lang.Integer force) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(force);
        Dataholder res=mInternal.runInstanceMethod("MyAddressGetObjectId",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method MyAddressSetObject in Cache.</p>
     @param newvalue represented as com.intersys.objects.Oid
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#MyAddressSetObject"> Method MyAddressSetObject</A>
    */
    public void MyAddressSetObject (com.intersys.objects.Oid newvalue) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(newvalue);
        Dataholder res=mInternal.runInstanceMethod("MyAddressSetObject",args,Database.RET_PRIM);
        getDatabase().parseStatus(res);
        return;
    }
    /**
     <p>Runs method MyAddressSetObjectId in Cache.</p>
     @param newid represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#MyAddressSetObjectId"> Method MyAddressSetObjectId</A>
    */
    public void MyAddressSetObjectId (java.lang.String newid) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(newid);
        Dataholder res=mInternal.runInstanceMethod("MyAddressSetObjectId",args,Database.RET_PRIM);
        getDatabase().parseStatus(res);
        return;
    }
    /**
     <p>Runs method OdbcToLogical in Cache.</p>
     @param db represented as Database
     default argument val set to ""
     @throws CacheException if any error occured while running the method.
     @see #OdbcToLogical(Database,java.lang.String)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#OdbcToLogical"> Method OdbcToLogical</A>
    */
    public static java.lang.String OdbcToLogical (Database db) throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OdbcToLogical",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method OdbcToLogical in Cache.</p>
     @param db represented as Database
     @param val represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#OdbcToLogical"> Method OdbcToLogical</A>
    */
    public static java.lang.String OdbcToLogical (Database db, java.lang.String val) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(val);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"OdbcToLogical",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method SetDefault in Cache.</p>
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#SetDefault"> Method SetDefault</A>
    */
    public void SetDefault () throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=mInternal.runInstanceMethod("SetDefault",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method ToString in Cache.</p>
     @param db represented as Database
     @param customer represented as SerialObject
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#ToString"> Method ToString</A>
    */
    public static java.lang.String ToString (Database db, SerialObject customer) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(customer);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ToString",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method ToStringWrapper in Cache.</p>
     @param db represented as Database
     @param Arg1 represented as byte[]
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#ToStringWrapper"> Method ToStringWrapper</A>
    */
    public static java.lang.String ToStringWrapper (Database db, byte[] Arg1) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(Arg1);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"ToStringWrapper",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method writeAddr in Cache.</p>
     @param db represented as Database
     @param str represented as com.intersys.objects.StringHolder
     @param addr represented as Sample.Address
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#writeAddr"> Method writeAddr</A>
    */
    public static void writeAddr (Database db, com.intersys.objects.StringHolder str, Sample.Address addr) throws CacheException {
        Dataholder[] args = new Dataholder[2];
        int[] _refs = new int[1];
        args[0] = Dataholder.create (str.value);
        _refs[0] = 1;
        args[1] = new Dataholder(addr);
        Dataholder[] res=db.runClassMethod(CACHE_CLASS_NAME,"writeAddr",_refs,args,Database.RET_NONE);
        str.set(res[1].getString());
        return;
    }
    /**
     <p>Returns a CallableStatement for stored procedure Customer_ToString.</p>
     @param db represented as Database
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#ToString"> Method ToString</A>
    */
    public static java.sql.CallableStatement prepare_Customer_ToString(Database db) throws CacheException {
        java.sql.CallableStatement statement = null;
        try {
            statement = db.prepareCall("{? =  call Sample.Customer_ToString(?) }");
            statement.registerOutParameter(1, java.sql.Types.VARCHAR);
        }
        catch (java.sql.SQLException x) {
            throw new CacheException(x, "Failed to prepare stored procedure Customer_ToString");
        }
        return statement;
    }
    
    /**
     <p>Returns a CallableStatement for stored procedure Customer_ToStringWrapper.</p>
     @param db represented as Database
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=Sample.Customer#ToStringWrapper"> Method ToStringWrapper</A>
    */
    public static java.sql.CallableStatement prepare_Customer_ToStringWrapper(Database db) throws CacheException {
        java.sql.CallableStatement statement = null;
        try {
            statement = db.prepareCall("{? =  call Sample.Customer_ToStringWrapper(?) }");
            statement.registerOutParameter(1, java.sql.Types.VARCHAR);
        }
        catch (java.sql.SQLException x) {
            throw new CacheException(x, "Failed to prepare stored procedure Customer_ToStringWrapper");
        }
        return statement;
    }
    
}
