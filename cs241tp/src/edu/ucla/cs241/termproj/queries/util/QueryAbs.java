package edu.ucla.cs241.termproj.queries.util;

import com.versant.trans.TransSession;


public abstract class QueryAbs implements Query {
    protected String name = "";
    protected String results = "";
    public static String newline = System.getProperty("line.separator");
    
    protected TransSession session;
    
    private long start = 0;
    private long end = 0;
    
    public QueryAbs() {
    }
    
    public QueryAbs(String name) {
        this(name, null);
    }
    
    public QueryAbs(String name, TransSession session) {
        this.name = name;
        this.session = session;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void run() {
        init();
        this.start = System.currentTimeMillis();
        query();
        this.end = System.currentTimeMillis();
        tearDown();
    }
    
    public double getResults() {
        return this.end - this.start;
    }
    
    public String printResults() {
        return results;
    }

}
