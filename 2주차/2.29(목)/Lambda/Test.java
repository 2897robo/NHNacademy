package Lambda;

class PrintToStandardOutput implements Function{
    @Override
    public void print(String valueable) {
        System.out.println(valueable);
    }

}

public class Test {
    public static void main(String[] args) {
        Student s = new Student(1, "hohoho");

        //s.printName(x -> System.out.println(x));
        s.printName(System.out::println);
    }
}
