public class sample {
    public static void main(String[] args) {
        int [][] matrix = {
            {0,0,0},
            {1,1,1}
        };

        System.out.println(matrix.length);

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j< matrix[i].length; j++) {
                System.out.println(matrix[i][j]);
            }
        }
    }
}
