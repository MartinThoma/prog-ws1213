package wordprocessor;


/**
 * Provides means to iterate over a Document without having to care for
 * how the document is split into Fragments.
 *
 * @author  anonymer Student und Tutoren
 * @version  1.2
 */

class DocumentCursor {
    /** The Fragment-List that this cursor navigates and edits. */
    private final FragmentList list;

    /** The Fragment this cursor currently points to. */
    private final FragmentList.Cursor listCursor;

    /** The Cursor for the current fragment. */
    private FragmentCursor fragmentCursor;

    /**
     * Allows for creation of new DocumentCursors at the beginning of documents.
     * Should only be used by the Document class.
     *
     * @param list The fragment-list actually holding the document data.
     */
    protected DocumentCursor(FragmentList list) {
        this.list = list;
        this.listCursor = this.list.cursor();
        this.fragmentCursor = new FragmentCursor(this.listCursor.getFragment(), 0);
    }

    /**
     * Allows for creation of new DocumentCursors at arbitrary positions in the document.
     * Should only be used by the Document class.
     *
     * @param list The fragment-list actually holding the document data.
     * @param absolutePosition The position the cursor should initially point at.
     */
    protected DocumentCursor(FragmentList list, int absolutePosition) {
        this.list = list;
        this.listCursor = this.list.cursor();
        if (this.listCursor.getFragment().length() < absolutePosition) {
            absolutePosition -= this.listCursor.getFragment().length();
            while (absolutePosition > this.listCursor.next().length()) {
                absolutePosition -= this.listCursor.getFragment().length();
            }
        }
        this.fragmentCursor = new FragmentCursor(this.listCursor.getFragment(), absolutePosition);
    }

    /**
     * Returns true if there is another character to the right of the cursor's position.
     * Uses the invariant, that there are no empty fragments in a non-empty document.
     *
     * @return true if there is another character to the right of the cursor's position, false otherwise.
     */
    public boolean hasRight() {
        return this.fragmentCursor.hasRight() || this.listCursor.hasNext();
    }

    /**
     * Returns true if there is another character to the left of the cursor's position.
     * Uses the invariant, that there are no empty fragments in a non-empty document.
     *
     * @return true if there is another character to the left of the cursor's position, false otherwise.
     */
    public boolean hasLeft() {
        return this.fragmentCursor.hasLeft() || this.listCursor.hasPrevious();
    }

    /**
     * Moves the fragment cursor over the right character.
     * If there is no right character within the fragment, moves the cursor to the next fragment
     * and over the first character of that fragment.
     * Aborts if the cursor is already right of the last character in the document.
     */
    public void moveRight() {
        if (this.fragmentCursor.hasRight()) {
            this.fragmentCursor.moveRight();
        } else if (this.listCursor.hasNext()) {
            this.fragmentCursor = new FragmentCursor(this.listCursor.next(), 1);
        } else {
            Helper.abortIf(true, "the cursor is already right of the last character in the document.");
        }
    }

    /**
     * Moves the fragment cursor over the left character.
     * If there is no left character within the fragment, moves the cursor to the previous fragment
     * and over the last character of that fragment.
     * Aborts if the cursor is already left of the first character in the document.
     */
    public void moveLeft() {
        if (this.fragmentCursor.hasLeft()) {
            this.fragmentCursor.moveLeft();
        } else if (this.listCursor.hasPrevious()) {
            Fragment leftFragment = this.listCursor.previous();
            this.fragmentCursor = new FragmentCursor(leftFragment, leftFragment.length() - 1);
        } else {
            Helper.abortIf(true, "the cursor is already left of the first character in the document.");
        }
    }

    /**
     * Returns the absolute position of the cursor in the document.
     *
     * @return The absolute position of the cursor in the document.
     */
    public int getPosition() {
        int result = this.fragmentCursor.getPosition();

        Fragment elem = this.list.getFirstElement();
        FragmentList.Cursor positionCursor = this.list.cursor();
        while (this.listCursor.getFragment() != elem) {
            result += elem.length();
            elem = positionCursor.next();
        }

        return result;
    }

    /**
     * Deletes the character to the right of the cursor.
     * Afterwards calls tryRemoveEmptyFragment() to remove the current fragment if it's now empty,
     * and to ensure, that there are no empty fragments in a non-empty document.
     * This invalidates all other cursors on this Document.
     * Aborts if the cursor is right of the last character in the document.
     */
    public void delete() {
        if (!this.fragmentCursor.hasRight()) {
            if (this.listCursor.hasNext()) {
                this.fragmentCursor = new FragmentCursor(this.listCursor.next(), 0);
            } else {
                Helper.abortIf(true, "the cursor is right of the last character in the document");
            }
        }
        this.fragmentCursor.delete();
        this.tryRemoveEmptyFragment();
    }

    /**
     * Checks if the cursor points to an empty fragment
     * and removes it if it's not the last fragment in the list.
     */
    private void tryRemoveEmptyFragment() {
        if (this.listCursor.getFragment().isEmpty()) {
            if (this.listCursor.hasPrevious()) {
                this.listCursor.previous();
                this.fragmentCursor = new FragmentCursor(this.listCursor.getFragment(),
                        this.listCursor.getFragment().length());
                this.listCursor.deleteNext();
            } else if (this.listCursor.hasNext()) {
                this.listCursor.next();
                this.listCursor.deletePrevious();
            }
        }

    }

    /**
     * Inserts a string into the current fragment.
     * This invalidates all other cursors on this Document.
     * @param string The string to insert
     */
    public void insert(String string) {
        this.fragmentCursor.insert(string);
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
        if (!this.fragmentCursor.hasLeft() || !this.fragmentCursor.hasRight()) {
            return;
        }
        Fragment newFragment = this.fragmentCursor.cutOff();
        this.listCursor.insertAfter(newFragment);
        this.listCursor.next();
        this.fragmentCursor = new FragmentCursor(newFragment, 0);
    }

    /**
     * Moves the Cursor before the first character of the next fragment.
     * Aborts if there is no next fragment.
     */
    private void nextFragment() {
        Helper.abortIf(!this.listCursor.hasNext(), "there is no next fragment");

        this.fragmentCursor = new FragmentCursor(this.listCursor.next(), 0);
    }

    /**
     * Moves the Cursor behind the last character of the previous fragment.
     * Aborts if there is no previous fragment.
     */
    private void previousFragment() {
        Helper.abortIf(!this.listCursor.hasPrevious(), "there is no previous fragment");

        this.fragmentCursor = new FragmentCursor(this.listCursor.previous(), this.listCursor.previous().length());
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

            Helper.abortIf(selectionStart.getPosition() > selectionEnd.getPosition(),
                    "selection start position > selection end position");

            Fragment firstFragment = selectionStart.listCursor.getFragment();
            Fragment endFragment = selectionEnd.listCursor.getFragment();


            FragmentList.Cursor cursor = selectionStart.list.cursor();
            boolean setStyle = false;
            boolean setEndFragment = selectionEnd.listCursor.getFragment().length() == selectionEnd.fragmentCursor.
                    getPosition();
            if (firstFragment != endFragment && cursor.hasNext()) {
                while (cursor.hasNext()) {
                    if (cursor.getFragment() == firstFragment) {
                        setStyle = true;
                    } else if (cursor.getFragment() == endFragment) {
                        setStyle = false;
                        if (setEndFragment) {
                            cursor.getFragment().getStyle().apply(styleChange);
                        }
                    }
                    if (setStyle) {
                        cursor.getFragment().getStyle().apply(styleChange);
                    }
                    cursor.next();

                }
            } else {
                firstFragment.getStyle().apply(styleChange);
            }

    }
}
