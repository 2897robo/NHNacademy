package sender;

import java.time.LocalTime;

public class Data {
    private String packet;
    
    // True if 수신자 should wait
    // False if 송신자 should wait
    private boolean transfer = true;
 
    //수신자가 받음 (수신자 대기)
    public synchronized String receive() {          
        while (transfer) {
            try {
                System.out.println(LocalTime.now() + ": wait " + Thread.currentThread().getName());
                wait();
                System.out.println(LocalTime.now() + ": Wakeup " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread Interrupted");
            }
        }
        transfer = true;
        
        String returnPacket = packet;
        notifyAll();
        System.out.println(LocalTime.now() + ": notifyAll " + Thread.currentThread().getName());
        return returnPacket;
    }
 
    //송신자 -> 수신자 보냄 (송신자 대기)
    public synchronized void send(String packet) {      
        while (!transfer) {
            try { 
                System.out.println(LocalTime.now() + ": wait sender1");
                wait();
                System.out.println(LocalTime.now() + ": Wakeup sender2");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread Interrupted");
            }
        }
        transfer = false;
        
        this.packet = packet;
        notifyAll();
    }
}