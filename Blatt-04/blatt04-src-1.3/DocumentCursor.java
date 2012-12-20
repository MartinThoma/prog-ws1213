package wordprocessor;

/**
 * Provides means to iterate over a Document without having to care for
 * how the document is split into Fragments. 
 *
 * @author  <insert your name here>
 * @version  1.2
 */

class DocumentCursor {
	/** The Fragment-List that this cursor navigates and edits. */
	private final FragmentList list;
	
	/** The Fragment this cursor currently points to. */
	private FragmentList.Cursor listCursor;
	
	/** The Cursor for the current fragment. */
	private FragmentCursor fragmentCursor;

	/**
	 * Allows for creation of new DocumentCursors at the beginning of documents.
	 * Should only be used by the Document class.
	 * 
	 * @param list The fragment-list actually holding the document data. 
	 */
	protected DocumentCursor(FragmentList list) {
	}

	/**
	 * Allows for creation of new DocumentCursors at arbitrary positions in the document.
	 * Should only be used by the Document class.
	 * 
	 * @param list The fragment-list actually holding the document data.
	 * @param absolutePosition The position the cursor should initially point at.  
	 */
	protected DocumentCursor(FragmentList list, int absolutePosition) {
	}
	
	/**
	 * Returns true if there is another character to the right of the cursor's position. 
	 * Uses the invariant, that there are no empty fragments in a non-empty document. 
	 * 
	 * @return true if there is another character to the right of the cursor's position, false otherwise.
	 */
	public boolean hasRight() {
	}

	/**
	 * Returns true if there is another character to the left of the cursor's position.
	 * Uses the invariant, that there are no empty fragments in a non-empty document. 
	 * 
	 * @return true if there is another character to the left of the cursor's position, false otherwise.
	 */
	public boolean hasLeft() {
	}

	/**
	 * Moves the fragment cursor over the right character. 
	 * If there is no right character within the fragment, moves the cursor to the next fragment 
	 * and over the first character of that fragment. 
	 * Aborts if the cursor is already right of the last character in the document. 
	 */
	public void moveRight() {
	}
	
	/**
	 * Moves the fragment cursor over the left character.
	 * If there is no left character within the fragment, moves the cursor to the previous fragment 
	 * and over the last character of that fragment.
	 * Aborts if the cursor is already left of the first character in the document.
	 */
	public void moveLeft() {
	}

	/**
	 * Returns the absolute position of the cursor in the document.
	 * 
	 * @return The absolute position of the cursor in the document.
	 */
	public int getPosition() {
	}

	/**
	 * Deletes the character to the right of the cursor.
	 * Afterwards calls tryRemoveEmptyFragment() to remove the current fragment if it's now empty,  
	 * and to ensure, that there are no empty fragments in a non-empty document.
	 * This invalidates all other cursors on this Document.
	 * Aborts if the cursor is right of the last character in the document.
	 */
	public void delete() {
	}
	
	/**
	 * Checks if the cursor points to an empty fragment 
	 * and removes it if it's not the last fragment in the list. 
	 */
	private void tryRemoveEmptyFragment() {
	}

	/**
	 * Inserts a string into the current fragment.
	 * This invalidates all other cursors on this Document.
	 * @param string The string to insert
	 */
	public void insert(String string) {
	}

	/**
	 * Splits the Fragment at the cursor's position into two Fragments.
	 *
	 * The substring right of the cursor is extracted from the Fragment and
	 * inserted as a new Fragment into the list of Fragments after the
	 * current Fragment.
	 * 
	 * The new Fragment has the same Style as the old Fragment.
	 * 
	 * Makes sure NOT to produce empty fragments. 
	 * If the cursor is at the start or end of a fragment, 
	 * the method just skips the split and returns. 
	 * 
	 * Makes sure that after a call to split the cursor always resides at 
	 * the FIRST position in a fragment and not at the end of a fragment, even when no split occurs. 
	 * The only exception to this rule is, when the cursor is at the end of the document.  
	 *
	 * This invalidates all other DocumentCursors on this Document.
	 */
	public void split() {
	}
	
	/**
	 * Moves the Cursor before the first character of the next fragment. 
	 * Aborts if there is no next fragment. 
	 */
	private void nextFragment() {
	}
	
	/**
	 * Moves the Cursor behind the last character of the previous fragment.
	 * Aborts if there is no previous fragment. 
	 */
	private void previousFragment() {
	}
	
	/**
	 * Applies the styleChange command to the selected fragments. 
	 * Changes the style of all fragments including the one pointed to by selectionStart 
	 * and not including selectionEnd. 
	 * Exception: If selectionStart and selectionEnd point to the same fragment, 
	 * the style of that fragment changes too. 
	 * Aborts if selectionStart.getPosition() > selectionEnd.getPosition()
	 * 
	 * @param styleChange The Style-Change Command
	 * @param selectionStart the first fragment of the selection that should change style
	 * @param selectionEnd the first fragment behind the selection that should not change style
	 */
	public static void applyStyleChange(
			StyleChange styleChange,
			DocumentCursor selectionStart,
			DocumentCursor selectionEnd) {
	}
}
