package mediabib;


/**
 * Klasse symbolisiert eine Person in der Medienbibliothek.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.1
 *
 */
public class Person extends Originator implements Matchable {
    private final String firstname;
    private final String lastname;

    /**
     * Konstruktor der Klasse Person.
     * @param firstname Vorname der Person
     * @param lastname Nachname der Person
     */
    public Person(String firstName, String lastName) {
        this.firstname = firstName;
        this.lastname = lastName;
    }

    /**
     * Liefert Vorname der Person.
     * @return Vorname der Person
     */
    public String getFirstName() {
        return this.firstname;
    }

    /**
     * Liefert Nachname der Person.
     * @return Nachname der Person
     */
    public String getLastName() {
        return this.lastname;
    }

    /**
     * Liefert String zum Personen Objekt.
     * @return String zum Personen Objekt
     */
    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }

    /**
     * Klont das Objekt.
     * @return Klon des Objekts
     */
    @Override
    public Person clone() {
        return new Person(this.firstname, this.lastname);
    }

    /**
     * Vergleicht dieses mit dem übergebenen Objekt auf Gleichheit.
     * @param other Objekt der vergleicht werden soll.
     * @return Liefert true wenn die Objekte gleich sind.
     */
    @Override
    public boolean equals(Object other) {
        boolean result = false;
        if (other instanceof Person) {
            Person person = (Person) other;

            if (person == this) {
                result = true;
            } else {
                result = person.getFirstName().equals(this.getFirstName())
                        && person.getLastName().equals(this.getLastName());
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
        return firstname.hashCode() + lastname.hashCode();
    }

    /**
     * Liefert die Übereinstimmung des Objekts anhand der normalisierten Levenshtein Distanz zu einem
     * übergebenen String.
     * @param str Suchbegriff
     * @return true wenn String zum Objekt passt.
     */
    @Override
    public boolean match(String str) {
        return new LevenshteinHelper(this.firstname,
                str).getNormalizedLevenshteinDistance() < 0.25
                || new LevenshteinHelper(this.lastname,
                        str).getNormalizedLevenshteinDistance() < 0.25
                || new LevenshteinHelper(this.toString(),
                        str).getNormalizedLevenshteinDistance() < 0.25;
    }
}