class print_array {
    public void printArray(int[] array) {
        System.out.println("일반 for문으로 출력한 1차원 배열입니다.");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void printArray(int[][] array) {
        System.out.println("일반 for문으로 출력한 2차원 배열입니다.");
        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printArrayForEach(int[] array) {
        System.out.println("for-each 문으로 출력한 1차원 배열입니다.");
        for(int i:array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void printArrayForEach(int[][] array) {
        System.out.println("for-each 문으로 출력한 2차원 배열입니다.");
        for(int[] i:array) {
            for(int j:i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class arrayprint {
    public static void main(String[] args) {
        print_array A = new print_array();

        int[] array1 = {1,2,3};
        int[][] array2 = {
            {4,5,6},
            {7,8,9}
        };

        A.printArray(array1);
        A.printArray(array2);
        A.printArrayForEach(array1);
        A.printArrayForEach(array2);
    }
}
