public class student implements Comparable<student> {
    private int studentNo;
    private String name;

    public student (int studentNo, String name) {
        this.studentNo = studentNo;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public int getStudentNo() {
        return this.studentNo;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(student o) {
        if(this.studentNo > o.studentNo) {
            return 1;
        }
        else if(this.studentNo == o.studentNo) {
            return 0;
        }
        else {
            return -1;
        }
    }

    public static void switch_location(student tmp1, student tmp2) {
        student tmp3 = tmp1;
        tmp1 = tmp2;
        tmp2 = tmp3;
    }

    public void print_stuNo() {
        System.out.print(this.studentNo + " ");
    }
}