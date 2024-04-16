public class test {
    public static int max(int... values, int n) {
        
        if(n==1) {
            return max(values[n-1]);
        }
        return max(values[n], )
    }

    public static void main(String[] args) {
        System.out.println(max({1,2,5,4,3}, 5));
    }
}
