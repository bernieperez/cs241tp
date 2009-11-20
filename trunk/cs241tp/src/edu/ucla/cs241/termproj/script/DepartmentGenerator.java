package edu.ucla.cs241.termproj.script;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class DepartmentGenerator {

    private String fileName = "Departments.txt";

    private ArrayList<String> departments = new ArrayList<String>();

    public DepartmentGenerator() {
        loadDepartments();
    }

    public String getRandomDepartment() {
        Random randomGenerator = new Random();
        // Generating random integers in range 0..(X-1).
        int randomInt = randomGenerator.nextInt(departments.size());
        return departments.get(randomInt);
    }

    public ArrayList<String> getAllDepartments() {
        return departments;
    }

    private void loadDepartments() {
        String line;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(DepartmentGenerator.class.getResourceAsStream(fileName)));

            while ((line = in.readLine()) != null) {
                departments.add(line);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
