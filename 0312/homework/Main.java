package homework;

public class Main {
    public static void main(String[] args) {
        // Store 객체 생성
        Store store = new Store();
        
        // Producer 객체 생성 및 쓰레드 시작
        Producer producer = new Producer(store);
        new Thread(producer).start();
        
        // Consumer 객체 생성 및 쓰레드 시작
        for(int i = 0; i < 3; i++) {
            Consumer consumer = new Consumer(store);
            new Thread(consumer).start();
        }
    }
}