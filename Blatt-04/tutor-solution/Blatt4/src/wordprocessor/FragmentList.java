package wordprocessor;

/**
 * A doubly linked list for storing Fragments.
 *
 * @author anonymer Student und Tutoren
 * @version 1.2
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
     *         empty.
     */
    public Fragment getFirstElement() {
        Helper.abortIf(size() == 0,
                "An empty list has no first element");
        return firstElement.getFragment();
    }

    /**
     * Returns the last Element in the FragmentList.
     *
     * @return the first Element in the FragmentList, or null if the list is
     *         empty.
     */
    public Fragment getLastElement() {
        return this.lastElement.getFragment();
    }

    /**
     * Appends a Fragment at the end of the list.
     *
     * If the list is empty, the new element is the first and last element. If
     * the list is not empty, the previously last element's successor is the new
     * element, the new element's predecessor is the previously last element,
     * and the new element is the list's last element.
     *
     * @param fragment The Fragment to be appended to the list.
     */
    public void add(Fragment fragment) {
        if (this.isEmpty()) {
            Element newElement = new Element(fragment, null, null);
            firstElement = newElement;
            lastElement = newElement;
        } else {
            Element newElement = new Element(fragment, lastElement,
                    null);
            lastElement.next = newElement;
            lastElement = newElement;
        }
    }

    /**
     * Returns a new FragmentList.Cursor pointing to the first element in the
     * list. The List must contain at least one element otherwise a call to
     * cursor is not allowed. Aborts if the list is empty.
     *
     * @return A new FragmentList.Cursor pointing to the first element in the
     *         list
     */
    public Cursor cursor() {
        Helper.abortIf(isEmpty(), "the list is empty");

        return new Cursor(this);
    }

    /**
     * Returns true if there are no Fragments in the list, false otherwise.
     *
     * @return true if there are no Fragments in the list, false otherwise.
     */
    public boolean isEmpty() {
        return firstElement == null;
    }

    /**
     * Returns the number of Fragments in the list.
     *
     * Note, that this does not return the total number of characters.
     *
     * @return The number of Fragments in the list.
     */
    public int size() {
        int result = 0;
        Element nextElement = firstElement;
        while (nextElement != null) {
            result++;
            nextElement = nextElement.getNext();
        }
        return result;
    }

    /**
     * A cursor to allow navigation and modification of a fragment-list.
     *
     * @author <insert your name here>
     * @version 1.2
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
            currentElement = list.firstElement;
        }

        /**
         * Returns the current fragment.
         *
         * @return The current fragment the cursor points at.
         */
        public Fragment getFragment() {
            return currentElement.getFragment();
        }

        /**
         * Insert the Fragment into the FragmentList right after the current
         * fragment.
         *
         * @param fragment The Fragment to be inserted.
         */
        public void insertAfter(Fragment fragment) {
            Element newElement = new Element(fragment,
                    currentElement, currentElement.getNext());
            if (currentElement.getNext() != null) {
                currentElement.getNext().setPrevious(newElement);
            } else {
                list.lastElement = newElement;
            }
            currentElement.setNext(newElement);
        }

        /**
         * Insert the Fragment into the FragmentList right before the current
         * fragment.
         *
         * @param fragment The Fragment to be inserted.
         */
        public void insertBefore(Fragment fragment) {
            Element newElement = new Element(fragment, currentElement
                    .getPrevious(), currentElement);

            if (currentElement.getPrevious() != null) {
                currentElement.getPrevious().setNext(newElement);
            }

            currentElement.setPrevious(newElement);
        }

        /**
         * Deletes the Fragment right after the current Element. Aborts if the
         * cursor points at the last element.
         */
        public void deleteNext() {
            Helper.abortIf(!hasNext(),
                    "the cursor points at the last element");
            Element element = currentElement.getNext().getNext();
            if (element != null) {
                element.setPrevious(currentElement);
                currentElement.setNext(element);
            } else {
                currentElement.setNext(null);
            }
        }

        /**
         * Deletes the Fragment right before the current Element. Aborts if the
         * cursor points at the first element.
         */
        public void deletePrevious() {
            Helper.abortIf(!hasPrevious(),
                    "the cursor points at the first element");
            Element element = currentElement.getPrevious()
                    .getPrevious();
            if (element != null) {
                element.setNext(currentElement);
                currentElement.setPrevious(element);
            } else {
                currentElement.setPrevious(null);
            }
        }

        /**
         * Returns true if there is another Fragment in the list after the
         * current fragment.
         *
         * @return True if there is another Fragment in the list after the
         *         current fragment.
         */
        public boolean hasNext() {
            return currentElement.getNext() != null;
        }

        /**
         * Returns true if there is another Fragment in the list before the
         * current fragment.
         *
         * @return True if there is another Fragment in the list before the
         *         current fragment.
         */
        public boolean hasPrevious() {
            return currentElement.getPrevious() != null;
        }

        /**
         * Returns the next element in the list and moves the cursor to the next
         * fragment. Aborts if there is no next fragment.
         *
         * @return This Element's successor Element.
         */
        public Fragment next() {
            Helper.abortIf(!hasNext(),
                    "the cursor has no next element");

            currentElement = currentElement.getNext();
            return currentElement.getFragment();
        }

        /**
         * Returns the previous element in the list and moves the cursor to the
         * previous fragment. Aborts if there is no previous fragment.
         *
         * @return This Element's predecessor Element.
         */
        public Fragment previous() {
            Helper.abortIf(!hasPrevious(),
                    "the cursor has no previous element");

            currentElement = currentElement.getPrevious();
            return currentElement.getFragment();
        }
    }

    /**
     * Wraps a fragment for insertion to a fragment-list.
     */
    private class Element {
        /**
         * This Element's predecessor Element. This is null if the Element is
         * the first in the list.
         */
        private Element prev = null;

        /**
         * This Element's successor Element. This is null if the Element is the
         * last in the list.
         */
        private Element next = null;

        /** The Fragment stored at this position in the list. */
        private final Fragment fragment;

        /**
         * Creates a new, list element. Inserts the Element into the list by
         * adjusting the prev and next pointers.
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
            return fragment;
        }

        /**
         * Returns the next Element.
         *
         * @return The next Element.
         */
        private Element getNext() {
            return next;
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
            return prev;
        }

        /**
         * Set the previous Element.
         *
         * @param previous The previous Element.
         */
        private void setPrevious(Element previous) {
            prev = previous;
        }
    }
}