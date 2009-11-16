package Sample;


/**
 * Cache' Java Class Generated for class Sample.Company on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: This sample persistent class represents a company.<br>
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company</A>
**/

public interface ICompany extends java.io.Serializable{
     String CACHE_CLASS_NAME = "Sample.Company";
    /**
       Returns value of property <code>Employees</code>.
       <p>Description: The <class>Employee</class> objects associated with this <class>Company</class>.</p>
       @return current value of <code>Employees</code> represented as
       <code>java.util.List</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Employees"> Employees</A>
    */
    public java.util.List getEmployees()  throws java.lang.Exception;
    /**
       Returns value of property <code>Mission</code>.
       <p>Description: The company's mission statement.</p>
       @return current value of <code>Mission</code> represented as
       <code>java.lang.String</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Mission"> Mission</A>
    */
    public java.lang.String getMission()  throws java.lang.Exception;
    /**
       Sets new value for <code>Mission</code>.
       <p>Description: The company's mission statement.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Mission"> Mission</A>
    */
    public void setMission(java.lang.String value)  throws java.lang.Exception;
    /**
       Returns value of property <code>Name</code>.
       <p>Description: The company's name.</p>
       @return current value of <code>Name</code> represented as
       <code>java.lang.String</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Name"> Name</A>
    */
    public java.lang.String getName()  throws java.lang.Exception;
    /**
       Sets new value for <code>Name</code>.
       <p>Description: The company's name.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Name"> Name</A>
    */
    public void setName(java.lang.String value)  throws java.lang.Exception;
    /**
       Returns value of property <code>Revenue</code>.
       <p>Description: The last reported revenue for the company.</p>
       @return current value of <code>Revenue</code> represented as
       <code>java.lang.Integer</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Revenue"> Revenue</A>
    */
    public java.lang.Integer getRevenue()  throws java.lang.Exception;
    /**
       Sets new value for <code>Revenue</code>.
       <p>Description: The last reported revenue for the company.</p>
       @param value new value to be set represented as
       <code>java.lang.Integer</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Revenue"> Revenue</A>
    */
    public void setRevenue(java.lang.Integer value)  throws java.lang.Exception;
    /**
       Returns value of property <code>TaxID</code>.
       <p>Description: The unique Tax ID number for the company.</p>
       @return current value of <code>TaxID</code> represented as
       <code>java.lang.String</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#TaxID"> TaxID</A>
    */
    public java.lang.String getTaxID()  throws java.lang.Exception;
    /**
       Sets new value for <code>TaxID</code>.
       <p>Description: The unique Tax ID number for the company.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#TaxID"> TaxID</A>
    */
    public void setTaxID(java.lang.String value)  throws java.lang.Exception;
    /**
     <p>Runs method PrintPayroll in Cache.</p>
     <p>Description: This method prints out the payroll for this company by iterating over 
all the <class>Employee</class> objects related to it and printing 
their names and salaries.<br>
You can try this out from the <i>Cach&eacute;</i> command line by opening 
an instance of <class>Company</class> object and invoking this method:
<example>
Set company = ##class(Sample.Company).%OpenId(1)

Do company.PrintPayroll()
</example></p>
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#PrintPayroll"> Method PrintPayroll</A>
    */
    public void PrintPayroll () throws java.lang.Exception;
}
