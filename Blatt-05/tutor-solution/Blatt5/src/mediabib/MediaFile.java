package mediabib;

/**
 * Abstrakte Klasse symbolisiert eine Mediendatei in der Medienbibliothek.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public abstract class MediaFile implements Matchable {

    /** Uri der Mediendatei. */
    private final String uri;

    /** Urheber der Mediendatei. */
    private final Originator originator;

    /**
     * Konstruktor der MediaFile Klasse.
     * @param uri Uri der Mediendatei
     * @param originator Urheber der Mediendatei
     */
    public MediaFile(String uri, Originator originator) {
        this.uri = uri;
        this.originator = originator;
    }

    /**
     * Liefert Uri der Mediendatei.
     * @return Uri der Mediendatei
     */
    public String getUri() {
        return this.uri;
    }

    /**
     * Liefert Urheber der Mediendatei.
     * @return Urheber der Mediendatei
     */
    public Originator getOriginator() {
        return this.originator;
    }

    /**
     * Liefert true wenn Suchbegriff zu Objekt passt.
     * @param str Suchbegriff
     * @return true wenn Suchbegriff zu Objekt passt
     */
    public boolean match(String str) {
        return new LevenshteinHelper(this.uri, str).getNormalizedLevenshteinDistance() < 0.25
                || this.originator.match(str);
    }

    /**
     * Liefert String zu aktuellem Mediendatei Objekt.
     * @return String zu aktuellem Mediendatei Objekt
     */
    @Override
    public String toString() {
        return this.getUri() + " - " + this.originator.toString();
    }

    /**
     * Liefert hash code zum Objekt.
     * @return hash code zum Objekt
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + this.uri.hashCode();
        result = result * 31 + this.originator.hashCode();
        return result;
    }
}