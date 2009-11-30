package edu.ucla.cs241.termproj;

import com.versant.trans.TransSession;

import edu.ucla.cs241.termproj.queries.VersantQueries;

public class Versant {

    public static void main(String[] args) {
        Versant main = new Versant();
        main.run();
    }

    private void run() {
        TransSession session = new TransSession("mydb");

        VersantQueries test = new VersantQueries(session);
        test.run();
    }

}
