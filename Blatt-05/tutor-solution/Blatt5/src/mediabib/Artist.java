package mediabib;

/**
 * Klasse symbolisiert einen Künstler in der Medienbibliothek.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public class Artist extends Person implements Matchable {

    /** String für den Künstlernamen. */
    private final String artistName;

    /**
     * Konstruktor der Klasse Artist.
     * @param firstName Vorname des Künstlers
     * @param artistName Künstlername des Künstlers
     * @param lastName Nachname des Künstlers
     */
    public Artist(String firstName, String artistName, String lastName) {
        super(firstName, lastName);
        this.artistName = artistName;
    }

    /**
     * Liefert Künstlername des Künstlers.
     * @return Künstlername des Künstlers
     */
    public String getArtistName() {
        return this.artistName;
    }

    /**
     * Liefert String zu aktuellem Künstler Objekt.
     * @return String zu aktuellem Künstler Objekt
     */
    @Override
    public String toString() {
        return this.getFirstName() + " \"" + this.artistName + "\" " + this.getLastName();
    }

    /**
     * Klont das Objekt.
     * @return Klon des Objekts
     */
    @Override
    public Artist clone() {
        return new Artist(super.getFirstName(), this.artistName, super.getLastName());
    }

    /**
     * Vergleicht dieses mit dem übergebenen Objekt auf Gleichheit.
     * @param other Objekt der vergleicht werden soll.
     * @return Liefert true wenn die Objekte gleich sind.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other != null && other instanceof Artist) {
            Artist artist = (Artist) other;
            if (artist == this) {
                result = true;
            } else {
                result = artist.getFirstName() == this.getFirstName()
                        && artist.getLastName() == this.getLastName()
                        && artist.getArtistName() == this.artistName;
            }
        }
        return result;
    }

    /**
     * Liefert hash code zum Objekt.
     * @return hash code zum Objekt
     */
    @Override
    public int hashCode() {
        return super.hashCode() + artistName.hashCode();
    }

    /**
     * Liefert die Übereinstimmung des Objekts anhand der normalisierten Levenshtein Distanz zu einem
     * übergebenen String.
     * @param str Suchbegriff
     * @return true wenn String zum Objekt passt.
     */
    @Override
    public boolean match(String str) {
        return new LevenshteinHelper(this.artistName, str).getNormalizedLevenshteinDistance() < 0.25
                || super.match(str);
    }


}