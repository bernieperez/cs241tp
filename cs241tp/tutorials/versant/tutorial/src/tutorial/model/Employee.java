package tutorial.model;

public class Employee extends Person {
  
  public Department department;
  public double salary;

  public Employee(String aName, int anAge, double aSalary) {
    super(aName, anAge);
    salary = aSalary;
  }

  public String toString() {
    return "Employee: " + name + " age: " + age +
           " salary: " + salary + " " + department;
  }
}
