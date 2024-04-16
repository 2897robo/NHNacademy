class ThreadA extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            // 현재 실행 중인 스레드가 CPU 사용을 양보(yield)
            Thread.yield();
        }
    }
}

class ThreadB extends Thread {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            // 현재 실행 중인 스레드가 CPU 사용을 양보(yield)
            Thread.yield();
        }
    }
}

public class YieldExample {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA();
        ThreadB t2 = new ThreadB();

        // 스레드 시작
        t1.start();
        t2.start();
    }
}