package sender;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Thread sender = new Thread(new Sender(data), "sender");
        Thread receiver1 = new Thread(new Receiver(data), "receiver1");

        sender.start();
        receiver1.start();
    }
}
