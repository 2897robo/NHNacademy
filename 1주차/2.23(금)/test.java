class Adder {
    public int apply(int i, int j) {
        return i+j;
    }
}

public class test {
    public static void main(String[] args) {
        String s = "abc";
        Adder a = new Adder();
        int[] array = {1,2,3};
        int[][] matrix = {
            {1,2,3},
            {6,7,8}
        };

        System.out.println(s);
        System.out.println(a);
        System.out.println(array);
        System.out.println(matrix[0][1]);

        
    }
}