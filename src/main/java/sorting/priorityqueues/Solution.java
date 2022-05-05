package sorting.priorityqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
  
  private final static Scanner scan = new Scanner(System.in);
  private final static Priorities priorities = new Priorities();

  public static void main(String[] args) {
    
    int totalEvents = Integer.parseInt(scan.nextLine());    
    List<String> events = new ArrayList<>();
    
    while (totalEvents-- != 0) {
      String event = scan.nextLine();
      events.add(event);
    }
    
    List<Student> students = priorities.getStudents(events);
    
    if (students.isEmpty()) {
      System.out.println("EMPTY");
    } 
    else {
      for (Student st: students) {
          System.out.println(st.getName());
      }
    }
    
  }

}
