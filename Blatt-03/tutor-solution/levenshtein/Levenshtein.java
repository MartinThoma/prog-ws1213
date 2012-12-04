package levenshtein;

/**
 * A class to calculate a modified version of the levenshtein distance.
 *
 * @author Markus Iser
 * @version 1.0
 */
public class Levenshtein {
    /** First word. */
    private final String word1;

    /** Second word. */
    private final String word2;

    /** Holds the levenshtein matrix. */
    private final int[][] distance;

    /** A list of german umlauts. */
    private final char[] umlauts = { 'ä', 'ö', 'ü' };

    /** A list of german vowels. */
    private final char[] vowels = { 'a', 'e', 'i', 'o', 'u' };

    /**
     * Initializes the Levenshtein Object with the two given words.
     *
     * @param word1
     *            first word
     * @param word2
     *            second word
     */
    public Levenshtein(String word1, String word2) {
        this.word1 = "." + word1.toLowerCase();
        this.word2 = "." + word2.toLowerCase();
        this.distance = new int[this.word2.length()][this.word1
                .length()];
    }

    /**
     * Calculates the Levenshtein-matrix.
     *
     * @return the Levenshtein distance of word1 and word2
     */
    public int getDistance() {
        distance[0][0] = 0;
        for (int i = 0; i < word2.length(); i++) {
            for (int j = 0; j < word1.length(); j++) {
                if (i != 0 || j != 0) {
                    distance[i][j] = Math.min(replace(i, j), Math
                            .min(delete(i, j), insert(i, j)));
                }
            }
        }
        return distance[word2.length() - 1][word1.length() - 1];
    }

    /**
     * Helper function, encodes a kind of borderless behaviour of the
     * Levenshtein-matrix.
     *
     * @param i
     *            row-index or the coordinate in word2
     * @param j
     *            column-index or the coordinate in word1
     * @return the matrix-value at the given coordinates or Integer.MAX_VALUE-1
     *         if out of bounds
     */
    private int getDistanceAt(int i, int j) {
        if (i < 0 || j < 0 || i > word2.length()
                || j > word1.length()) {
            return Integer.MAX_VALUE - 1;
        } else {
            return distance[i][j];
        }
    }

    /**
     * @param test
     *            the character to check for being umlaut
     * @return {@code true} if the given character is a german umlaut, otherwise
     *         {@code false}
     */
    private boolean isUmlaut(char test) {
        for (int i = 0; i < umlauts.length; i++) {
            if (umlauts[i] == test)
                return true;
        }
        return false;
    }

    /**
     * @param test
     *            the character to check for being vowel
     * @return {@code true} if the given character is a german vowel,
     *         otherwise {@code false}
     */
    private boolean isVowel(char test) {
        for (int i = 0; i < vowels.length; i++) {
            if (vowels[i] == test)
                return true;
        }
        return false;
    }

    /**
     * @param i
     *            row-index or the coordinate in word2
     * @param j
     *            column-index or the coordinate in word1
     * @return the total cost if a deletion took place at the given coordinates
     */
    private int delete(int i, int j) {
        if (word1.charAt(j) == 'h'
                && (isUmlaut(word1.charAt(j - 1)) || isVowel(word1
                        .charAt(j - 1)))) {
            return getDistanceAt(i, j - 1); // delete of 'h' after umlauts and
            // vocals costs nothing
        }
        return getDistanceAt(i, j - 1) + 1;
    }

    /**
     * @param i
     *            row-index or the coordinate in word2
     * @param j
     *            column-index or the coordinate in word1
     * @return the total cost if an insertion took place at the given
     *         coordinates
     */
    private int insert(int i, int j) {
        if (word2.charAt(i) == 'h'
                && (isUmlaut(word2.charAt(i - 1)) || isVowel(word2
                        .charAt(i - 1)))) {
            return getDistanceAt(i - 1, j); // insert of 'h' after umlauts and
            // vocals costs nothing
        }
        return getDistanceAt(i - 1, j) + 1;
    }

    /**
     * @param i
     *            row-index or the coordinate in word2
     * @param j
     *            column-index or the coordinate in word1
     * @return the total cost if a replacement (for the non-equal case) took
     *         place at the given coordinates
     */
    private int replace(int i, int j) {
        return word2.charAt(i) == word1.charAt(j) ? getDistanceAt(
                i - 1, j - 1) : getDistanceAt(i - 1, j - 1) + 1;
    }

    /**
     * Testing it.
     *
     * @param args
     *            commandline arguments (should be used with two arguments: two
     *            words for which the levenshtein-matrix is calculated)
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Two Arguments required");
            System.exit(0);
        }
        Levenshtein levi = new Levenshtein(args[0], args[1]);
        System.out.println("The distance between '" + args[0]
                + "' and '" + args[1] + "' is " + levi.getDistance());

        for (int i = 0; i < levi.word2.length(); i++) {
            for (int j = 0; j < levi.word1.length(); j++) {
                System.out.print(levi.getDistanceAt(i, j) + "|");
            }
            System.out.println();
        }
    }
}
