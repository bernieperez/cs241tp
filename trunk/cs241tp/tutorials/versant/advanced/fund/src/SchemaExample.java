
import com.versant.fund.*;
import java.util.Properties;

/**
 * An example to demonstrate access of attribute information about
 * a database class and various schema evolution methods.
 */
public class SchemaExample 
{

/**
 * helper method for main, to print all attributes of the named class
 */
static void printClass (String className , Session session )
{
    ClassHandle ch = session.locateClass (className);

    // attributes of attribute objects
    AttrString attr_name      = session.newAttrString ("name");
    AttrHandle attr_domain    = session.newAttrHandle ("domain");
    AttrInt    attr_repfactor = session.newAttrInt    ("repfactor");

    // attributes of class objects
    AttrString      cls_name  = session.newAttrString      ("name");
    AttrHandleArray cls_attrs = session.newAttrHandleArray ("attrs");

    Handle domain;
    String domainName;
    String attrName;
    int    repFactor;  // repeat factor

    Handle[] attrs= ch.get(cls_attrs); //get() acquires RLOCK on the class object
    ch.downgradeLockTo (Constants.IRLOCK); //so downgrade it to IRLOCK

    System.out.println("class name: " + className);
    System.out.println("  attributes: ");

    if (attrs!=null) {
        for (int i=0; i<attrs.length; i++) {
            domain = attrs[i].get(attr_domain);
            attrName = attrs[i].get(attr_name);
            if (domain==null || domain.isEmpty())
                domainName = "";
            else
                domainName = domain.get(cls_name);
            repFactor = attrs[i].get(attr_repfactor);
            // print it
            System.out.print("    name: " + attrName + "\t");
            System.out.print(" type: " + domainName + "\t");
            System.out.println(" repeat factor: " + repFactor);
            // downgrade the RLOCK acquired by get() on the attribute object
            attrs[i].downgradeLockTo(Constants.IRLOCK); 
        }//for
    }//if

    System.out.println("");
}

static void say (String whatToSay)
{
    System.out.println (whatToSay);
}

/**
 * main function.
 */
public static void main (String[] args)
{
    if (args.length < 1) {
        System.out.println ("Usage: SchemaExample <db> ");
        System.exit (-1);
    }

    // begin session by instantiating FundSession
    String db= args[0];
    Capability capability= new NewSessionCapability();
    Properties properties = new Properties();
    properties.put("database", db);
    FundSession session = new FundSession(properties, capability);

    // drop Human class if exists
    try { 
        session.locateClass("Human").dropClass();
    } 
    catch (VException vex) {}

    // define class Human
    AttrString name= session.newAttrString("name");
    AttrFloat weight= session.newAttrFloat("weight");

    AttrBuilder[] attrs= {
        session.newAttrBuilder(name).withBTreeIndex(),
        session.newAttrBuilder(weight) 
    };

    ClassHandle Human= session.withAttrBuilders(attrs).defineClass("Human");

    // create two Human instances
    Handle janos= Human.makeObject();
    janos.put( name, "Janos" );
    janos.put( weight, (float)14.0 );

    Handle edina= Human.makeObject();
    edina.put( name, "Edina" );
    edina.put( weight, (float)12.0 );

    session.commit();

    say ("Original schema: ");
    printClass ("Human", session);

    // rename an attribute

    say ("renaming attribute 'name' to 'firstname' ");
    Human.renameAttr ("name", "firstname");
    session.commit();
    printClass ("Human", session);

    // drop an attribute

    say ("dropping attribute 'weight' ");
    Human.dropAttr ("weight");
    session.commit();
    printClass ("Human", session);
	
    // add multiple attributes at specific position

    say ("inserting new attributes 'address' and 'phone' at position 1");
    AttrString address = session.newAttrString ("address");
    AttrString phone = session.newAttrString ("phone");
    AttrBuilder[] newattrs= {
        session.newAttrBuilder (address),
        session.newAttrBuilder (phone)
    };
    Human.insertAttrsAt (newattrs, 1);
    session.commit();
    printClass ("Human", session);

    session.endSession();

  } // end main

} // end class

