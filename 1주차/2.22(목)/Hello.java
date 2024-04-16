public class Hello {
    void myMethod() {
        System.out.println();
    }

    static int add(int i, int j) {
        return i+j;
    }

    public static void main(String[] args) {
        Hello hi = new Hello();
        hi.myMethod();

        int i=5, j=1;
        int sum = add(i,j);

        Sample.addInt(i, j);
    }
}

class Sample {      // public 클래스는 하나다.
    static int addInt(int i, int j) {
        return i+j;
    }
}