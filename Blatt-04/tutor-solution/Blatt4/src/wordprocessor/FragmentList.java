package wordprocessor;


/**
 * A doubly linked list for storing Fragments.
 *
 * @author  anonymer Student und Tutoren
 * @version  1.2
 */
public class FragmentList {
    /** The first Element in the list. */
    private Element firstElement;

    /** The last Element in the list. */
    private Element lastElement;

    /**
     * Creates a new, empty Fragment List.
     */
    public FragmentList() {
    }

    /**
     * Returns the first Element in the FragmentList.
     *
     * @return the first Element in the FragmentList, or null if the list is
     * empty.
     */
    public Fragment getFirstElement() {
        return this.firstElement.getFragment();
    }

    /**
     * Returns the last Element in the FragmentList.
     *
     * @return the first Element in the FragmentList, or null if the list is
     * empty.
     */
    public Fragment getLastElement() {
        return this.lastElement.getFragment();
    }

    /**
     * Appends a Fragment at the end of the list.
     *
     * If the list is empty, the new element is the first and last element.
     * If the list is not empty, the previously last element's successor is the new element,
     * the new element's predecessor is the previously last element, and
     * the new element is the list's last element.
     *
     * @param fragment The Fragment to be appended to the list.
     */
    public void add(Fragment fragment) {
        if (this.isEmpty()) {
            Element newElement =  new Element(fragment, null, null);
            this.firstElement = newElement;
            this.lastElement = newElement;
        } else {
            Element newElement =  new Element(fragment, this.lastElement, null);
            this.lastElement.next = newElement;
            this.lastElement = newElement;
        }
    }

    /**
     * Returns a new FragmentList.Cursor pointing to the first element in the list.
     * The List must contain at least one element otherwise a call to cursor is not allowed.
     * Aborts if the list is empty.
     *
     * @return A new FragmentList.Cursor pointing to the first element in the list
     */
    public Cursor cursor() {
        Helper.abortIf(this.isEmpty(), "the list is empty");

        return new Cursor(this);
    }

    /**
     * Returns true if there are no Fragments in the list, false otherwise.
     *
     * @return true if there are no Fragments in the list, false otherwise.
     */
    public boolean isEmpty() {
        return this.firstElement == null;
    }

    /**
     * Returns the number of Fragments in the list.
     *
     * Note, that this does not return
     * the total number of characters.
     *
     * @return The number of Fragments in the list.
     */
    public int size() {
        int result = 0;
        Element nextElement = this.firstElement;
        while (nextElement != null) {
            result++;
            nextElement = nextElement.getNext();
        }
        return result;
    }

    /**
     * A cursor to allow navigation and modification of a fragment-list.
     *
     * @author  <insert your name here>
     * @version  1.2
     */
    public class Cursor {
        /** The fragment-list this cursor navigates. */
        private final FragmentList list;

        /** The current fragment this cursor points at. */
        private Element currentElement;

        /**
         * Creates a new cursor pointing to the first element of the given list.
         *
         * @param list The list that cursor navigates on
         */
        public Cursor(FragmentList list) {
            this.list = list;
            this.currentElement = list.firstElement;
        }

        /**
         * Returns the current fragment.
         *
         * @return The current fragment the cursor points at.
         */
        public Fragment getFragment() {
            return this.currentElement.getFragment();
        }

        /**
         * Insert the Fragment into the FragmentList right after the current fragment.
         *
         * @param fragment The Fragment to be inserted.
         */
        public void insertAfter(Fragment fragment) {
            Element newElement = new Element(fragment, this.currentElement, this.currentElement.getNext());
            if (this.currentElement.getNext() != null) {
                this.currentElement.getNext().setPrevious(newElement);
            } else {
                this.list.lastElement = newElement;
            }
            this.currentElement.setNext(newElement);
        }

        /**
         * Insert the Fragment into the FragmentList right before the current fragment.
         *
         * @param fragment The Fragment to be inserted.
         */
        public void insertBefore(Fragment fragment) {
            Element newElement = new Element(fragment, this.currentElement.getPrevious(), this.currentElement);
            if (this.currentElement.getPrevious() != null) {
                this.currentElement.getPrevious().setNext(newElement);
            }
            this.currentElement.setPrevious(newElement);
        }

        /**
         * Deletes the Fragment right after the current Element.
         * Aborts if the cursor points at the last element.
         */
        public void deleteNext() {
            Helper.abortIf(!this.hasNext(), "the cursor points at the last element");
            Element element = this.currentElement.getNext().getNext();
            if (element != null) {
                element.setPrevious(this.currentElement);
                this.currentElement.setNext(element);
            } else {
                this.currentElement.setNext(null);
            }
        }

        /**
         * Deletes the Fragment right before the current Element.
         * Aborts if the cursor points at the first element.
         */
        public void deletePrevious() {
            Helper.abortIf(!this.hasPrevious(), "the cursor points at the first element");
            Element element = this.currentElement.getPrevious().getPrevious();
            if (element != null) {
                element.setNext(this.currentElement);
                this.currentElement.setPrevious(element);
            } else {
                this.currentElement.setPrevious(null);
            }
        }

        /**
         * Returns true if there is another Fragment in the list after the current fragment.
         *
         * @return True if there is another Fragment in the list after the current fragment.
         */
        public boolean hasNext() {
            return this.currentElement.getNext() != null;
        }

        /**
         * Returns true if there is another Fragment in the list before the current fragment.
         *
         * @return True if there is another Fragment in the list before the current fragment.
         */
        public boolean hasPrevious() {
            return this.currentElement.getPrevious() != null;
        }

        /**
         * Returns the next element in the list and moves the cursor to the next fragment.
         * Aborts if there is no next fragment.
         *
         * @return This Element's successor Element.
         */
        public Fragment next() {
            Helper.abortIf(!this.hasNext(), "the cursor has no next element");

            this.currentElement = this.currentElement.getNext();
            return this.currentElement.getFragment();
        }

        /**
         * Returns the previous element in the list and moves the cursor to the previous fragment.
         * Aborts if there is no previous fragment.
         *
         * @return This Element's predecessor Element.
         */
        public Fragment previous() {
            Helper.abortIf(!this.hasPrevious(), "the cursor has no previous element");

            this.currentElement = this.currentElement.getPrevious();
            return this.currentElement.getFragment();
        }
    }

    /**
     * Wraps a fragment for insertion to a fragment-list.
     */
    private class Element {
        /**
         * This Element's predecessor Element.
         * This is null if the Element is the first in the list.
         */
        private Element prev = null;

        /**
         * This Element's successor Element.
         * This is null if the Element is the last in the list.
         */
        private Element next = null;

        /** The Fragment stored at this position in the list. */
        private final Fragment fragment;

        /**
         * Creates a new, list element.
         * Inserts the Element into the list by adjusting the prev and next pointers.
         *
         * @param fragment The Fragment this Element is supposed to store.
         * @param prev The element that should become the fragments predecessor
         * @param next The element that should become the fragments successor
         */
        public Element(Fragment fragment, Element prev, Element next) {
            this.fragment = fragment;
            this.prev = prev;
            this.next = next;
        }

        /**
         * Returns the fragment.
         *
         * @return The fragment of this Element.
         */
        private Fragment getFragment() {
            return this.fragment;
        }

        /**
         * Returns the next Element.
         *
         * @return The next Element.
         */
        private Element getNext() {
            return this.next;
        }

        /**
         * Set the next Element.
         *
         * @param next The next Element
         */
        private void setNext(Element next) {
            this.next = next;
        }

        /**
         * Returns the previous Element.
         *
         * @return The previous Element.
         */
        private Element getPrevious() {
            return this.prev;
        }

        /**
         * Set the previous Element.
         *
         * @param previous The previous Element.
         */
        private void setPrevious(Element previous) {
            this.prev = previous;
        }
    }
}
