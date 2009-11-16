package index;


import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import com.versant.fund.Constants;
import com.versant.fund.QueryExecutionOptions;
import com.versant.trans.*;
import model.*;

public class IndexDemo {

  static String database;

  public IndexDemo( ) {
  }

  public static void main( String  args[ ] ) {

    if (args.length  < 1) {
      System.out.println( "Usage  java IndexDemo <<dbname>>" );
      System.exit(0);
    }

    database = args [ 0 ];
    String param = "";	
    if (args.length == 2) 
      param = args [1];
    
    final String vqls[] = {
        "select selfoid from model.CD where (model.Singer) singer->name like \"Wilson\"",
    };

    IndexDemo index = new IndexDemo();

    if (param.equals("-run"))
      // display  data
      index.displayFilterBy(vqls[0]);
    else 
      // insert data
      index.insertData( );

  }

  public void insertData() {
    TransSession session1 = new TransSession( database );
    final int MAX = 100;
    final String names[] = {"Clinton", "Tom", "Wilson", "Mono", "Tom", "Jonson"};
    final String genders[] = {"male", "female"}; 

    try  {		
      Random rand = new Random();
      for (int i = 0; i <= MAX ; i++ ) {
        String id = new String("" + (i + 1));
        String title = "CD" + i ;
        int price = rand.nextInt(1000);
        Image image = new Image();
        String transtFromDate = new String(((12 + i) % 30) + "-" + ((i + 1) % 12) + "-" + 2005);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dtFromDate = sdf.parse(transtFromDate);
        Singer singer = new Singer(i + 1, names[i%6], dtFromDate, genders[i%2]);
        CD cd = new CD(id, title, price, image);

        cd.setSinger(singer);
        cd.setCopiesSold(20 * (i % MAX));
        cd.addToStock(10 *  i);

        session1.makePersistent(cd);
      }	
      session1.commit();
      session1.endSession();

    }
    catch(java.text.ParseException e) {
      e.printStackTrace();
    }
  } 

  public void displayFilterBy(String filter)  {
    TransSession session1 = new TransSession(database);
    Query query = new Query(session1, filter);
    QueryResult result = query.execute( );
    Object obj = null;
    int counter = 0;

    while ((obj = result.next()) != null) {
      CD cd = (CD)obj;
      System.out.println("retrived : " + cd);
      ++counter;
    }
    System.out.println("Total : " + counter + " results.");

    result.close();
    session1.endSession();
  }

}
