package edu.ucla.cs241.termproj.script;

import java.util.Random;

import de.beimax.janag.Namegenerator;
import edu.ucla.cs241.termproj.schema.Person;

public class PersonGenerator {
    
    Namegenerator ng = new Namegenerator("languages.txt", "semantics.txt");
    Random randomGenerator = new Random();
    
    public PersonGenerator() {
    }
    
    public Person createPerson(Person person) {
        String sex;
        String name;
        if (randomGenerator.nextBoolean()) {
            sex = "female";
            name = ng.getRandomName("U.S. Human (1990)", "Female - Top 500+");
        } else {
            sex = "male";
            name = ng.getRandomName("U.S. Human (1990)", "Male - Top 500+");
        }
        int age = randomGenerator.nextInt(42) + 28; // Age 28-70
        
        // Assign to this person
        person.setName(name);
        person.setSex(sex);
        person.setAge(age);

        return person;
    }

}
