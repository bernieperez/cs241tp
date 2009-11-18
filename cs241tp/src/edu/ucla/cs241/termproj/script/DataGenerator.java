package edu.ucla.cs241.termproj.script;

import de.beimax.janag.Namegenerator;

public class DataGenerator {

    /**
     * Creates data to populate our schema.
     */
    public static void main(String[] args) {
        Namegenerator ng = new Namegenerator("languages.txt", "semantics.txt");
        String[] males = ng.getRandomName("U.S. Human (1990)", "Male - Top 500+", 100);
        String[] females = ng.getRandomName("U.S. Human (1990)", "Female - Top 500+", 100);
        for(String name : males) {
            System.out.println(name);
        }
        for(String name : females) {
            System.out.println(name);
        }
        System.out.println();
        System.out.println("Done Bitch! I just jacked yo code!!!");
    }

}
