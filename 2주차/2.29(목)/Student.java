public class Student implements Comparable<Student> {
    int studentNo;
    String name;
    String department;

    public Student(int studentNo, String name, String department) {
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
    }

    @Override
    public String toString() {
        return this.studentNo + " " + this.name + " " + this.department;
    }

    // @Override
    // public int compareTo(Object o) {
    //     Student s = (Student) o;
    // }

    @Override
    public int compareTo(Student o) {
        return this.studentNo - o.studentNo;
    }
    
}
