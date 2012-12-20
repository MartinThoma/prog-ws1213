package wordprocessor;

/**
 * Models a text document. Stores a fragment-list and provides methods to output
 * the document. Can create a document-cursor to edit the document.
 *
 * @author KRK
 * @version 1.2
 */

public class Document {
    /** The FragmentList storing this Document's formatted text. */
    private final FragmentList fragments;

    /**
     * Creates a new Document, initialized with a single, empty Fragment of
     * text.
     */
    public Document() {
        fragments = new FragmentList();
        fragments.add(new Fragment(new Style(false, false)));
    }

    /**
     * Creates a new DocumentCursor at position <code>position</code> of this
     * Document.
     *
     * @param position The position the DocumentCursor is created at.
     * @return A new DocumentCursor at distance <code>position</code> to the
     *         start of the Document.
     */
    public DocumentCursor newCursorAt(int position) {
        return new DocumentCursor(fragments, position);
    }

    /**
     * Returns the number of characters in the document.
     *
     * @return The number of characters in the document.
     */
    public int length() {
        int output = 0;

        FragmentList.Cursor tempCursor = fragments.cursor();
        while (tempCursor.hasNext()) {
            tempCursor.hasNext();
            output += tempCursor.getFragment().length();
        }
        return output;
    }

    /**
     * Returns the text of the document as an unformatted String.
     *
     * @return The text of the document as an unformatted String.
     */
    @Override
    public String toString() {
        String output = "";

        FragmentList.Cursor tempCursor = fragments.cursor();
        output += tempCursor.getFragment().toString();
        while (tempCursor.hasNext()) {
            tempCursor.next();
            output += tempCursor.getFragment().toString();
        }
        return output;
    }

    /**
     * Returns the text of the document formatted as HTML code.
     *
     * @return The text of the document formatted as HTML code.
     */
    public String toHTML() {
        String output = "";

        FragmentList.Cursor tempCursor = fragments.cursor();
        output += tempCursor.getFragment().toHTML();
        while (tempCursor.hasNext()) {
            tempCursor.next();
            output += tempCursor.getFragment().toHTML();
        }
        return output;
    }
}
