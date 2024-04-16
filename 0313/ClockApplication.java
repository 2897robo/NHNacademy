public class ClockApplication {

    public static void main(String[] args) {
        // 사용자 스레드 생성 및 시작: 시간 표시 기능
        Thread userThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("현재 시간: " + System.currentTimeMillis());
                    try {
                        Thread.sleep(1000); // 1초마다 시간 표시
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        userThread.start(); // 사용자 스레드 시작

        // 데몬 스레드 생성 및 시작: 로그 기록 기능
        Thread daemonThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    // 현재 시간을 로그 파일에 기록하는 코드 (가상 코드)
                    logTime(System.currentTimeMillis());
                    try {
                        Thread.sleep(5000); // 5초마다 로그 기록
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void logTime(long time) {
                // 로그 파일에 시간을 기록하는 로직 (가상 코드)
                System.out.println("로그 기록: " + time);
            }
        });
        daemonThread.setDaemon(true); // 이 스레드를 데몬 스레드로 설정
        daemonThread.start(); // 데몬 스레드 시작
    }
}
