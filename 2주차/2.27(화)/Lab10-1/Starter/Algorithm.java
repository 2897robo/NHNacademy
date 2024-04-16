public class Algorithm {
    static final int addFrom1To10 = 55;
    
    /*
    public static int sigma(int begin, int end, int step) {
        return accumulate(new Adder(), 0, begin, end, step); 
    }

    public static int pi(int begin, int end, int step) {
        return accumulate(new Multiplier(), 1, begin, end, step);
    }
    */

    static int accumulate(BinaryOp binder, int init, int begin, int end, int step) {
        int result = init;
        for (int next = begin; next <= end; next = next + step) {
            result = binder.apply(result, next);
        }
        return result;
    }

    public static void main(String[] args) {
        int result = accumulate((i,j) -> i+j, 0, 1, 10, 1);     //람다 표현식
        System.out.println(result);
        //System.out.println(sigma(1, 10, 1));
        //System.out.println(pi(1, 10, 1));
    }
}