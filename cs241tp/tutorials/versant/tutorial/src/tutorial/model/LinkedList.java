package tutorial.model;

public class LinkedList {
  int label;
  LinkedList next_node;

  public LinkedList(int aLabel, LinkedList list) {
    label = aLabel;
    next_node = list;
  }

  public String toString() {
    return label + ((next_node == null) ? "" : " " + next_node);
  }
}
