package Sample;


/**
 * Cache' Java Class Generated for class Sample.Company on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: This sample persistent class represents a company.<br>
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company</A>
**/

public class PCompany implements Sample.ICompany {
    private static final long serialVersionUID = 6083;
    private static String CACHE_CLASS_NAME = "Sample.Company";
    private java.util.List Employees;
    private java.lang.String Mission;
    private java.lang.String Name;
    private java.lang.Integer Revenue;
    private java.lang.String TaxID;

    // code from IncludeCode in JavaBlock

    private java.io.Serializable rowId = null;

    public java.io.Serializable getId (){
        return rowId;
    }

    public PCompany() {
        Employees = new java.util.ArrayList();
    }
    /**
       Returns value of property <code>Employees</code>.
       <p>Description: The <class>Employee</class> objects associated with this <class>Company</class>.</p>
       @return current value of <code>Employees</code> represented as
       <code>java.util.List</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Employees"> Employees</A>
    */
    public java.util.List getEmployees()  {
        return Employees;
    }
    /**
       Returns value of property <code>Mission</code>.
       <p>Description: The company's mission statement.</p>
       @return current value of <code>Mission</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Mission"> Mission</A>
    */
    public java.lang.String getMission()  {
        return Mission;
    }
    /**
       Sets new value for <code>Mission</code>.
       <p>Description: The company's mission statement.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Mission"> Mission</A>
    */
    public void setMission(java.lang.String value)  {
        Mission = value;
    }
    /**
       Returns value of property <code>Name</code>.
       <p>Description: The company's name.</p>
       @return current value of <code>Name</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Name"> Name</A>
    */
    public java.lang.String getName()  {
        return Name;
    }
    /**
       Sets new value for <code>Name</code>.
       <p>Description: The company's name.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Name"> Name</A>
    */
    public void setName(java.lang.String value)  {
        Name = value;
    }
    /**
       Returns value of property <code>Revenue</code>.
       <p>Description: The last reported revenue for the company.</p>
       @return current value of <code>Revenue</code> represented as
       <code>java.lang.Integer</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Revenue"> Revenue</A>
    */
    public java.lang.Integer getRevenue()  {
        return Revenue;
    }
    /**
       Sets new value for <code>Revenue</code>.
       <p>Description: The last reported revenue for the company.</p>
       @param value new value to be set represented as
       <code>java.lang.Integer</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#Revenue"> Revenue</A>
    */
    public void setRevenue(java.lang.Integer value)  {
        Revenue = value;
    }
    /**
       Returns value of property <code>TaxID</code>.
       <p>Description: The unique Tax ID number for the company.</p>
       @return current value of <code>TaxID</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#TaxID"> TaxID</A>
    */
    public java.lang.String getTaxID()  {
        return TaxID;
    }
    /**
       Sets new value for <code>TaxID</code>.
       <p>Description: The unique Tax ID number for the company.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Company#TaxID"> TaxID</A>
    */
    public void setTaxID(java.lang.String value)  {
        TaxID = value;
    }
    public void PrintPayroll () {
        throw new IllegalStateException ("Methods can not be called on POJO projections");
    }
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
    }
}
