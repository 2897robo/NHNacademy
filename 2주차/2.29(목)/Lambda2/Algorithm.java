package Lambda2;

public class Algorithm {
    public static int calc(BinaryOp binder, int i, int j) {
        return binder.apply(i, j);
    }
}

class Adder {
    public static int add(int i, int j) {
        return i+j;
    }
}

class Test {
    public static void main(String[] args) {
        BinaryOp bb = (i, j) -> i+j;
        Algorithm.calc(bb, 1, 2);
    }
}