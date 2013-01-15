package mediabib;

import java.util.ArrayList;

/**
 * Klasse symbolisiert eine Band in der Medienbibliothek.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public class Band extends Originator implements Matchable {

    /** String für Bandname des Objekts. */
    private final String bandName;

    /** Liste aller Bandmitglieder. */
    private final ArrayList<Originator> members;

    /**
     * Konstruktor der Klasse Band.
     * @param bandName Bandname der Band
     * @param members Mitgliederliste der Band
     */
    public Band(String bandName, ArrayList<Originator> members) {
        this.bandName = bandName;
        this.members = members;
    }

    /**
     * Liefert Bandname der Band.
     * @return Bandname der Band
     */
    public String getBandName() {
        return this.bandName;
    }

    /**
     * Klont Band Objekt.
     * @return Band Objekt
     */
    @Override
    public Band clone() {
        return new Band(this.bandName, this.members);
    }

    /**
     * Liefert Mitgliederliste der Band.
     * @return Mitgliederliste der Band
     */
    public ArrayList<Originator> getMembers() {
        return this.members;
    }

    /**
     * Vergleicht zwei Band Objekte auf Gleichheit.
     * @param other Objekt der vergleicht werden soll.
     * @return true wenn Bandobjekte identisch
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Band) {
            Band band = (Band) other;
            if (band == this) {
                result = true;
            } else {
                result = band.getBandName() == this.bandName
                        && band.getMembers().size() == this.members.size();
            }
        }
        return result;
    }

    /**
     * Liefert String zu Bandobjekt.
     * @return String zu Bandobjekt
     */
    @Override
    public String toString() {
        String result = this.bandName + " (";

        for (Originator memberObj : this.members) {
            if (memberObj instanceof Artist) {
                Artist memberArt = (Artist) memberObj;
                result += memberArt.toString();
            } else if (memberObj instanceof Person) {
                Person memberPer = (Person) memberObj;
                result += memberPer.toString();
            }
            result += ", ";
        }
        return result.substring(0, result.length() - 2) + ")";
    }

    /**
     * Liefert die Übereinstimmung des Objekts anhand der normalisierten Levenshtein Distanz zu einem
     * übergebenen String.
     * @param str Suchbegriff
     * @return true wenn String zum Objekt passt.
     */
    @Override
    public boolean match(String str) {
        boolean result = new LevenshteinHelper(this.bandName, str).getNormalizedLevenshteinDistance() < 0.25;
        if (!result) {
            for (Originator memberObj : this.members) {
                if (memberObj instanceof Artist) {
                    Artist memberArtist = (Artist) memberObj;
                    result = memberArtist.match(str);
                } else if (memberObj instanceof Person) {
                    Person memberPerson = (Person) memberObj;
                    result = memberPerson.match(str);
                }
                if (result) break;
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
        int result = 1;
        result = result * 31 + this.bandName.hashCode();
        result = result * 31 + this.members.size();
        return result;
    }
}