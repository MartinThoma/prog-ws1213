package wordprocessor;

/**
 * Models the user interface of the word processor.
 *
 * @author Markus Iser, Florian Merz
 * @version 1.3
 */
public class WordProcessor {
	/** The Document opened in this WordProcessor. */
	private Document document;

	/** The DocumentCursor used internally. */
	private DocumentCursor documentCursor;

	/** True if shift is pressed, false otherwise. */
	private boolean isInSelectionMode;

	/** Absolute position of the start of the selection. */
	private int selectionOffset;

	/**
	 * Creates a new instance of the word processor interface.
	 * Creates a new document and documentCursor.
	 */
	public WordProcessor() {
		document = new Document();
		documentCursor = document.newCursorAt(0);
		isInSelectionMode = false;
		selectionOffset = 0;
	}

	/**
	 * Moves the cursor one character to the right.
	 * If selectionMode is active decrements the selectionOffset, 
	 * otherwise resets the selectionOffset.
	 */
	public void moveRight() {
		if (documentCursor.hasRight()) {
			documentCursor.moveRight();
			
			if (isInSelectionMode) {
				selectionOffset--;
			} else {
				selectionOffset = 0;
			}
		}
	}

	/**
	 * Moves the cursor n characters to the right.
	 *
	 * Stops automatically if at the end of the document.
	 *
	 * @param n The number of characters to move right.
	 */
	public void moveRight(int n) {
		for (int i = 0; i < n; ++i) {
			moveRight();
		}
	}

	/**
	 * Moves the cursor one character to the left.
	 * If selectionMode is active increments the selectionOffset, 
	 * otherwise resets the selectionOffset.
	 */
	public void moveLeft() {
		if (documentCursor.hasLeft()) {
			documentCursor.moveLeft();
			
			if (isInSelectionMode) {
				selectionOffset++;
			} else {
				selectionOffset = 0;
			}
		}
	}

	/**
	 * Moves the cursor n characters to the left.
	 *
	 * Stops automatically if at the start of the document.
	 *
	 * @param n The number of characters to move left.
	 */
	public void moveLeft(int n) {
		for (int i = 0; i < n; ++i) {
			moveLeft();
		}
	}

	/**
	 * Makes the WordProcessor enter selection mode.
	 *
	 * This corresponds to pressing down the shift key.
	 */
	public void pressShift() {
		isInSelectionMode = true;
	}

	/**
	 * Makes the WordProcessor leave selection mode.
	 *
	 * This corresponds to releasing the shift key.
	 */
	public void releaseShift() {
		isInSelectionMode = false;
	}

	/**
	 * Inserts the string into the document at the cursors position.
	 * If there is a selection the selected text is deleted first. 
	 *
	 * @param string The string to be inserted.
	 */
	public void insert(String string) {
		if (selectionOffset != 0) {
			delete();
		}

		documentCursor.insert(string);
	}

	/**
	 * Deletes the next character to the right of the cursor. 
	 * In Selection-Mode deletes the selected text.
	 *
	 * Does nothing if the cursor is at the end of the document.
	 */
	public void delete() {
		if (selectionOffset == 0) {
			if (documentCursor.hasRight()) {
				documentCursor.delete();
			}
		} else {
			while (selectionOffset < 0) {
				if (documentCursor.hasLeft()) {
					documentCursor.moveLeft();
					documentCursor.delete();
					selectionOffset++;
				} else {
					selectionOffset = 0;
				}
			}
			while (selectionOffset > 0) {
				if (documentCursor.hasRight()) {
					documentCursor.delete();
					selectionOffset--;
				} else {
					selectionOffset = 0;
				}
			}
		}
	}

	/**
	 * Deletes the next n charaters to the right of the cursor.
	 *
	 * If there are fewer characters to be deleted than n, it deletes all characters to the right.
	 *
	 * @param n The number of characters to delete.
	 */
	public void delete(int n) {
		for (int i = 0; i < n; ++i) {
			delete();
		}
	}

	/**
	 * Deletes the next character on the left of the cursor.
	 *
	 * Does nothing if the cursor is at the beginning of the document.
	 */
	public void backspace() {
		if (isInSelectionMode) {
			delete();
		} else {
			if (documentCursor.hasLeft()) {
				documentCursor.moveLeft();
				documentCursor.delete();
			}
		}
	}

	/**
	 * Deletes the next n characters to the left of the cursor.
	 *
	 * If there are fewer characters to be deleted than n, it deletes all characters to the left.
	 *
	 * @param n The number of characters to delete.
	 */
	public void backspace(int n) {
		for (int i = 0; i < n; ++i) {
			backspace();
		}
	}

	/**
	 * Makes the current selection bold.
	 */
	public void setBold() {
		applyStyleChangeInRange(StyleChange.SET_BOLD);
	}

	/**
	 * Makes the current selection non-bold.
	 */
	public void unsetBold() {
		applyStyleChangeInRange(StyleChange.UNSET_BOLD);
	}

	/**
	 * Makes the current selection italic.
	 */
	public void setItalic() {
		applyStyleChangeInRange(StyleChange.SET_ITALIC);
	}

	/**
	 * Makes the current selection non-italic.
	 */
	public void unsetItalic() {
		applyStyleChangeInRange(StyleChange.UNSET_ITALIC);
	}
	
	/**
	 * Applies a style-change to the currently selected text. 
	 *
	 * This invalidates all Cursors on the Document.
	 *
	 * @param styleChange The change in formating to be applied to the range.
	 */
	protected void applyStyleChangeInRange(StyleChange styleChange) {
		int cursorPosition = documentCursor.getPosition(); 
		int start = cursorPosition;
		int end = cursorPosition;
		
		// determine selection start and end position
		if (selectionOffset == 0) {
			return;
		} else if (selectionOffset < 0) {
			start += selectionOffset;
		} else {
			end += selectionOffset;
		}
		
		// create cursors to first and last fragment, split fragments if necessary
		DocumentCursor selectionStart = document.newCursorAt(start);
		selectionStart.split();
		DocumentCursor selectionEnd = document.newCursorAt(end);
		selectionEnd.split();
		
		// apply style-change to specified range
		DocumentCursor.applyStyleChange(styleChange, selectionStart, selectionEnd);
		
		// BugFix: Wanted Implementation omits last fragment in list
		if (!selectionEnd.hasRight()) {
			DocumentCursor.applyStyleChange(styleChange, selectionEnd, selectionEnd);
		}
		
		// create new valid cursor
		documentCursor = document.newCursorAt(cursorPosition);
	}

	/**
	 * @return the string representation of the document
	 */
	public String toString() {
		return document.toString();
	}

	/**
	 * @return the html representation of the document
	 */
	public String toHTML() {
		return document.toHTML();
	}

	/**
	 * Returns the distance in characters of the cursor to the beginning of the document.
	 *
	 * @return The cursors distance from the beginning of the document.
	 */
	protected int getCursorPosition() {
		return documentCursor.getPosition();
	}
}
