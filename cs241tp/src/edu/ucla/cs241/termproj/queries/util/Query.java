package edu.ucla.cs241.termproj.queries.util;

public interface Query {
    // Name of query
    public String getName();
    
    // Start the query
    public void run();
    
    // The actual query
    void query();
    
    // Return Results of query
    public double getResults();
    
    // Display Results
    public String printResults();
    
    void init();
    void tearDown();

}
