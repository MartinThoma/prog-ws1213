package mediabib;

import java.util.ArrayList;
import java.util.List;

import programmieren.MyTerminal;

/**
 * Klasse MediaBib enthält die Main-Methode und die Benutzer-Schnittstelle.
 *
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.1
 *
 */
public final class MediaBib {

    /** Statisches Datenbank Objekt. */
    private static Database database = new Database();


    /**
     * Konstruktor der Klasse MediaBib.
     */
    private MediaBib() { }

    /**
     * Main-Methode der MedienBibliothek.
     * @param args Argumente
     */
    public static void main(String[] args) {
        String command = "";
        while (!command.equals("quit")) {
            command = MyTerminal.askString("Enter command (add|search|quit)> ");

            switch (Commands.valueOf(command.toUpperCase())) {
            case ADD:
                add();
                break;
            case SEARCH:
                search();
                break;
            case QUIT:
                break;
            default:
                System.out.println("Invalid input");
                break;
            }
        }
    }

    /**
     * Aufruf der Suchfunktionalität.
     */
    private static void search() {
        String searchTerm = MyTerminal.askString("Enter search term> ");
        List<Object> resultObjects = database.search(searchTerm);
        if (resultObjects.size() > 0) {
            List<String> resultList = new ArrayList<String>();
            for (Object obj : resultObjects) {
                String result = "";
                if (obj instanceof Person) {
                    Person person = (Person) obj;
                    result = person.toString();
                } else if (obj instanceof Artist) {
                    Artist artist = (Artist) obj;
                    result = artist.toString();
                } else if (obj instanceof Band) {
                    Band band = (Band) obj;
                    result = band.toString();
                } else if (obj instanceof AudioFile) {
                    AudioFile audioFile = (AudioFile) obj;
                    result = audioFile.toString();
                } else if (obj instanceof VideoFile) {
                    VideoFile videoFile = (VideoFile) obj;
                    result = videoFile.toString();
                }
                if (!resultList.contains(result)) {
                    resultList.add(result);
                }
            }
            java.util.Collections.sort(resultList);

            for (String str : resultList) {
                System.out.println(str);
            }
        } else {
            System.out.println("No matches found");
        }
    }

    /**
     * Konsolenaus- und Eingabe für das Hinzufügen von Elementen.
     */
    private static void add() {
        String command = MyTerminal
                .askString("Enter entry type (person|artist|band|audio|video)> ");

        switch (Commands.valueOf(command.toUpperCase())) {
        case PERSON:
            addPerson();
            break;
        case ARTIST:
            addArtist();
            break;
        case BAND:
            addBand();
            break;
        case AUDIO:
            addAudio();
            break;
        case VIDEO:
            addVideo();
            break;
        default:
            System.out.println("Invalid input");
            break;
        }
    }

    /**
     * Konsolenaus- und Eingabe für das Hinzufügen von Personen.
     */
    private static void addPerson() {
        String firstName = MyTerminal.askString("Enter given name> ");
        String lastName = MyTerminal.askString("Enter family name> ");

        database.addOriginator(new Person(firstName, lastName));
    }

    /**
     * Konsolenaus- und Eingabe für das Hinzufügen von Künstlern.
     */
    private static void addArtist() {
        String firstName = MyTerminal.askString("Enter given name> ");
        String lastName = MyTerminal.askString("Enter family name> ");
        String pseudonym = MyTerminal.askString("Enter pseudonym> ");

        database.addOriginator(new Artist(firstName, pseudonym,
                lastName));
    }

    /**
     * Konsolenaus- und Eingabe für das Hinzufügen von Bands.
     */
    private static void addBand() {
        String bandName = MyTerminal.askString("Enter band name> ");
        String memberName = "";
        List<Originator> memberList = new ArrayList<Originator>();
        while (!memberName.equals("quit")) {
            memberName = MyTerminal.askString("Enter member or 'quit'> ");
            if (memberName.equals("quit")) {
                continue;
            }
            List<Originator> searchResult = database
                    .searchOriginators(memberName);

            if (searchResult.size() == 1) {
                memberList.add(searchResult.get(0));
            } else {
                System.out.println("No unique match found");
            }
        }

        database.addOriginator(new Band(bandName, memberList));
    }

    /**
     * Konsolenaus- und Eingabe für das Hinzufügen von Audiowerken.
     */
    private static void addAudio() {
        String uri = MyTerminal.askString("Enter URI> ");
        String originatorName = MyTerminal.askString("Enter creator> ");
        List<Originator> originatorList = database
                .searchOriginators(originatorName);
        if (originatorList.size() == 1) {
            int seconds = MyTerminal.askInt("Enter duration (as integer)> ");
            database.addMediaFile(new AudioFile(uri, originatorList
                    .get(0), seconds));
        } else {
            System.out.println("No unique match found");
        }
    }

    /**
     * Konsolenaus- und Eingabe für das Hinzufügen von Videowerken.
     */
    private static void addVideo() {
        String uri = MyTerminal.askString("Enter URI> ");
        String originatorName = MyTerminal.askString("Enter creator> ");
        List<Originator> originatorList = database
                .searchOriginators(originatorName);
        if (originatorList.size() == 1) {
            int width = MyTerminal.askInt("Enter width (as integer)> ");
            int height = MyTerminal.askInt("Enter height (as integer)> ");
            database.addMediaFile(new VideoFile(uri, originatorList
                    .get(0), width, height));
        } else {
            System.out.println("No unique match found");
        }
    }

}