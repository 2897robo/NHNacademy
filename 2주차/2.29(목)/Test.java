public class Test {

    public static void main(String[] args) {
        MyList<Student> list = new MyList<>();

        list = new MyList<>();
        list.add(new Student(3, "Celine", "CE"));
        list.add(new Student(1, "James", "CE"));
        list.add(new Student(2, "Robert", "CE"));

        System.out.println("=====정렬 전=====");
        for (Student i : list) { // for-each의 대상은 iterable 인터페이스를 구현한 클래스의 객체. (뒤에 들어가는 거 말하는 거야)
            System.out.print(i + " ");
        }
        System.out.println();

        list.sort();

        System.out.println("=====정렬 후=====");
        for (Student i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
