package Sample;


/**
 * Cache' Java Class Generated for class Sample.Address on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: This is a sample embeddable class representing an address.
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address</A>
**/

public class PAddress implements Sample.IAddress {
    private static final long serialVersionUID = 2821;
    private static String CACHE_CLASS_NAME = "Sample.Address";
    private java.lang.String City;
    private java.lang.String State;
    private java.lang.String Street;
    private java.lang.String Zip;

    // code from IncludeCode in JavaBlock

    public PAddress() {
    }
    /**
       Returns value of property <code>City</code>.
       <p>Description: The city name.</p>
       @return current value of <code>City</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#City"> City</A>
    */
    public java.lang.String getCity()  {
        return City;
    }
    /**
       Sets new value for <code>City</code>.
       <p>Description: The city name.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#City"> City</A>
    */
    public void setCity(java.lang.String value)  {
        City = value;
    }
    /**
       Returns value of property <code>State</code>.
       <p>Description: The 2-letter state abbreviation.</p>
       @return current value of <code>State</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#State"> State</A>
    */
    public java.lang.String getState()  {
        return State;
    }
    /**
       Sets new value for <code>State</code>.
       <p>Description: The 2-letter state abbreviation.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#State"> State</A>
    */
    public void setState(java.lang.String value)  {
        State = value;
    }
    /**
       Returns value of property <code>Street</code>.
       <p>Description: The street address.</p>
       @return current value of <code>Street</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#Street"> Street</A>
    */
    public java.lang.String getStreet()  {
        return Street;
    }
    /**
       Sets new value for <code>Street</code>.
       <p>Description: The street address.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#Street"> Street</A>
    */
    public void setStreet(java.lang.String value)  {
        Street = value;
    }
    /**
       Returns value of property <code>Zip</code>.
       <p>Description: The 5-digit U.S. Zone Improvement Plan (ZIP) code.</p>
       @return current value of <code>Zip</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#Zip"> Zip</A>
    */
    public java.lang.String getZip()  {
        return Zip;
    }
    /**
       Sets new value for <code>Zip</code>.
       <p>Description: The 5-digit U.S. Zone Improvement Plan (ZIP) code.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Address#Zip"> Zip</A>
    */
    public void setZip(java.lang.String value)  {
        Zip = value;
    }
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
    }
}
