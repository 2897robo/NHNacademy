import java.time.LocalTime;

public class ThreadCounter extends Thread{
    private String name;
    private int count;
    private int max_count;

    public ThreadCounter(String name, int max_count) {
        this.name = name;
        this.count = 0;
        this.max_count = max_count;
    }

    public void run() {
        while(count<max_count) {
            System.out.println(name + ", " + count);
            try {
                Thread.sleep(1000);
                count++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadCounter counter1 = new ThreadCounter("giuk1", 5);
        ThreadCounter counter2 = new ThreadCounter("giuk2", 5);

        System.out.println(LocalTime.now());
        counter1.start();
        counter2.start();
        System.out.println(LocalTime.now());
    }
}
