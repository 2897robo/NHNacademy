public class bit {
    static int getBit(int num, int i, boolean val) {
        return num & ~(1 << i) | ((val ? 1:0) << i);
    }
    public static void main(String[] args) {
        // 1 0 1 0 1 0 0 1
        System.out.println(getBit(169, 3, false));

        //실행결과 : 161 = 1 0 1 0 0 0 0 1
    }
}
