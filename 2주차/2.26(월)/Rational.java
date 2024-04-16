public class Rational {
    int numerator;
    int denominator;

    public Rational(int num, int denom) {
        this.numerator = num;
        this.denominator = denom;

        int g = this.gCD(numerator, denominator);

        this.numerator = this.numerator / g;
        this.denominator = this.denominator / g;
    }

    private static int gCD(int m, int n) {
        return (n==0) ? m: gCD(n, m%n);
    }

    public Rational plus(Rational rational) {

    }
}


