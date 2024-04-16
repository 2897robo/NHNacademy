public class changepara {
    public static int max(int... values) {
        int big = 0;
        for(int i:values) {
            if(i>big) {
                big = i;
            }
        }

        return big;
    }

    public static void main(String[] args) {
        System.out.println(max(3,5,6,7,8));
    }
}
