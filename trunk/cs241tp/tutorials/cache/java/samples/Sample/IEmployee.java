package Sample;


/**
 * Cache' Java Class Generated for class Sample.Employee on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: This sample persistent class represents an employee.<br>
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee</A>
**/

public interface IEmployee extends java.io.Serializable, Sample.IPerson{
     String CACHE_CLASS_NAME = "Sample.Employee";
    /**
       Returns value of property <code>Company</code>.
       <p>Description: The company this employee works for.</p>
       @return current value of <code>Company</code> represented as
       <code>Sample.ICompany</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Company"> Company</A>
    */
    public Sample.ICompany getCompany()  throws java.lang.Exception;
    /**
       Sets new value for <code>Company</code>.
       <p>Description: The company this employee works for.</p>
       @param value new value to be set represented as
       <code>Sample.ICompany</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Company"> Company</A>
    */
    public void setCompany(Sample.ICompany value)  throws java.lang.Exception;
    public java.io.Reader getNotesIn()  throws java.lang.Exception;
    public java.io.Writer getNotesOut() throws java.lang.Exception;
    /**
       Returns value of property <code>Salary</code>.
       <p>Description: The employee's current salary.</p>
       @return current value of <code>Salary</code> represented as
       <code>java.lang.Integer</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Salary"> Salary</A>
    */
    public java.lang.Integer getSalary()  throws java.lang.Exception;
    /**
       Sets new value for <code>Salary</code>.
       <p>Description: The employee's current salary.</p>
       @param value new value to be set represented as
       <code>java.lang.Integer</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Salary"> Salary</A>
    */
    public void setSalary(java.lang.Integer value)  throws java.lang.Exception;
    /**
       Returns value of property <code>Title</code>.
       <p>Description: The employee's job title.</p>
       @return current value of <code>Title</code> represented as
       <code>java.lang.String</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Title"> Title</A>
    */
    public java.lang.String getTitle()  throws java.lang.Exception;
    /**
       Sets new value for <code>Title</code>.
       <p>Description: The employee's job title.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Title"> Title</A>
    */
    public void setTitle(java.lang.String value)  throws java.lang.Exception;
    /**
     <p>Runs method PrintPerson in Cache.</p>
     <p>Description: This method overrides the method in <class>Person</class>.<br>
Prints the properties <property>Name</property> and <property>Title</property> 
to the console.</p>
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#PrintPerson"> Method PrintPerson</A>
    */
    public void PrintPerson () throws java.lang.Exception;
}
