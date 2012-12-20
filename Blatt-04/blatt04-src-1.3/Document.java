package wordprocessor;

/**
 * Models a text document. 
 * Stores a fragment-list and provides methods to output the document. 
 * Can create a document-cursor to edit the document. 
 *
 * @author  <insert your name here>
 * @version  1.2
 */

public class Document {
	/** The FragmentList storing this Document's formatted text. */
	private final FragmentList fragments;

	/** Creates a new Document, initialized with a single, empty Fragment of text. */	
	public Document() {
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
	}

	/**
	 * Returns the number of characters in the document.
	 *
	 * @return The number of characters in the document.
	 */	
	public int length() {
	}

	/**
	 * Returns the text of the document as an unformatted String.
	 *
	 * @return The text of the document as an unformatted String.
	 */
	public String toString() {
	}

	/**
	 * Returns the text of the document formatted as HTML code.
	 *
	 * @return The text of the document formatted as HTML code.
	 */
	public String toHTML() {
	}
}
