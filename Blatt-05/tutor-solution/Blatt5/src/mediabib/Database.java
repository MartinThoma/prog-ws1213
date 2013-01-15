package mediabib;

import java.util.ArrayList;

/**
 * Klasse symbolisiert die Datenbank der Medienbibliothek.
 * @author Student(in) hat Basis geschrieben; verbessert von Tutor(en)
 * @version 1.0
 *
 */
public class Database {

    /** Liste aller Urheber. */
    private final ArrayList<Originator> originators;

    /** Liste aller Mediendateien. */
    private final ArrayList<MediaFile> mediaFiles;

    /**
     * Konstruktor der Database Klasse.
     */
    public Database() {
        this.originators = new ArrayList<Originator>();
        this.mediaFiles = new ArrayList<MediaFile>();
    }

    /**
     * Fügt übergebenen Urheber der Urheberlist hinzu.
     * @param originator Urheber
     */
    public void addOriginator(Originator originator) {
        if (originator != null && !this.originators.contains(originator)) {
            this.originators.add(originator);
        }
    }

    /**
     * Fügt übergebene Mediendatei der Mediendateiliste hinzu.
     * @param mediaFile Mediendatei
     */
    public void addMediaFile(MediaFile mediaFile) {
        if (mediaFile != null && !this.mediaFiles.contains(mediaFile)) {
            this.mediaFiles.add(mediaFile);
        }
    }

    /**
     * Liefert zum übergebenen Suchbegriff passende Urheberliste.
     * @param str Suchbegriff
     * @return Urheberliste
     */
    public ArrayList<Originator> searchOriginators(String str) {
        ArrayList<Originator> result = new ArrayList<Originator>();
        for (Originator originator : this.originators) {
            if (originator.match(str)) {
                result.add(originator);
            }
        }
        return result;
    }

    /**
     * Liefert zum übergebenen Suchbegriff passende Mediendateiliste.
     * @param str Suchbegriff
     * @return Mediendateiliste
     */
    public ArrayList<MediaFile> searchMediaFiles(String str) {
        ArrayList<MediaFile> result = new ArrayList<MediaFile>();
        for (MediaFile mediaFile : this.mediaFiles) {
            if (mediaFile.match(str)) {
                result.add(mediaFile);
            }
        }
        return result;
    }

    /**
     * Liefert zum übergebenen Suchbegriff passende Liste mit allen Elementen.
     * @param str Suchbegriff
     * @return Liste mit allen Elementen
     */
    public ArrayList<Object> search(String str) {
        ArrayList<Object> result = new ArrayList<Object>();
        result.addAll(searchOriginators(str));
        result.addAll(searchMediaFiles(str));
        return result;
    }

}