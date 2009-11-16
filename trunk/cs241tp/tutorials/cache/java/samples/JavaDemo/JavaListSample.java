package JavaDemo;


/**
 * Cache' Java Class Generated for class JavaDemo.JavaListSample on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: 
Caché classes for Java List Demo 
Version: $Revision: 1.1 $
Author:  Gerd Nachtsheim
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=JavaDemo.JavaListSample</A>
**/

public class JavaListSample  implements java.io.Serializable {
    private static final long serialVersionUID = 5170;
    private static String CACHE_CLASS_NAME = "JavaDemo.JavaListSample";
    /**
       Returns class name of the class JavaDemo.JavaListSample as it is in
      Cache Database. Note, that this is a static method, so no
      object specific information can be returned. Use
      <code>getCacheClass().getName()</code> to get the class name
      for specific object.
       @return Cache class name as a <code>String</code>
      @see #getCacheClass()
      @see com.intersys.objects.reflect.CacheClass#getName()
     */
    public static String getCacheClassName( ) {
        return CACHE_CLASS_NAME;
    }

    /**
     <p>Runs method GetListOfNames in Cache.</p>
     <p>Description: Get a list of names<br> 
in: $list of IDs<br>
out: $list of names<br></p>
     @param db represented as com.intersys.objects.Database
     @param pList represented as com.intersys.objects.SList
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=JavaDemo.JavaListSample#GetListOfNames"> Method GetListOfNames</A>
    */
    public static com.intersys.objects.SList GetListOfNames (com.intersys.objects.Database db, com.intersys.objects.SList pList) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        args[0] = new com.intersys.cache.Dataholder(pList);
        com.intersys.cache.Dataholder res=db.runClassMethod(CACHE_CLASS_NAME,"GetListOfNames",args,com.intersys.objects.Database.RET_PRIM);
        return res.getSList();
    }
    /**
     <p>Runs method GetListOfNamesByRef in Cache.</p>
     <p>Description: Get a list of names by reference<br> 
in: $list of IDs by ref<br>
out: # of names<br>
     list of names in pList as $list</p>
     @param db represented as com.intersys.objects.Database
     @param pList represented as com.intersys.objects.SysListHolder
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=JavaDemo.JavaListSample#GetListOfNamesByRef"> Method GetListOfNamesByRef</A>
    */
    public static java.lang.Integer GetListOfNamesByRef (com.intersys.objects.Database db, com.intersys.objects.SysListHolder pList) throws com.intersys.objects.CacheException {
        com.intersys.cache.Dataholder[] args = new com.intersys.cache.Dataholder[1];
        int[] _refs = new int[1];
        args[0] = com.intersys.cache.Dataholder.create (pList.value);
        _refs[0] = 1;
        com.intersys.cache.Dataholder[] res=db.runClassMethod(CACHE_CLASS_NAME,"GetListOfNamesByRef",_refs,args,com.intersys.objects.Database.RET_PRIM);
        pList.set(res[1].getSList());
        return res[0].getInteger();
    }
    /**
     <p>Returns a CallableStatement for stored procedure GetListOfNames.</p>
         <p>Description: Get a list of names<br> 
in: $list of IDs<br>
out: $list of names<br></p>
     @param db represented as com.intersys.objects.Database
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=JavaDemo.JavaListSample#GetListOfNames"> Method GetListOfNames</A>
    */
    public static java.sql.CallableStatement prepare_GetListOfNames(com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        java.sql.CallableStatement statement = null;
        try {
            statement = db.prepareCall("{? =  call JavaDemo.GetListOfNames(?) }");
            statement.registerOutParameter(1, java.sql.Types.BINARY);
        }
        catch (java.sql.SQLException x) {
            throw new com.intersys.objects.CacheException(x, "Failed to prepare stored procedure GetListOfNames");
        }
        return statement;
    }
    
    /**
     <p>Returns a CallableStatement for stored procedure GetListOfNamesByRef.</p>
         <p>Description: Get a list of names by reference<br> 
in: $list of IDs by ref<br>
out: # of names<br>
     list of names in pList as $list</p>
     @param db represented as com.intersys.objects.Database
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=JavaDemo.JavaListSample#GetListOfNamesByRef"> Method GetListOfNamesByRef</A>
    */
    public static java.sql.CallableStatement prepare_GetListOfNamesByRef(com.intersys.objects.Database db) throws com.intersys.objects.CacheException {
        java.sql.CallableStatement statement = null;
        try {
            statement = db.prepareCall("{? =  call JavaDemo.GetListOfNamesByRef(?) }");
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.registerOutParameter(2, java.sql.Types.BINARY);
        }
        catch (java.sql.SQLException x) {
            throw new com.intersys.objects.CacheException(x, "Failed to prepare stored procedure GetListOfNamesByRef");
        }
        return statement;
    }
    
}
