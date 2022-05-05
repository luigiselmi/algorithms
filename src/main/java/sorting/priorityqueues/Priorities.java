package sorting.priorityqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Priorities {
  /*
   * Create a list of students then put them in priority queue
   * to sort them according to their cgpa.
   */
  public List<Student> getStudents(List<String> events) {
    PriorityQueue<Student> students = new PriorityQueue<Student>();
    for (String event: events) {
      String [] sub = event.trim().split(" ");
      if ("ENTER".equals(sub[0])) {
        String name = sub[1];
        double cgpa = Double.parseDouble(sub[2]);
        int id = Integer.parseInt(sub[3]);
        Student student = new Student(id, name, cgpa);
        students.add(student);
      }
      if ("SERVED".equals(sub[0])) {
        students.poll();
      }
    }
    
    List<Student> sortedStudent = new ArrayList<Student>();
    while ( ! students.isEmpty() ) {
      Student s = students.poll();
      sortedStudent.add(s);
    }
    
    return sortedStudent;
  }

}
