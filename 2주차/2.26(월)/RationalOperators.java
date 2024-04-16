public class RationalOperators {
    public static int[] plus(int[] left, int[] right) {
        int nominator = left[0] + right[0];
        int denominator = left[1] + right[1];

        int g = gCD(nominator, denominator);

        nominator = nominator / g;
        denominator = denominator / g;

        return new int[] {nominator, denominator};
    }

    public static int[] times(int[] left, int[] right) {
        int nominator = left[0] * right[0];
        int denominator = left[1] * right[1];

        int g = gCD(nominator, denominator);

        nominator = nominator / g;
        denominator = denominator / g;

        return new int[] {nominator, denominator};
    }

    public static int[] divide(int[] left, int[] right) {
        int[] result = times(left, right);
        return new int[] {result[1], result[0]};
    }

    private static int gCD(int m, int n) {
        return (n==0) ? m: gCD(n, m%n);
    }
}

class Oper_test {
    public static void main(String[] args) {
        int[] i = {2,4};
        int[] j = {2,4};

        System.out.println(RationalOperators.plus(j,i));
    }
}