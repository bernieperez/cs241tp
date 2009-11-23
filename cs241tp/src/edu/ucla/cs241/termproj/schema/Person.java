package edu.ucla.cs241.termproj.schema;

public class Person {

    private String name;

    private int age;
    
    private Person spouse;
    
    private String sex;

    public Person() {
    }

    /**
     * Getters and Setters
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Person getSpouse() {
        return spouse;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
