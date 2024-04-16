import java.util.*;

public class Utilities {
    public static int max(int i, int j) {
        if (i > j)
            return i;
        else
            return j;
    }

    public static int max3(int i, int j, int k) {
        return max(max(i,j), k);
    }

    public static int max(int... values, int i) {
        for(int j:values) {
            return max(values, j--)
        }
    }

    public static int max(int... values) {
        int result = values[0];
        for(int i:values) {
            result = max(i, result);
        }
        return result;
    }

    public static void swap(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }
}

class Test {
    public static void main(String[] args) {

        int big = Utilities.max(1,5,6,7,1);
        System.out.println("most big num is : " + big);

        int i = 5, j = 6;
        Utilities.swap(i, j);
        System.out.printf("swap Func : %d, %d", i, j);
    }
}