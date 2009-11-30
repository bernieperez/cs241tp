package edu.ucla.cs241.termproj.queries;

import java.util.ArrayList;

import com.versant.trans.TransSession;

import edu.ucla.cs241.termproj.queries.util.Query;
import edu.ucla.cs241.termproj.queries.versant.Query1;
import edu.ucla.cs241.termproj.queries.versant.Query2;
import edu.ucla.cs241.termproj.queries.versant.Query3;
import edu.ucla.cs241.termproj.queries.versant.Query4;
import edu.ucla.cs241.termproj.queries.versant.Query5;

public class VersantQueries {
    
    TransSession session;
    ArrayList<Query> queries = new ArrayList<Query>();
    
    public VersantQueries(TransSession session) {
        this.session = session;
        addQueries();
    }
    
    private void addQueries() {
        queries.add(new Query1("Query 1", session));
        queries.add(new Query2("Query 2", session));
        queries.add(new Query3("Query 3", session));
        queries.add(new Query4("Query 4", session));
        queries.add(new Query5("Query 5", session));
    }
    
    public void run() {
        for(Query query : queries) {
            query.run();
            System.out.println(query.printResults());
        }
    }

}
