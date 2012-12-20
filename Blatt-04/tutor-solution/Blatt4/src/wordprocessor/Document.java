package wordprocessor;


/**
 * Models a text document.
 * Stores a fragment-list and provides methods to output the document.
 * Can create a document-cursor to edit the document.
 *
 * @author  anonymer Student und Tutoren
 * @version  1.2
 */

public class Document {
    /** The FragmentList storing this Document's formatted text. */
    private final FragmentList fragments;

    /** Creates a new Document, initialized with a single, empty Fragment of text. */
    public Document() {
        this.fragments = new FragmentList();
        this.fragments.add(new Fragment(new Style(false, false)));
    }

    /**
     * Creates a new DocumentCursor at position <code>position</code> of this
     * Document.
     *
     * @param position The position the DocumentCursor is created at.
     * @return A new DocumentCursor at distance <code>position</code> to the
     * start of the Document.
     */
    public DocumentCursor newCursorAt(int position) {
        return new DocumentCursor(this.fragments, position);
    }

    /**
     * Returns the number of characters in the document.
     *
     * @return The number of characters in the document.
     */
    public int length() {
        int result = 0;
        Fragment nextFragment = this.fragments.cursor().getFragment();
        result += nextFragment.length();
        while (this.fragments.cursor().hasNext()) {
            this.fragments.cursor().next();
            nextFragment = this.fragments.cursor().getFragment();
            result += nextFragment.length();
        }
        return result;
    }

    /**
     * Returns the text of the document as an unformatted String.
     *
     * @return The text of the document as an unformatted String.
     */
    @Override
    public String toString() {
        String result = "";
        FragmentList.Cursor cursor = this.fragments.cursor();
        Fragment nextFragment = cursor.getFragment();
        result += nextFragment.toString();
        while (cursor.hasNext()) {
            cursor.next();
            nextFragment = cursor.getFragment();
            result += nextFragment.toString();
        }
        return result;
    }

    /**
     * Returns the text of the document formatted as HTML code.
     *
     * @return The text of the document formatted as HTML code.
     */
    public String toHTML() {
        String result = "";
        FragmentList.Cursor cursor = this.fragments.cursor();
        Fragment nextFragment = cursor.getFragment();
        result += nextFragment.toHTML();
        while (cursor.hasNext()) {
            cursor.next();
            nextFragment = cursor.getFragment();
            result += nextFragment.toHTML();
        }
        return result;
    }
}
