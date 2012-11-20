/**
 * The class Tribonacci calculates the first 37 numbers of the tribonacci
 * sequence.
 *
 * @author Markus Iser, Martin Thoma
 * @version 1.0
 */
public final class Tribonacci {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Tribonacci() {
    }

    /**
     * Command line interface.
     *
     * @param args The command line parameters.
     */
    public static void main(final String[] args) {
        int a = 1, b = 1, c = 1;
        for (int i = 4; i <= 37; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        System.out.println(c);
    }
}

