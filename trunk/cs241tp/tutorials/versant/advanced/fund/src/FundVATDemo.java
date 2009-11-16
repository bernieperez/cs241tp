import com.versant.fund.*;
import com.versant.util.*;
import java.util.*;

/**
 * A very simple demo that demonstrates uage of VAT and Fundamental Binding.
 * The person instances stored in the database have chinese, german and ascii
 * characters contained in them. The query string is set up with the
 * appropriate locale before querying the database.
 */

public
class FundVATDemo 
{

  /* We need to modify the profile.be so that VAT is defined in the backend
   * profile. This function reads the profile and then modifies it as needed.
   */
  static void 
  modifyBEProfile(String database)
  {
    BEProfile be = DBUtility.getBEProfile(database);
    String versantRuntime = DBUtility.versantRuntimePath();
    String versantRoot = DBUtility.versantRootPath();
    String[] customDLLs;
    String OS = System.getProperty("os.name").toLowerCase();
    Properties prop = new Properties();
    prop.put("-f","");
    
    if(OS.indexOf("window") == 0)
    {
      String pluginPath = versantRuntime + "\\" + "bin" + "\\" ;
      customDLLs = new String[] {
                     pluginPath + "national.dll" + "$" + versantRuntime,
                     pluginPath + "nocase.dll"   + "$" + versantRuntime,
                     pluginPath + "tuple.dll"    + "$" + versantRuntime
                   };

    } else if(OS.indexOf("hp-ux") == 0) {
       String pluginPath = versantRuntime + "/" + "lib" + "/" ;
       customDLLs = new String[] {
                    pluginPath + "libnational.sl" + "$" + versantRuntime,
                    pluginPath + "libnocase.sl"   + "$" + versantRuntime,
                    pluginPath + "libtuple.sl"    + "$" + versantRuntime
                  };

    } else {
      String pluginPath = versantRuntime + "/" + "lib" + "/" ;
      customDLLs = new String[] {
                    pluginPath + "libnational.so" + "$" + versantRuntime,
                    pluginPath + "libnocase.so"   + "$" + versantRuntime,
                    pluginPath + "libtuple.so"    + "$" + versantRuntime
                  };

    }

    for(int i = 0; i < customDLLs.length; i++)
      be.addToCustom_BE_Plugins(customDLLs[i]);
      
    DBUtility.writeBEProfile(be);
    DBUtility.stopDB(database,prop);
    DBUtility.startDB(database);
  }
 
  public static 
  void main(String[] args) 
  {
    if (args.length < 1)
    {
      System.out.println("java FundVATDemo <database name>");
      System.exit(-1);
    }

    FundSession session;
    String database = args[0];
    
    modifyBEProfile(database);
    session = new FundSession(database);
    
    // use Attr objects to describe the attributes
    AttrString firstname = session.newAttrString("firstname");
    AttrString lastname  = session.newAttrString("lastname");

    // an array of AttrBuilders will be required to build the class.
    AttrBuilder[] attrs= {
      session.newAttrBuilder(firstname),
      session.newAttrBuilder(lastname) 
    };

    // define the class Person to the database. 
    ClassHandle	person = session.withAttrBuilders(attrs).defineClass("Person");
   
    System.out.println("Creating Person instances in the database....");
    // create 2 instances of Person.
    // First instance of Person  use German characters.
    Handle handle = person.makeObject();
    handle.put(firstname, "J�rgen Uwe");
    handle.put(lastname,  "Be�le-Schmidt");
   
    // now use chinese characters.
    handle = person.makeObject();
    handle.put(firstname, "老板娘 Jordan");
    handle.put(lastname,   "私家偵探社");
   
    // use English characters. 
    handle = person.makeObject();
    handle.put(firstname, "Carole");
    handle.put(lastname,  "Mortimer Smith");
    
    session.commit();
    System.out.println("Done Committing the instances...");
   
    //Query. Use German to specify the search criteria.
    String queryString1   = "/national de_DE utf8 firstname";
    String queryString2   = "/national de_DE utf8 lastname";
    String string1        = "J�rgen Uwe";
    String string2        = "Be�le-Schmidt";

    // create index on first name 
    person.createIndex(queryString1, Constants.UNIQUE_BTREE_INDEX);

    // Having created a index, we will now query based on first name using the
    // national VAT. The Braces are necessary if the string has spaces
    // embedded in it. Here string1 has spaces embedded in the string it
    // refers to.
    Predicate predicate = session.newAttrString(queryString1).eq("{" + string1 
                                                + "}");
    Handle[] h = person.select(predicate).asArray();
    if (h.length > 0)
      System.out.println("Query using national VAT and German characters" +
                         " succeeded ....");
    else
      throw new RuntimeException("Query using German characters failed");
      
    // delete the index created.
    person.deleteIndex(queryString1, Constants.UNIQUE_BTREE_INDEX);

    // query for instances of class person using the tuple VAT.
    predicate = 
            session.newAttrString("{/tuple {/national de_DE utf8 firstname } " +
                                  "{/national de_DE utf8 lastname} }").eq(
			          "{" + string1 + "} {" + string2 + "}");
    h = person.select(predicate).asArray();
    if (h.length > 0)
      System.out.println("Query using tuple VAT and German characters" +
                         " succeeded ....");
    else
      throw new RuntimeException("Tuple Query using German Characters failed");
 
    // Query for instances of persons using chinese characters. 
    queryString1   = "/national zh_TW utf8 firstname";
    queryString2   = "/national zh_TW utf8 lastname";
    string1        = "老板娘 Jordan"; 
    string2        = "私家偵探社";

    // query  for instances of class Person using the tuple VAT
    predicate = 
            session.newAttrString("{/tuple {/national zh_TW utf8 firstname } " +
                                  "{/national zh_TW utf8 lastname} }").eq(
			          "{" + string1 + "} {" + string2 + "}");
    h = person.select(predicate).asArray();
    if (h.length > 0)
      System.out.println("Query using tuple VAT and Chinese characters" +
                         " succeeded ....");
    else
      throw new RuntimeException("Tuple query using Chinese characters failed");
      
    // Query for instances of class Person, using the no case and the tuple
    // VAT.
    predicate = session.newAttrString("{/tuple {/nocase ascii firstname} " + 
                                      "{/nocase ascii lastname} }").eq(
				      "carole  {mortimer smith}");
    h = person.select(predicate).asArray();
    if (h.length > 0)
      System.out.println("Query using tuple and nocase VAT succeeded ....");
    else
      throw new RuntimeException("nocase query failed");
 
    person.dropClass();
    System.out.println("Done...");
  }
}

