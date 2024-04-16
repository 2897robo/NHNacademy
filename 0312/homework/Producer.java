package homework;
import java.util.concurrent.ThreadLocalRandom;

 //생산자는 매장에 물건이 부족하지 않도록 채워둔다.
 public class Producer implements Runnable {
    private Store store;

    public Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        try {
            while(true) {
                store.sell();
                //물건은 1~10초 간격으로 채운다.
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10001));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}