package tutorial.model;

public class Department {
  String name;
  Employee manager;

  public Department(String aName, Employee aManager) {
    name    = aName;
    manager = aManager;
  }

  public String toString() {
    return "Department: " + name + " manager: " +
    ((manager != null) ?  manager.name : "nobody");
  }
}
