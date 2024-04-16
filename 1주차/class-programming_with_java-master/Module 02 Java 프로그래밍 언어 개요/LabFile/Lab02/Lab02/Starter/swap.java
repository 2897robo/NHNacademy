public class swap {
    public static void main(String[] args) {
        int i = 5;
        int j = 10;

        swap(i,j);

        System.out.printf("MAIN i: %d, j: %d", i, j);
    }

    public static void swap(int i, int j) {
        int tmp = i;
        i = j;
        j = tmp;

        System.out.printf("SWAP i: %d, j: %d", i, j);
        System.out.println();
    }
}
