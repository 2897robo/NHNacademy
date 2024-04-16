import java.time.LocalTime;

public class RunnableCounter implements Runnable {
    Thread thread;
    String name;
    int count;
    int maxCount;

    public RunnableCounter(String name, int maxCount) {
        this.name = name;
        this.maxCount = maxCount;
        count = 0;
        thread = new Thread(this);
    }

    // 내가 필드로 가지고 있는 쓰레드를 돌아가게 만들기
    public void start() {
        this.thread.start();
    }

    // 내가 필드로 가지고 있는 쓰레드를 멈추게 만들기
    public void stop() {
        // this.thread.interrupt(); // 이건 RunnableCounter의 쓰레드에게 interrupt를 준 것.
        Thread.currentThread().interrupt(); // 이건 main의 쓰레드에게 interrupt를 준 것.
    }

    public Thread getThread() {
        return this.thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    /*
     * InteruptedException -> sleep()을 할 때 무조건 써야 함.
     * Thread가 자고 있으면 Interrupt를 받을 수 없기 때문임. 그래서 catch문으로 깨워서 -> 굳이굳이 다시 깨워서
     * interrupt를 걸어 주는 것.
     */
    @Override
    public void run() {
        while (!this.getThread().isInterrupted() && this.count < this.maxCount) {
            try {
                Thread.sleep(1000);
                // InterruptedException은 스레드가 대기(waiting), 수면(sleeping), 또는 작업 중(blocked)일 때, 다른
                // 스레드가 현재 스레드를 중단(interrupt)하려고 시도할 때 발생하는 예외 => 이 경우에 깨워서 끊게 만들어야 함.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 현재 돌아가고 있는 쓰레드를 -> 깨워서 -> interrupt를 걸자.
            }
            this.count += 1;
            System.out.println(this.name + " : " + this.count);

            if (this.getCount() > 5) {
                stop();
                // 두 코드는 다르다
                // this.thread.interrupt(); // 이건 RunnableCounter의 쓰레드에게 interrupt를 준 것.
                // Thread.currentThread().interrupt(); // 이건 main의 쓰레드에게 interrupt를 준 것.
            }
        }
    }

    // 함수의 call 관계와 쓰레드의 주인은 다르다........................
    public static void main(String[] args) {
        RunnableCounter[] counters = new RunnableCounter[10]; // counters는 RunnableCounter 타입의 배열일 뿐.
        for (int i = 0; i < 10; i++) {
            counters[i] = new RunnableCounter("counter" + (i), 10); // counters는 각각의 쓰레드를 가진다.
            counters[i].start();
        }

        boolean isAllStopped = false; // 하나라도 멈췄나요?
        while (!isAllStopped) {
            for (int i = 0; i < counters.length; i++) { //
                if (!counters[i].getThread().isAlive()) { // 하나라도 살아있지 않다면
                    isAllStopped = true; // while문 끊어서 엔드 표시하자.
                }
            }
        }

        System.out.println("end : " + LocalTime.now());
    }
}