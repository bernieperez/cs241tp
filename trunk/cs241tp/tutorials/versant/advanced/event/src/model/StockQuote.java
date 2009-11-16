package model;

import com.versant.fund.*;
import com.versant.trans.*;

public class StockQuote {
  public String symbolName;
  public double price;

  StockQuote(String symbolName, double price) {
    this.symbolName = symbolName;
    this.price      = price;
  }

  public void adjustPrice(int quantity) {
    price += quantity / 1000.0;
    if (price < 0.1)
      price = 0.1;
  }

  static public void initDatabase(String database) {
    TransSession session  = new TransSession (database);
    try {
      session.locateClass("model.StockQuote");
    } 
    catch (VException vex) {
      if (vex.getErrno () != 6002) // SCH_CLASS_UNDEFINED
        throw vex;
      for (int i = 0; i < stocks.length; i++) {
        StockQuote quote = new StockQuote (stocks [i], prices [i]);
        session.makeRoot (stocks [i], quote);
      }
    }
    session.endSession();
  }

  static public final double[] prices = { 20.60,  101.90, 34.42,  24.43  };
  static public final String[] stocks = { "VSNT", "MSFT", "SUNW", "ORCL" };
}
