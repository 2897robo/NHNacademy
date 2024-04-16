public class bublesort {
    public static void main(String[] args) {
        int[] a = {2,6,4,5,1};

        for(int i=a.length-1; i>=0; i--) {
            for(int j=0;j<i;j++) {
                if(a[j] < a[j+1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
        }

        System.out.println(a);
            
    }
}
