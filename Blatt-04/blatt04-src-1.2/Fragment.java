package wordprocessor;

/**
 * Models a string of characters with an associated style. 
 * Wraps a StringBuffer object containing the fragment's text. 
 * Provides methods to edit that text and to change the style. 
 *
 * @author  <insert your name here>
 * @version  1.2
 */
class Fragment {
	/** The StringBuffer containing the Fragment's text. */
	private final StringBuilder content;

	/** The Style according to which the text is formatted. */
	private final Style style;

	/**
	 * Creates a new, empty Fragment with the given style.
	 * Aborts if style is null.
	 *
	 * @param style The Style according to which the Fragment is formatted.
	 */
	public Fragment(Style style) {
	}

	/**
	 * Creates a new Fragment with the given text and style.
	 * Aborts if style is null or string is null. 
	 *
	 * @param style The Style according to which the Fragment will be formatted.
	 * @param string The new Fragment's text.
	 */
	public Fragment(Style style, String string) {
	}

	/**
	 * Returns the number of characters in the Fragment.
	 *
	 * @return The number of characters in the Fragment.
	 */
	public int length() {
	}

	/**
	 * Returns true if the Fragment is empty, false otherwise.
	 *
	 * @return True if the Fragment is empty, false otherwise.
	 */
	public boolean isEmpty() {
	}

	/**
	 * Returns the character at the position.
	 * Aborts if there is no character at the given position 
	 * (position is out of string bounds). 
	 *
	 * @param position The position of the character to be returned.
	 * @return The character at the specified position.
	 */
	public Character charAt(int position) {
	}

	/**
	 * Inserts the string at position into the Fragment.
	 *
	 * After insertion the first of the newly inserted characters is at
	 * position <code>position</code>.
	 * 
	 * Aborts if the given position is invalid (the string can not be inserted there).
	 * Aborts if string is null.  
	 *
	 * @param position The position to insert at.
	 * @param string The String to insert.
	 */
	public void insert(int position, String string) {
	}

	/**
	 * Deletes the character at the given position from the Fragment.
	 *
	 * This makes the Fragment one character shorter and moves all characters
	 * after <code>position</code> one position to the left.
	 * 
	 * Aborts if there is no character to be deleted at the given position.
	 *
	 * @param position The position of the character to be deleted.
	 */
	public void deleteCharAt(int position) {
	}

	/**
	 * Returns the Style associated with the Fragment.
	 *
	 * @return The Style associated with the Fragment.
	 */
	public Style getStyle() {
	}

	/**
	 * Returns a substring of the Fragment's text. 
	 * Aborts if one of the two parameters is out of the string's bounds. 
	 * The character at position start is included in the substring,
	 * the character at position end is not included in the substring.
	 * 
	 * @param start inclusive
	 * @param end exclusive
	 * @return The substring at [start,end).
	 */
	public String substring(int start, int end) {
	}

	/**
	 * Deletes the substring between start and end of the fragment's text.
	 * Aborts if one of the two parameters is out of the string's bounds.
	 * The character at position start is included in the substring,
	 * the character at position end is not included in the substring.
	 * 
	 * @param start Inclusive start of the substring.
	 * @param end Exclusive end of the substring.
	 */
	public void deleteSubstring(int start, int end) {
	}

	/**
	 * Returns the Fragment's text as a String.
	 *
	 * @return The Fragment's text as a String.
	 */
	public String toString() {
	}

	/**
	 * Returns the Fragment's text as formatted HTML code.
	 * If the Fragment does not contain any characters this method does not add
	 * HTML either, and therefore returns an empty String.
	 *
	 * @return The Fragment's text as formatted HTML code.
	 */
	public String toHTML() {
	}

}
