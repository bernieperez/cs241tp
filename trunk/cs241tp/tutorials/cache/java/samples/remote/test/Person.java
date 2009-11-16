/**
 * Java Gateway sample java class
 *
 */

package remote.test;

import java.util.ArrayList;
import com.intersys.objects.Database;

public class Person {

    public static final int     INTCONSTANT = 76;

    public static final short   SHORTCONSTANT = 5;

    public static final long    LONGCONSTANT = 4441;

    public static final boolean BOOLEANCONSTANT = false;

    public static final double  DOUBLECONSTANT = 7.451;

    public static final String  STRINGCONSTANT = "String constant";

    public Database	cacheDatabase = null;

    public static int   staticProperty;

    public 	int 		age;

    public 	Double 		myDoubleWrapper;

    public 	String 		name;

    public 	String 		SSN;

    public 	ArrayList 	favoriteSports;

    public 	Address 	home;

    public	long		weigth;

    private	Class		clazz;

    private     String[]        stringArray;

    private     String[][]      twoStringArray;

    private     Address[]       addressArray;

    public 	byte[]		ByteArray;

    public Person() {
    }

    public Person(String ssn) {
	super();
	SSN = ssn;
	favoriteSports = new ArrayList();
    }

    public static String myStaticMethod() {
	return "Success";
    }

    public long getweigth() {
        return weigth;
    }

    public void setweigth(long value) {
        weigth = value;
    }

    public int getage() {
        return age;
    }

    public void mySetAge(int a) {
        age = a;
    }

    public ArrayList myGetFavoriteSports() {
        return favoriteSports;
    }

    public void mySetFavoriteSports(ArrayList value) {
        favoriteSports = value;
    }

    public Double myGetDoubleWrapper() {
        return myDoubleWrapper;
    }

    public void mySetDoubleWrapper(Double d) {
        myDoubleWrapper = d;
    }

    public String myGetName() throws Exception {
        return name;
    }

    public void mySetName(String first, String last) {
        name = last + "," + first;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String value) {
	SSN = value;
    }

    public Address gethome() {
        return home;
    }

    public void sethome(Address value) {
	home = value;
    }

    public String getStreetAndCity() {
	return "Street: " + home.street.number + " " + home.street.name + 
	   "\r\nCity: " + home.city;
    }

    public void setClazz(Class cls) {
	clazz = cls;
    }

    public Class getClazz() {
	return clazz;
    }

    public void changeAddress(Address old, Address newAddress) throws Exception {
	if (!old.city.equals(home.city)) {
	    throw new Exception("Invalid old city " + old.city + " " + home.city);
	} else if (!old.state.equals(home.state)) {
	    throw new Exception("Invalid old address specified " + old.state + " " + home.state);
	} else if (!old.street.equals(home.street)) {
	    throw new Exception("Invalid old address specified " + old.street.name + " " + home.street.name);
	} else if (!old.zip.equals(home.zip)) {
	    throw new Exception("Invalid old address specified " + old.zip + " " + home.zip);
	}
	home = newAddress;
    }

    public String[] getAddressAsCollection() {
	String[] address = new String[4];
	address[0] = home.city;
	address[1] = home.state;
	address[2] = home.zip;
	address[3] = home.street.number + " " + home.street.name;
	return address;
    }

    public void setStringArray(String[] sa) {
	stringArray = sa;
    }

    public String[] getStringArray() {
	return stringArray;
    }

    public Address[] getAddressArray() {
	return addressArray;
    }

    public void setAddressArray(Address[] aa) {
	addressArray = aa;
    }

    public void setTwoStringArray(String[][] tsa) {
	twoStringArray = tsa;
    }

    public String[][] getTwoStringArray() {
	return twoStringArray;
    }

    public int read(byte[] b, int len) {
	byte[] temp = (new String("This byte stream has been filled in by Java")).getBytes();
	System.arraycopy(temp,0,b,0,temp.length);
	return temp.length;
    }

/*
    public String bindingCallback() throws Exception {
        Sample.Person   person = null;
        Sample.Address  addr = null;
        Oid             oid = null;
	String		out = null;

        try {
           Sample.Person.checkAllFieldsValid(cacheDatabase);

            // Open an instance of the Sample.Person object
            if (oid == null) {
                oid = new Oid(1);
	    }

            if (!(Sample.Person.exists(cacheDatabase,oid))) {
                System.out.println ("No Person with id " + oid.getId());
                return out;
            }

            person = (Sample.Person) Sample.Person._open(cacheDatabase,oid);

            // Fetch some properties 
            out = "ID: " + person.getId() + "\r\n";
            out += "Name: " + person.getName() + "\r\n";
            out += "SSN:  " + person.getSSN() + "\r\n";
            out += "DOB:  " + person.getDOB() + "\r\n";

            // Embedded object
            addr = person.getHome();
            out += "Street: " + addr.getStreet() + "\r\n";
            out += "City:   " + addr.getCity() + "\r\n";
            out += "State:  " + addr.getState() + "\r\n";
            out += "Zip:    " + addr.getZip() + "\r\n";

            Sample.Person spouse = person.getSpouse();
            if (spouse != null) {
                out += "Spouse Name: " + spouse.getName() + "\r\n";
            }

	    // System.out.println("Person : " + out);
            // close person object
            cacheDatabase.closeObject(person.getOref());
            person = null;

	    return out;
        } catch (Exception ex) {
            ex.printStackTrace();
	    throw ex;
	}
    }
*/
}
