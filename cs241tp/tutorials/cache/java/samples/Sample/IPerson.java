package Sample;


/**
 * Cache' Java Class Generated for class Sample.Person on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: This sample persistent class represents a person.<br>
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person</A>
**/

public interface IPerson extends java.io.Serializable{
     String CACHE_CLASS_NAME = "Sample.Person";
    /**
       Returns value of property <code>Age</code>.
       <p>Description: Person's age.<br>
This is a calculated field whose value is derived from <property>DOB</property>.</p>
       @return current value of <code>Age</code> represented as
       <code>java.lang.Integer</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Age"> Age</A>
    */
    public java.lang.Integer getAge()  throws java.lang.Exception;
    /**
       Returns value of property <code>DOB</code>.
       <p>Description: Person's Date of Birth.</p>
       @return current value of <code>DOB</code> represented as
       <code>java.sql.Date</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#DOB"> DOB</A>
    */
    public java.sql.Date getDOB()  throws java.lang.Exception;
    /**
       Sets new value for <code>DOB</code>.
       <p>Description: Person's Date of Birth.</p>
       @param value new value to be set represented as
       <code>java.sql.Date</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#DOB"> DOB</A>
    */
    public void setDOB(java.sql.Date value)  throws java.lang.Exception;
    /**
       Returns value of property <code>FavoriteColors</code>.
       <p>Description: A collection of strings representing the person's favorite colors.</p>
       @return current value of <code>FavoriteColors</code> represented as
       <code>java.util.List</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#FavoriteColors"> FavoriteColors</A>
    */
    public java.util.List getFavoriteColors()  throws java.lang.Exception;
    /**
       Returns value of property <code>Home</code>.
       <p>Description: Person's home address. This uses an embedded object.</p>
       @return current value of <code>Home</code> represented as
       <code>Sample.IAddress</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Home"> Home</A>
    */
    public Sample.IAddress getHome()  throws java.lang.Exception;
    /**
       Sets new value for <code>Home</code>.
       <p>Description: Person's home address. This uses an embedded object.</p>
       @param value new value to be set represented as
       <code>Sample.IAddress</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Home"> Home</A>
    */
    public void setHome(Sample.IAddress value)  throws java.lang.Exception;
    /**
       Returns value of property <code>Name</code>.
       <p>Description: Person's name.</p>
       @return current value of <code>Name</code> represented as
       <code>java.lang.String</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Name"> Name</A>
    */
    public java.lang.String getName()  throws java.lang.Exception;
    /**
       Sets new value for <code>Name</code>.
       <p>Description: Person's name.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Name"> Name</A>
    */
    public void setName(java.lang.String value)  throws java.lang.Exception;
    /**
       Returns value of property <code>Office</code>.
       <p>Description: Person's office address. This uses an embedded object.</p>
       @return current value of <code>Office</code> represented as
       <code>Sample.IAddress</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Office"> Office</A>
    */
    public Sample.IAddress getOffice()  throws java.lang.Exception;
    /**
       Sets new value for <code>Office</code>.
       <p>Description: Person's office address. This uses an embedded object.</p>
       @param value new value to be set represented as
       <code>Sample.IAddress</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Office"> Office</A>
    */
    public void setOffice(Sample.IAddress value)  throws java.lang.Exception;
    /**
       Returns value of property <code>SSN</code>.
       <p>Description: Person's Social Security number. This is validated using pattern match.</p>
       @return current value of <code>SSN</code> represented as
       <code>java.lang.String</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#SSN"> SSN</A>
    */
    public java.lang.String getSSN()  throws java.lang.Exception;
    /**
       Sets new value for <code>SSN</code>.
       <p>Description: Person's Social Security number. This is validated using pattern match.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#SSN"> SSN</A>
    */
    public void setSSN(java.lang.String value)  throws java.lang.Exception;
    /**
       Returns value of property <code>Spouse</code>.
       <p>Description: Person's spouse. This is a reference to another persistent object.</p>
       @return current value of <code>Spouse</code> represented as
       <code>Sample.IPerson</code>

       @throws com.intersys.objects.CacheException if any error occurred during value retrieval.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Spouse"> Spouse</A>
    */
    public Sample.IPerson getSpouse()  throws java.lang.Exception;
    /**
       Sets new value for <code>Spouse</code>.
       <p>Description: Person's spouse. This is a reference to another persistent object.</p>
       @param value new value to be set represented as
       <code>Sample.IPerson</code>.
       @throws com.intersys.objects.CacheException if any error occurred during value setting.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Spouse"> Spouse</A>
    */
    public void setSpouse(Sample.IPerson value)  throws java.lang.Exception;
    /**
     <p>Runs method Addition in Cache.</p>
     <p>Description: A simple, sample method: add two numbers (<var>x</var> and <var>y</var>) 
and return the result.</p>
     default argument x set to 1
     default argument y set to 1
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #Addition(java.lang.Integer,java.lang.Integer)
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Addition"> Method Addition</A>
    */
    public java.lang.Integer Addition () throws java.lang.Exception;
    /**
     <p>Runs method Addition in Cache.</p>
     <p>Description: A simple, sample method: add two numbers (<var>x</var> and <var>y</var>) 
and return the result.</p>
     @param x represented as java.lang.Integer
     default argument y set to 1
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see #Addition(java.lang.Integer,java.lang.Integer)
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Addition"> Method Addition</A>
    */
    public java.lang.Integer Addition (java.lang.Integer x) throws java.lang.Exception;
    /**
     <p>Runs method Addition in Cache.</p>
     <p>Description: A simple, sample method: add two numbers (<var>x</var> and <var>y</var>) 
and return the result.</p>
     @param x represented as java.lang.Integer
     @param y represented as java.lang.Integer
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#Addition"> Method Addition</A>
    */
    public java.lang.Integer Addition (java.lang.Integer x, java.lang.Integer y) throws java.lang.Exception;
    /**
     <p>Runs method NinetyNine in Cache.</p>
     <p>Description: A simple, sample expression method: returns the value 99.</p>
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#NinetyNine"> Method NinetyNine</A>
    */
    public java.lang.Integer NinetyNine () throws java.lang.Exception;
    /**
     <p>Runs method PrintPerson in Cache.</p>
     <p>Description: Prints the property <property>Name</property> to the console.</p>
     @throws com.intersys.objects.CacheException if any error occured while running the method.
     @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Person#PrintPerson"> Method PrintPerson</A>
    */
    public void PrintPerson () throws java.lang.Exception;
}
