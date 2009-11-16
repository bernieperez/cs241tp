package Sample;


/**
 * Cache' Java Class Generated for class Sample.Employee on version Cache for Windows (x86-32) 2009.1.2 (Build 602.0U_SU) Mon Oct 26 2009 11:38:16 EDT.<br>
 * Description: This sample persistent class represents an employee.<br>
 *
 * @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee</A>
**/

public class PEmployee extends Sample.PPerson implements Sample.IEmployee {
    private static final long serialVersionUID = 6769;
    private static String CACHE_CLASS_NAME = "Sample.Employee";
    private Sample.ICompany Company;
    transient java.io.StringWriter NotesWritten;
    private String Notes;
    private java.lang.Integer Salary;
    private java.lang.String Title;

    // code from IncludeCode in JavaBlock

    public PEmployee() {
    }
    /**
       Returns value of property <code>Company</code>.
       <p>Description: The company this employee works for.</p>
       @return current value of <code>Company</code> represented as
       <code>Sample.ICompany</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Company"> Company</A>
    */
    public Sample.ICompany getCompany()  {
        return Company;
    }
    /**
       Sets new value for <code>Company</code>.
       <p>Description: The company this employee works for.</p>
       @param value new value to be set represented as
       <code>Sample.ICompany</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Company"> Company</A>
    */
    public void setCompany(Sample.ICompany value)  {
        Company = value;
    }
    public java.io.Reader getNotesIn() {
        if (NotesWritten != null) {
             Notes = NotesWritten.toString();
         }
        if (Notes == null) {
            return null;
        }
        return new java.io.StringReader(Notes);
    }

    public java.io.Writer getNotesOut() {
        if (NotesWritten == null) {
            NotesWritten = new java.io.StringWriter();
        }
        return NotesWritten;
    }
    /**
       Returns value of property <code>Salary</code>.
       <p>Description: The employee's current salary.</p>
       @return current value of <code>Salary</code> represented as
       <code>java.lang.Integer</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Salary"> Salary</A>
    */
    public java.lang.Integer getSalary()  {
        return Salary;
    }
    /**
       Sets new value for <code>Salary</code>.
       <p>Description: The employee's current salary.</p>
       @param value new value to be set represented as
       <code>java.lang.Integer</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Salary"> Salary</A>
    */
    public void setSalary(java.lang.Integer value)  {
        Salary = value;
    }
    /**
       Returns value of property <code>Title</code>.
       <p>Description: The employee's job title.</p>
       @return current value of <code>Title</code> represented as
       <code>java.lang.String</code>

       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Title"> Title</A>
    */
    public java.lang.String getTitle()  {
        return Title;
    }
    /**
       Sets new value for <code>Title</code>.
       <p>Description: The employee's job title.</p>
       @param value new value to be set represented as
       <code>java.lang.String</code>.
       @see <a href = "http://localhost:8972/csp/documatic/%25CSP.Documatic.cls?APP=1&PAGE=CLASS&LIBRARY=SAMPLES&CLASSNAME=Sample.Employee#Title"> Title</A>
    */
    public void setTitle(java.lang.String value)  {
        Title = value;
    }
    public void PrintPerson () {
        throw new IllegalStateException ("Methods can not be called on POJO projections");
    }
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
        if (NotesWritten != null) {
            Notes = NotesWritten.toString();
        }
        out.defaultWriteObject();
    }
}
