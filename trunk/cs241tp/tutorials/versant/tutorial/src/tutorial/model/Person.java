package tutorial.model;

public class Person {  
  public String name;
  public int age;

  public Person(String aName, int anAge) {
    name = aName;
    age  = anAge;
  }

  public String toString() {
    return "Person: " + name + " age: " + age;
  }
}
