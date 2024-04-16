public class passtest {
    static void process(int value) {
        System.out.println("전달 받은 파라미터 값 : " + value);
        value = 10;
        System.out.println("변경된 파라미터 값 : "+value);
    }

    public static void main(String[] args) {
        int i = 5;
        System.out.println("선언한 변수의 초깃 값 : "+i);
        process(i);
        System.out.println(("process 함수 호출 이후 변수의 값 : "+i));
    }
}
