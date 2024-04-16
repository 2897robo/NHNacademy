public interface Sample {
    int apply(int i, int j);
    default int apply(int i, int j, int k) {
        return apply(apply(i, j), k);
    }
}

class Adder implements Sample {
    public int apply(int i, int j) {
        return i+j;
    }

    public static void main(String[] args) {
        Adder a = new Adder();
        a.apply(1, 2, 3);
    }
}