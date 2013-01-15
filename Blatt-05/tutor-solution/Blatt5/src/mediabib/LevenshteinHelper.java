package mediabib;

import programmieren.Levenshtein;



/**
 * Hilfsklasse damit die vorgegebene Levenshtein Klasse nicht geändert werden muss.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public class LevenshteinHelper {

    /** Wort 1 für die Levenshtein Distanz. */
    private final String word1;

    /** Wort 2 für die Levenshtein Distanz. */
    private final String word2;

    /** Levenshtein Distanz Objekt. */
    private final Levenshtein lv;

    /**
     * Konstruktor der LevenshteinHelper Klasse.
     * @param word1 Wort 1
     * @param word2 Wort 2
     */
    public LevenshteinHelper(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        this.lv = new Levenshtein(word1, word2);
    }

    /**
     * Liefert normalisierte Levenshtein Distanz.
     * @return normalisierte Levenshtein Distanz.
     */
    public double getNormalizedLevenshteinDistance() {
        return this.lv.getDistance() / Math.max(this.word1.length(), this.word2.length());
    }
}