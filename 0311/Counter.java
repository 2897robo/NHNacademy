public class Counter {
    private String name;
    private int count;
    private int max_count;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getMax_count() {
        return max_count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMax_count(int max_count) {
        this.max_count = max_count;
    }

    public Counter(String name, int max_count) {
        this.name = name;
        this.count = 0;
        this.max_count = max_count;
    }

    public void run() {
        while(count<max_count) {
            System.out.println(getName() + ", " + getCount());
            try {
                Thread.sleep(1000);
                setCount(count+1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter1 = new Counter("giuk1", 5);
        Counter counter2 = new Counter("giuk2", 5);
        counter1.run();
        counter2.run();
    }
}