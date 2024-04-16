public class threadState {
    
}

class Exam01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            long count = 0;
            while(count<Integer.MAX_VALUE) {
                count++;
            }
        });

        System.out.println(thread.getState());

        thread.start();

        while(thread.isAlive()) {
            System.out.println(thread.getState());  
                // RUNNING이 아닌 Runnerble이 나오는 이유 : 
                // 타 스레드가 running 상태이므로, 다른 스레드는 runnable 임
            Thread.sleep(100);
        }

        System.out.println(thread.getState());
    }
}

class Exam03 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized(lock) {
                for(int i=0; i<10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized(lock) {
                for(int i=0; i<10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch(InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());

        thread1.start();
        thread2.start();

        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }
}