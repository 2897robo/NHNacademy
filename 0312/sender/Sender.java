package sender;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {
    private Data data;
 
    // standard constructors
 
    public Sender(Data data2) {
        this.data = data2;
    }

    @Override
    public void run() {
        String packets[] = {
          "First packet",
          "Second packet",
          "Third packet",
          "Fourth packet",
          "End"
        };
 
        for (String packet : packets) {
            data.send(packet);

            // Thread.sleep() to mimic heavy server-side processing
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread Interrupted"); 
            }
        }
    }
}