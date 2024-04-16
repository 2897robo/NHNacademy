package colleage;

public class Graduator extends Student {
    private String major;

    public Graduator(int studentNo, String name, String department, String major) {
        super(studentNo, name, department);
        this.major = major;
    }

    public String toString() {
        return super.toString() + ", " + this.major;
    }
    
}
