package mediabib;

/**
 * Klasse symbolisiert eine Video Datei in der Medienbibliothek.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public class VideoFile extends MediaFile {

    /** Breite der Video Datei. */
    private final int width;

    /** Höhe der Video Datei. */
    private final int height;

    /**
     * Konstruktor der Klasse VideoFile.
     * @param uri Uri der Video Datei
     * @param originator Urheber der Video Datei
     * @param width Breite der Video Datei
     * @param height Höhe der Video Datei
     */
    public VideoFile(String uri, Originator originator, int width, int height) {
        super(uri, originator);
        this.width = width;
        this.height = height;
    }

    /**
     * Liefert String zu Objekt.
     * @return String zu Objekt
     */
    @Override
    public String toString() {
        return this.getUri() + " by " + this.getOriginator().toString() + ", ("
                + this.width + "x" + this.height + ")";
    }

    /**
     * Klont das Objekt.
     * @return Klon des Objekts
     */
    @Override
    public VideoFile clone() {
        return new VideoFile(this.getUri(), this.getOriginator(), this.width, this.height);
    }

    /**
     * Liefert hash code zum Objekt.
     * @return hash code zum Objekt
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + super.hashCode();
        result = result * 31 + this.width;
        result = result * 31 + this.height;
        return result;
    }

}