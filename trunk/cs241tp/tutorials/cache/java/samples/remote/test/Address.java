/**
 * Java Gateway sample java class
 *
 */

package remote.test;

public class Address {

    public	String			city;
    public	String			state;
    public	Street			street;
    public	String			zip;
    public	java.util.HashMap	hm;

    public String myGetCity() {
        return city;
    }

    public void mySetCity(String value) {
        city = value;
    }

    public String myGetState() {
        return state;
    }

    public void mySetState(String value) {
        state = value;
    }

    public String myGetStreet() {
        return street.number + " " + street.name;
    }

    public void mySetStreet(Street value) {
        street = value;
    }

    public String myGetZip() {
        return zip;
    }

    public void mySetZip(String value) {
        zip = value;
    }
}
