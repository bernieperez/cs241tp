package remote.test.reentrancy;
import com.intersys.cache.*;
import com.intersys.objects.*;
import com.intersys.objects.reflect.*;
import com.intersys.classes.*;
import java.sql.Date;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
//import com.intersys.classes.Net.Java.JavaGateway;


/**
 * Cache' Java Class Generated for class javagateway.reentrancy.ReentrancyTest on version Cache for Windows (Intel/P4) 5.2 (Build 129U) Wed Jun 29 2005 16:45:54 EDT.<br>
 *
 * @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest</A>
**/

public class ReentrancyTest extends RegisteredObject implements java.io.Serializable {
    private static String CACHE_CLASS_NAME = "%Net.Remote.Java.ReentrancyTest";
    /**
           <p>NB: DO NOT USE IN APPLICATION(!!!).
           <br>Use <code>javagateway.reentrancy.ReentrancyTest.open</code> instead!</br></p>
           <p>
           Used to construct a Java object, corresponding to existing object
           in Cache database.
           </p>
           @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
           @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
    */
    public ReentrancyTest (CacheObject ref) throws CacheException {
        super (ref);
    }
    public ReentrancyTest (Database db, String initstr) throws CacheException {
        super (((SysDatabase)db).newCacheObject (CACHE_CLASS_NAME,initstr));
    }
    /**
       Creates a new instance of object "javagateway.reentrancy.ReentrancyTest" in Cache
       database and corresponding object of class
       <code>javagateway.reentrancy.ReentrancyTest</code>.

       @param db <code>Database</code> object used for connection with
       Cache database.

       @throws CacheException in case of error.

              @see #_open(com.intersys.objects.Database, com.intersys.objects.Oid)
              @see #open(com.intersys.objects.Database, com.intersys.objects.Oid)
     */
    public ReentrancyTest (Database db) throws CacheException {
        super (((SysDatabase)db).newCacheObject (CACHE_CLASS_NAME));
    }
    /**
       Returns class name of the class javagateway.reentrancy.ReentrancyTest as it is in
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
        checkAllFieldsValid(db, CACHE_CLASS_NAME, ReentrancyTest.class);
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
        checkAllMethods(db, CACHE_CLASS_NAME, ReentrancyTest.class);
    }
    /**
     <p>Runs method sys_ClassName in Cache.</p>
     <p>Description: Returns the object's class name. The <var>fullname</var> determines how the
class name is represented. If it is 1 then it returns the full class name
including any package qualifier. If it is 0 (the default) then it returns the
name of the class without the package, this is mainly for backward compatibility
with the pre-package behaviour of %ClassName.</p>
     @param db represented as Database
     @param fullname represented as boolean
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#%ClassName"> Method %ClassName</A>
    */
    public static java.lang.String sys_ClassName (Database db, boolean fullname) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(fullname);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%ClassName",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method sys_Extends in Cache.</p>
     <p>Description: Returns true (1) if this class is inherited either via primary or secondary inheritance from 'isclass'.</p>
     @param db represented as Database
     @param isclass represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#%Extends"> Method %Extends</A>
    */
    public static int sys_Extends (Database db, java.lang.String isclass) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(isclass);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%Extends",args,Database.RET_PRIM);
        return res.getIntValue();
    }
    /**
     <p>Runs method sys_GetParameter in Cache.</p>
     <p>Description: This method returns the value of a class parameter at runtime</p>
     @param db represented as Database
     default argument paramname set to ""
     @throws CacheException if any error occured while running the method.
     @see #sys_GetParameter(Database,java.lang.String)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#%GetParameter"> Method %GetParameter</A>
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
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#%GetParameter"> Method %GetParameter</A>
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
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#%IsA"> Method %IsA</A>
    */
    public static int sys_IsA (Database db, java.lang.String isclass) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(isclass);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%IsA",args,Database.RET_PRIM);
        return res.getIntValue();
    }
    /**
     <p>Runs method sys_PackageName in Cache.</p>
     <p>Description: Returns the object's package name.</p>
     @param db represented as Database
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#%PackageName"> Method %PackageName</A>
    */
    public static java.lang.String sys_PackageName (Database db) throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"%PackageName",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method CallEcho in Cache.</p>
     @param port represented as java.lang.String
     default argument msg set to ""
     @throws CacheException if any error occured while running the method.
     @see #CallEcho(java.lang.String,java.lang.String)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#CallEcho"> Method CallEcho</A>
    */
    public java.lang.String CallEcho (java.lang.String port) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(port);
        Dataholder res=mInternal.runInstanceMethod("CallEcho",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method CallEcho in Cache.</p>
     @param port represented as java.lang.String
     @param msg represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#CallEcho"> Method CallEcho</A>
    */
    public java.lang.String CallEcho (java.lang.String port, java.lang.String msg) throws CacheException {
        Dataholder[] args = new Dataholder[2];
        args[0] = new Dataholder(port);
        args[1] = new Dataholder(msg);
        Dataholder res=mInternal.runInstanceMethod("CallEcho",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method CallEchoAll in Cache.</p>
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#CallEchoAll"> Method CallEchoAll</A>
    */
    public void CallEchoAll () throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=mInternal.runInstanceMethod("CallEchoAll",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method CallNestedEcho in Cache.</p>
     @param port represented as java.lang.String
     default argument depth set to 1
     default argument msg set to ""
     @throws CacheException if any error occured while running the method.
     @see #CallNestedEcho(java.lang.String,int,java.lang.String)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#CallNestedEcho"> Method CallNestedEcho</A>
    */
    public java.lang.String CallNestedEcho (java.lang.String port) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(port);
        Dataholder res=mInternal.runInstanceMethod("CallNestedEcho",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method CallNestedEcho in Cache.</p>
     @param port represented as java.lang.String
     @param depth represented as int
     default argument msg set to ""
     @throws CacheException if any error occured while running the method.
     @see #CallNestedEcho(java.lang.String,int,java.lang.String)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#CallNestedEcho"> Method CallNestedEcho</A>
    */
    public java.lang.String CallNestedEcho (java.lang.String port, int depth) throws CacheException {
        Dataholder[] args = new Dataholder[2];
        args[0] = new Dataholder(port);
        args[1] = new Dataholder(depth);
        Dataholder res=mInternal.runInstanceMethod("CallNestedEcho",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method CallNestedEcho in Cache.</p>
     @param port represented as java.lang.String
     @param depth represented as int
     @param msg represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#CallNestedEcho"> Method CallNestedEcho</A>
    */
    public java.lang.String CallNestedEcho (java.lang.String port, int depth, java.lang.String msg) throws CacheException {
        Dataholder[] args = new Dataholder[3];
        args[0] = new Dataholder(port);
        args[1] = new Dataholder(depth);
        args[2] = new Dataholder(msg);
        Dataholder res=mInternal.runInstanceMethod("CallNestedEcho",args,Database.RET_PRIM);
        return res.getString();
    }
    /**
     <p>Runs method CallNestedEchoAll in Cache.</p>
     @param depth represented as int
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#CallNestedEchoAll"> Method CallNestedEchoAll</A>
    */
    public void CallNestedEchoAll (int depth) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(depth);
        Dataholder res=mInternal.runInstanceMethod("CallNestedEchoAll",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method Connect in Cache.</p>
     @param port represented as int
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#Connect"> Method Connect</A>
    */
//    public com.intersys.classes.Net.Java.JavaGateway Connect (int port) throws CacheException {
//        Dataholder[] args = new Dataholder[1];
//        args[0] = new Dataholder(port);
//        Dataholder res=mInternal.runInstanceMethod("Connect",args,Database.RET_OBJECT);
//        CacheObject cobj = res.getCacheObject();
//        if (cobj == null)
//            return null;
//        return (com.intersys.classes.Net.Java.JavaGateway)(cobj.newJavaInstance());
//    }
    /**
     <p>Runs method ConnectMultiple in Cache.</p>
     @param n represented as int
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#ConnectMultiple"> Method ConnectMultiple</A>
    */
    public void ConnectMultiple (int n) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(n);
        Dataholder res=mInternal.runInstanceMethod("ConnectMultiple",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method DisconnectAll in Cache.</p>
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#DisconnectAll"> Method DisconnectAll</A>
    */
    public void DisconnectAll () throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=mInternal.runInstanceMethod("DisconnectAll",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method EchoTest in Cache.</p>
     @param db represented as Database
     default argument numConnections set to 3
     default argument depth set to 7
     @throws CacheException if any error occured while running the method.
     @see #EchoTest(Database,int,int)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#EchoTest"> Method EchoTest</A>
    */
    public static void EchoTest (Database db) throws CacheException {
        Dataholder[] args = new Dataholder[0];
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"EchoTest",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method EchoTest in Cache.</p>
     @param db represented as Database
     @param numConnections represented as int
     default argument depth set to 7
     @throws CacheException if any error occured while running the method.
     @see #EchoTest(Database,int,int)
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#EchoTest"> Method EchoTest</A>
    */
    public static void EchoTest (Database db, int numConnections) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(numConnections);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"EchoTest",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method EchoTest in Cache.</p>
     @param db represented as Database
     @param numConnections represented as int
     @param depth represented as int
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#EchoTest"> Method EchoTest</A>
    */
    public static void EchoTest (Database db, int numConnections, int depth) throws CacheException {
        Dataholder[] args = new Dataholder[2];
        args[0] = new Dataholder(numConnections);
        args[1] = new Dataholder(depth);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"EchoTest",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method GetMe in Cache.</p>
     @param db represented as Database
     @param oref represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#GetMe"> Method GetMe</A>
    */
    public static remote.test.reentrancy.ReentrancyTest GetMe (Database db, java.lang.String oref) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(oref);
        Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetMe",args,Database.RET_OBJECT);
        CacheObject cobj = res.getCacheObject();
        if (cobj == null)
            return null;
        return (remote.test.reentrancy.ReentrancyTest)(cobj.newJavaInstance());
    }
    /**
     <p>Runs method ImportToAll in Cache.</p>
     @param _class represented as java.lang.String
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#ImportToAll"> Method ImportToAll</A>
    */
    public void ImportToAll (java.lang.String _class) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(_class);
        Dataholder res=mInternal.runInstanceMethod("ImportToAll",args,Database.RET_NONE);
        return;
    }
    /**
     <p>Runs method SetupOutputAll in Cache.</p>
     @param depth represented as int
     @throws CacheException if any error occured while running the method.
     @see <a href = "http://mishaws:8973/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=USER&CLASSNAME=javagateway.reentrancy.ReentrancyTest#SetupOutputAll"> Method SetupOutputAll</A>
    */
    public void SetupOutputAll (int depth) throws CacheException {
        Dataholder[] args = new Dataholder[1];
        args[0] = new Dataholder(depth);
        Dataholder res=mInternal.runInstanceMethod("SetupOutputAll",args,Database.RET_NONE);
        return;
    }
}
