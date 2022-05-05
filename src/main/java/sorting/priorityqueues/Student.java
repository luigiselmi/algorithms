package sorting.priorityqueues;

public class Student implements Comparable<Student>{
  
  private int id;
  private String name;
  private double cgpa;

  public Student(int id, String name, double cgpa) {
    this.id = id;
    this.name = name;
    this.cgpa = cgpa;
  }
  /*
   * Returns the id of the student
   */
  public int getID() {
    return id;
  }
  /*
   * Returns the name of the student.
   */
  public String getName() {
    return name;
  }
  /*
   * Returns the CGPA of the student.
   */
  public double getCGPA() {
    return cgpa;
  }
  
  @Override
  public int compareTo(Student o) {
    int result = 0;
    // 1st test by cgpa
    if (this.getCGPA() > o.getCGPA()) result = -1;
    if (this.getCGPA() < o.getCGPA()) result =  1;
    
    // 2nd test by alphabetical order
    if (this.getCGPA() == o.getCGPA()) {
      int name_comparison = this.getName().compareTo(o.getName()); 
      if (name_comparison != 0) result = name_comparison;
    }
    
    // lastly test by id
    if (this.getCGPA() == o.getCGPA() && this.getName().equals(o.getName())) {
      if (this.getID() > o.getID()) result = -1;
      if (this.getID() < o.getID()) result = 1;
    }
    
    return result;
  }
  
}
