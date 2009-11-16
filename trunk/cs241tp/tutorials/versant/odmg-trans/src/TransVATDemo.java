import model.Person;
import com.versant.fund.*;
import com.versant.trans.*;
import com.versant.util.*;
import java.util.*;

/**
 * A very simple demo that demonstrates uage of VAT with Transparent JVI.
 * The person instances stored in the database have chinese, german and ascii
 * characters contained in them. The query string is set up with the
 * appropriate locale before querying the database.
 */

public class TransVATDemo {

  /* We need to modify the profile.be so that VAT is defined in the backend
   * profile. This function reads the profile and then modifies it as needed.
   */
  static void modifyBEProfile(String database)  {
    BEProfile be = DBUtility.getBEProfile(database);
    String versantRuntime = DBUtility.versantRuntimePath();
    String versantRoot = DBUtility.versantRootPath();
    String[] customDLLs;
    String OS = System.getProperty("os.name").toLowerCase();
    Properties prop = new Properties();
    prop.put("-f","");

    if (OS.indexOf("window") == 0) {
      String pluginPath = versantRuntime + "\\" + "bin" + "\\" ;
      customDLLs = new String[] {
          pluginPath + "national.dll" + "$" + versantRuntime,
          pluginPath + "nocase.dll"   + "$" + versantRuntime,
          pluginPath + "tuple.dll"    + "$" + versantRuntime
      };

    } 
    else if (OS.indexOf("hp-ux") == 0) {
      String pluginPath = versantRuntime + "/" + "lib" + "/" ;
      customDLLs = new String[] {
          pluginPath + "libnational.sl" + "$" + versantRuntime,
          pluginPath + "libnocase.sl"   + "$" + versantRuntime,
          pluginPath + "libtuple.sl"    + "$" + versantRuntime
      };

    } 
    else {
      String pluginPath = versantRuntime + "/" + "lib" + "/" ;
      customDLLs = new String[] {
          pluginPath + "libnational.so" + "$" + versantRuntime,
          pluginPath + "libnocase.so"   + "$" + versantRuntime,
          pluginPath + "libtuple.so"    + "$" + versantRuntime
      };
    }

    for (int i = 0; i < customDLLs.length; i++)
      be.addToCustom_BE_Plugins(customDLLs[i]);

    DBUtility.writeBEProfile (be);
    DBUtility.stopDB(database,prop);
    DBUtility.startDB(database);
  }

  public static void main (String[] args) {
    if (args.length < 1) {
      System.out.println("java TransVATDemo <database name>");
      System.exit(-1);
    }

    TransSession session;
    String database = args[0];

    modifyBEProfile(database);
    session = new TransSession(database);

    // Create Instances of class Person using German, Chinese and English
    // characters.
    Person person1 = new Person("JÆrgen Uwe", "Be¤le-Schmidt", 18, (float)6.1); 
    Person person2 = new Person("êÌ’åÑÁàÀ Jordan", "ç¦’à½´à’³åýç±¾", 18, (float)6.1);
    Person person3 = new Person("Carole", "Mortimer Smith", 18,(float)6.1);

    session.makePersistent(person1);
    session.makePersistent(person2);
    session.makePersistent(person3);

    session.commit();
    System.out.println("Done Committing the instances...");

    //Query. Use German to specify the search criteria.
    String queryString1   = "/national de_DE utf8 firstName";
    String queryString2   = "/national de_DE utf8 lastName";
    String string1        = "JÆrgen Uwe";
    String string2        = "Be¤le-Schmidt";

    // create index on first name 
    ClassHandle personClassHandle = session.locateClass("model.Person");
    personClassHandle.createIndex(queryString1, Constants.UNIQUE_BTREE_INDEX);

    // Having created a index, we will now query based on first name using the
    // national VAT. The Braces are necessary if the string has spaces
    // embedded in it. Here string1 has spaces embedded in the string it
    // refers to.
    Predicate predicate = session.newAttrString(queryString1).eq("{" + string1 
        + "}");
    Handle[] h = personClassHandle.select(predicate).asArray();
    if (h.length > 0)
      System.out.println("Query using national VAT and German characters" +
      " succeeded ....");
    else
      throw new RuntimeException("Query using German characters failed");

    // delete the index created.
    personClassHandle.deleteIndex(queryString1, Constants.UNIQUE_BTREE_INDEX);

    // query for instances of class person using the tuple and national VAT.
    VQLQuery query = new VQLQuery(session, "select selfoid from model.Person where `/tuple {/national de_DE utf8 firstName} {/national de_DE utf8 lastName}` = '{JÆrgen Uwe} Be¤le-Schmidt'");

    VEnumeration venum = query.execute();
    if (venum.size() > 0)
      System.out.println("Query using tuple VAT and German characters" +
      " succeeded ....");
    else
      throw new RuntimeException("Tuple Query using German Characters failed");

    // Query for instances of persons using chinese characters. 
    query = new VQLQuery(session, "select selfoid from model.Person where `/tuple {/national zh_TW utf8 firstName} {/national zh_TW utf8 lastName}` = '{êÌ’åÑÁàÀ Jordan} ç¦’à½´à’³åýç±¾'");

    venum = query.execute();
    // query  for instances of class Person using the tuple VAT
    if (venum.size() > 0)
      System.out.println("Query using tuple VAT and Chinese characters" +
      " succeeded ....");
    else
      throw new RuntimeException("Tuple query using Chinese characters failed");

    // Query for instances of class Person, using the no case and the tuple
    // VAT.
    query = new VQLQuery(session, "select selfoid from model.Person where `/tuple {/nocase ascii firstName} {/nocase ascii lastName}` = 'carole {mortimer smith}'");

    venum = query.execute();
    if (venum.size() > 0)
      System.out.println("Query using tuple and nocase VAT succeeded ....");
    else
      throw new RuntimeException("nocase query failed");

    System.out.println("Done...");
  }
}

