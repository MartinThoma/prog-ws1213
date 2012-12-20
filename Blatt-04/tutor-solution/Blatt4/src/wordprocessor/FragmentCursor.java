package wordprocessor;

/**
 * Points between two characters in a Fragment. This is similar to an iterator,
 * but is slightly more powerful. E.g. it allows insertion and deletion of
 * characters next to it and it also allows splitting Fragments into two.
 *
 * @author anonymer Student und Tutoren
 * @version 1.2
 */
public class FragmentCursor {
    /** The Fragment this FragmentCursor works on. */
    private final Fragment fragment;

    /** The FragmentCursor's distance to the start of the document. */
    private int position;

    /**
     * Creates a new FragmentCursor pointing to the specified position in the
     * fragment. If n == Fragment.length() the cursor points after the last
     * character of the Fragment. Aborts if position is out of fragment bounds.
     *
     * @param fragment The Fragment the FragmentCursor will work on.
     * @param position The FragmentCursor's distance to the start of the
     *            Fragment.
     */
    public FragmentCursor(Fragment fragment, int position) {
        Helper.abortIf(fragment == null,
                        "NullPointerException: you have to give a fragment, not null");
        Helper.abortIf(position < 0 || fragment.length() < position,
                "position is out of fragment bounds");

        this.fragment = fragment;
        if (this.checkPosition(position)) {
            this.position = position;
        }
    }

    /**
     * Return True if the position is between 0 and Fragment length, abort and
     * false otherwise.
     *
     * @param position The position of the character
     * @return True if the position is between 0 and Fragment length, abort and
     *         false otherwise.
     */
    public boolean checkPosition(int position) {
        Helper.abortIf(!(position >= 0 && position <= this.fragment
                .length()), "position is out of string bounds");
        return true;
    }

    /**
     * Returns true iff there is at least one more character to the left of the
     * cursor's position.
     *
     * @return True iff there is at least one more character to the left of the
     *         cursor's position.
     */
    public boolean hasLeft() {
        return this.position > 0;
    }

    /**
     * Returns true iff there is at least one more character to the right of the
     * cursor's position.
     *
     * @return True iff there is at least one more character to the right of the
     *         cursor's position.
     */
    public boolean hasRight() {
        return this.position < this.fragment.length();
    }

    /**
     * Moves the cursor past the character to the left of the cursor. Aborts if
     * there is no character to the left of the cursor.
     */
    public void moveLeft() {
        if (this.checkPosition(this.position - 1)) {
            this.position--;
        }
    }

    /**
     * Moves the cursor past the character to the right of the cursor. Aborts if
     * there is no character to the right of the cursor.
     */
    public void moveRight() {
        if (this.checkPosition(this.position + 1)) {
            this.position++;
        }
    }

    /**
     * Returns the position of the cursor in the Fragment.
     *
     * @return The distance of the Cursor to the beginning of the Fragment.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Deletes the character on the right of the cursor. Aborts if there is no
     * character to delete. Invalidates all other cursors on this Fragment.
     */
    public void delete() {
        if (this.checkPosition(this.position + 1)) {
            this.fragment.deleteCharAt(this.position);
        }
    }

    /**
     * Inserts a string at the position right of the cursor. Moves the cursor
     * behind the last character of the inserted string. Invalidates all other
     * cursors on this Fragment.
     *
     * @param string The text to be inserted.
     */
    public void insert(String string) {
        this.fragment.insert(this.position, string);
        this.position += string.length();
    }

    /**
     * Splits the Fragment into two at the position of the cursor and returns
     * the newly generated right Fragment.
     *
     * This method extracts the substring at [position,length) from this
     * FragementCursor's Fragment and returns a new Fragment which contains that
     * substring and uses a copy of the old fragment's style.
     *
     * After executing this method, the Fragment this cursor points to is
     * shortened up to the position of the FragmentCursor, the cursor still
     * points to the position after the last character in the fragment.
     *
     * MUST NOT PRODUCE EMPTY FRAGMENTS: If the the current position is at the
     * beginning or end of the current fragment, a split would produce an empty
     * fragment. This is not allowed, so the caller must ensure that these
     * preconditions hold. The method checks if the preconditions are met and
     * aborts otherwise.
     *
     * @return The newly generated right Fragment.
     */
    public Fragment cutOff() {
        Helper
                .abortIf(
                        !checkPosition(this.position - 1)
                                || !checkPosition(this.position + 1),
                        "the current position is at the beginning or end of the current fragment");

        Fragment result = new Fragment(new Style(this.fragment
                .getStyle().isBold(), this.fragment.getStyle()
                .isItalic()), this.fragment.substring(this.position,
                this.fragment.length()));
        this.fragment.deleteSubstring(this.position, this.fragment
                .length());
        return result;
    }
}