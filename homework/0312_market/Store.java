package homework;

 public class Store {
    //매장에는 최대 10개의 물건만 전시할 수 있다.
    private final int MAX_ITEMS = 10;
    private int items = 0;
    //매장은 최대 5명까지만 동시 입장 가능하다.
    private final int MAX_CUSTOMERS = 5;
    private int customers = 0;

    public synchronized void enter() throws InterruptedException {
        while(customers == MAX_CUSTOMERS) {
            wait();
        }
        customers++;
        System.out.println("고객 입장, 현재 고객 수: " + customers);
    }

    public synchronized void exit() {
        customers--;
        System.out.println("고객 퇴장, 현재 고객 수: " + customers);
        notifyAll();
    }

    //매장에서 물건 구매는 동시에 1명만 가능하다.
    public synchronized void buy() throws InterruptedException {
        while(items <= 0) {
            wait();     
        }
        items--;
        System.out.println("물건 구매, 남은 물건 수: " + items);
        notifyAll();    //매장에서 물건 판매 후 빈 공간에 생기면 생산자에게 알려 준다.
    }

    //매장에서 물건 납품은 동시에 1명만 가능하다.
    public synchronized void sell() throws InterruptedException {
        while(items == MAX_ITEMS) {
            wait();
        }
        items++;        //매장은 물건을 납품 받아서 판매한다.
        System.out.println("물건 납품, 남은 물건 수: " + items);
        notifyAll();    //매장에서 물건이 들어오면 소비자에게 알려 준다.
    }
}