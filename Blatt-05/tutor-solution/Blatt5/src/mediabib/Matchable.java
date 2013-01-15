package mediabib;

/**
 * Interface Matchable gibt eine Schnittstelle zum Vergleichen eines Objekts mit einem String vor.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public interface Matchable {

    /**
     * Match Methode die Vergleich ermöglichen soll.
     * @param str Suchbegriff
     * @return true wenn Suchbegriff passend zum Objekt
     */
    boolean match(String str);

}