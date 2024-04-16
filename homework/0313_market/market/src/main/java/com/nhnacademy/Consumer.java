package com.nhnacademy;
import java.util.concurrent.ThreadLocalRandom;

 public class Consumer implements Runnable {
    private Store store;
    Thread thread;

    public Consumer(Store store) {
        this.store = store;
        this.thread = new Thread(this);
    }

    public void start() {
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            //소비자는 매장에 입장 후 물건을 구매할 수 있다.
            //매장에는 입장 인원 제한이 있으므로, 인원 초과시 기다린다.
            store.enter();

            //1~10초 간격으로 구매한다.
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10001));
            
            //매장에 입장하면 물건을 구매하고, 퇴장한다.
            store.buy();
            store.exit();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}