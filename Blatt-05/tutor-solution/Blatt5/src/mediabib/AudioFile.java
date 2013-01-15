package mediabib;

/**
 * Klasse symbolisiert eine Audio Datei in der Medienbibliothek.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public class AudioFile extends MediaFile {

    /** Länge der Audio Datei in Sekunden. */
    private final int seconds;

    /**
     * Konstruktor der Klasse AudioFile.
     * @param uri Uri der Audio Datei
     * @param originator Urheber der Audio Datei
     * @param seconds Länge der Audio Datei in Sekunden
     */
    public AudioFile(String uri, Originator originator, int seconds) {
        super(uri, originator);
        this.seconds = seconds;
    }

    /**
     * Liefert String zu Objekt.
     * @return String zu Objekt
     */
    @Override
    public String toString() {
        return this.getUri() + " by " + this.getOriginator().toString() + ", (" + this.seconds + "s)";
    }

    /**
     * Klont das Objekt.
     * @return Klon des Objekts
     */
    @Override
    public AudioFile clone() {
        return new AudioFile(this.getUri(), this.getOriginator(), this.seconds);
    }

    /**
     * Liefert hash code zum Objekt.
     * @return hash code zum Objekt
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + super.hashCode();
        result = result * 31 + this.seconds;
        return result;
    }

}