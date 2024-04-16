public class compare {

    static int max(int i, int j, int k) {
        int num = (i>j) ? i:j;
        int big = (k>num) ? k:num;

        System.out.println(big);
        return big;
    }

    public static void main(String[] args) {
        compare.max(7, 5, 5);
    }
}
