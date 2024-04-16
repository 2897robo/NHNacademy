@FunctionalInterface
public interface BinaryOp {
    int apply(int x, int j);
}

class Algo {
    public static int calc(BinaryOp binder, int i, int j) {
        return binder.apply(i, j);
    }
}

class Test {
    public static void main(String[] args) {
        BinaryOp adder = (x, y) -> x + y;
        Algo.calc(adder , 1, 2);
        Algo.calc((x, y) -> x * y, 1, 2);
    }
}