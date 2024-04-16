public class Algorithm {
    // To-do add sigma method here
    static int sigma(int start, int end, int step) {
        int result = 0;
        for (int next = start; next <= end; next = next + step) {
            result = result + next;
        }
        return result;
    }

    // To-do add pi method here
    static int pi(int start, int end, int step) {
        int result = 1;
        for (int next = start; next <= end; next = next+step) {
            result = result * next;
        }
        return result;
    }

    // To-do add accumulate method here
    static int addFrom1To10() {
        int sum = 0;
        for(int i=1; i<=10; i++) {
            sum += i;
        }
        return sum;
    }

    // To-do add overloaded accumulate method here
    static int accumulate(int init, int end, int step) {
        int result = init;
        for(int next=)
    }


    public static void main(String[] args) {
        System.out.println(addFrom1To10());
        System.out.println(sigma(1, 10, 1));
        System.out.println(pi(1, 10, 1));
    }
}