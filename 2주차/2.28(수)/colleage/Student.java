package colleage;

public class Student implements Comparable<Student> {
    private int studentNo;
    private String name;
    private String department;

    public Student(int studentNo, String name, String department) {
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
    }

    public String toString() {
        return this.studentNo + ", " + this.name + ", " + this.department;
    }

    public int compareTo(Student item) {
        return this.studentNo - item.studentNo;
    }
}
