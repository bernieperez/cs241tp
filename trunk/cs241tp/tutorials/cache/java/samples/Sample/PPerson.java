package Sample;


/**
 * Cache' Java Class Generated for class Sample.Person on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: This sample persistent class represents a person.<br>
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person</A>
**/

public class PPerson implements Sample.IPerson {
    private static final long serialVersionUID = 874;
    private static String CACHE_CLASS_NAME = "Sample.Person";
    private java.lang.Integer Age;
    private java.sql.Date DOB;
    private java.util.List FavoriteColors;
    private Sample.IAddress Home;
    private java.lang.String Name;
    private Sample.IAddress Office;
    private java.lang.String SSN;
    private Sample.IPerson Spouse;

    // code from IncludeCode in JavaBlock

    private java.io.Serializable rowId = null;

    public java.io.Serializable getId (){
        return rowId;
    }

    public PPerson() {
        FavoriteColors = new java.util.ArrayList();
    }
    /**
       Returns value of property <code>Age</code>.
       <p>Description: Person's age.<br>
This is a calculated field whose value is derived from <property>DOB</property>.</p>
       @return current value of <code>Age</code> represented as
       <code>java.lang.Integer</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Age"> Age</A>
    */
    public java.lang.Integer getAge()  {
        return Age;
    }
    /**
       Returns value of property <code>DOB</code>.
       <p>Description: Person's Date of Birth.</p>
       @return current value of <code>DOB</code> represented as
       <code>java.sql.Date</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#DOB"> DOB</A>
    */
    public java.sql.Date getDOB()  {
        return DOB;
    }
    /**
       Sets new value for <code>DOB</code>.
       <p>Description: Person's Date of Birth.</p>
       @param value new value to be set represented as
       <code>java.sql.Date</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#DOB"> DOB</A>
    */
    public void setDOB(java.sql.Date value)  {
        DOB = value;
    }
    /**
       Returns value of property <code>FavoriteColors</code>.
       <p>Description: A collection of strings representing the person's favorite colors.</p>
       @return current value of <code>FavoriteColors</code> represented as
       <code>java.util.List</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#FavoriteColors"> FavoriteColors</A>
    */
    public java.util.List getFavoriteColors()  {
        return FavoriteColors;
    }
    /**
       Returns value of property <code>Home</code>.
       <p>Description: Person's home address. This uses an embedded object.</p>
       @return current value of <code>Home</code> represented as
       <code>Sample.IAddress</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Home"> Home</A>
    */
    public Sample.IAddress getHome()  {
        return Home;
    }
    /**
       Sets new value for <code>Home</code>.
       <p>Description: Person's home address. This uses an embedded object.</p>
       @param value new value to be set represented as
       <code>Sample.IAddress</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Home"> Home</A>
    */
    public void setHome(Sample.IAddress value)  {
        Home = value;
    }
    /**
       Returns value of property <code>Name</code>.
       <p>Description: Person's name.</p>
       @return current value of <code>Name</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Name"> Name</A>
    */
    public java.lang.String getName()  {
        return Name;
    }
    /**
       Sets new value for <code>Name</code>.
       <p>Description: Person's name.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Name"> Name</A>
    */
    public void setName(java.lang.String value)  {
        Name = value;
    }
    /**
       Returns value of property <code>Office</code>.
       <p>Description: Person's office address. This uses an embedded object.</p>
       @return current value of <code>Office</code> represented as
       <code>Sample.IAddress</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Office"> Office</A>
    */
    public Sample.IAddress getOffice()  {
        return Office;
    }
    /**
       Sets new value for <code>Office</code>.
       <p>Description: Person's office address. This uses an embedded object.</p>
       @param value new value to be set represented as
       <code>Sample.IAddress</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Office"> Office</A>
    */
    public void setOffice(Sample.IAddress value)  {
        Office = value;
    }
    /**
       Returns value of property <code>SSN</code>.
       <p>Description: Person's Social Security number. This is validated using pattern match.</p>
       @return current value of <code>SSN</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#SSN"> SSN</A>
    */
    public java.lang.String getSSN()  {
        return SSN;
    }
    /**
       Sets new value for <code>SSN</code>.
       <p>Description: Person's Social Security number. This is validated using pattern match.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#SSN"> SSN</A>
    */
    public void setSSN(java.lang.String value)  {
        SSN = value;
    }
    /**
       Returns value of property <code>Spouse</code>.
       <p>Description: Person's spouse. This is a reference to another persistent object.</p>
       @return current value of <code>Spouse</code> represented as
       <code>Sample.IPerson</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Spouse"> Spouse</A>
    */
    public Sample.IPerson getSpouse()  {
        return Spouse;
    }
    /**
       Sets new value for <code>Spouse</code>.
       <p>Description: Person's spouse. This is a reference to another persistent object.</p>
       @param value new value to be set represented as
       <code>Sample.IPerson</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Spouse"> Spouse</A>
    */
    public void setSpouse(Sample.IPerson value)  {
        Spouse = value;
    }
    public java.lang.Integer Addition () {
        throw new IllegalStateException ("Methods can not be called on POJO projections");
    }
    public java.lang.Integer Addition (java.lang.Integer x) {
        throw new IllegalStateException ("Methods can not be called on POJO projections");
    }
    public java.lang.Integer Addition (java.lang.Integer x, java.lang.Integer y) {
        throw new IllegalStateException ("Methods can not be called on POJO projections");
    }
    public java.lang.Integer NinetyNine () {
        throw new IllegalStateException ("Methods can not be called on POJO projections");
    }
    public void PrintPerson () {
        throw new IllegalStateException ("Methods can not be called on POJO projections");
    }
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        out.defaultWriteObject();
    }
}
