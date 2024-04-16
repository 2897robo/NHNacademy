public class maxrecul {
    public static int max(int i, int j) {
        return (i>j)?i:j;
    }

    public static int max(int... values) {
        int result = 0;
        for(int i=0; i<values.length; i++) {
            result = max(values[i], result);
        }
        return result;
    }

    public static int maxre(int index, int... values) {
        if(index==1) {
            return values[0];
        }
        else {
            return max(values[index-1], maxre(index-1, values));
        }
    }
    public static void main(String[] args) {
        int result = maxre(10, 1,2,4,5,61,5,1);
        System.out.println(result);
    }
}