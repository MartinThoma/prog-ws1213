package wordprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class EmptyFragmentTest {
    Fragment fragment;

    public EmptyFragmentTest() {
    }

    @Before
    public void initialize() {
        fragment = new Fragment(new Style(true, true));
    }

    @Test
    public void creationWithoutStringTest() {
        assertEquals("Newly created empty Fragment has wrong length", 0, fragment.length());
        assertEquals("Newly created empty Fragment has wrong text", "", fragment.toString());
    }

    @Test(timeout = 1000)
    public void isEmptyTest() {
        assertTrue("Empty Fragment considered non-empty", fragment.isEmpty());
    }

    @Test(timeout = 1000)
    public void insertTest() {
        fragment.insert(0, "Hello, World!");
        assertEquals("Failed inserting a String into the Fragment", "Hello, World!", fragment.toString());
    }

    @Test(timeout = 1000)
    public void substringTest() {
        assertEquals("Failed returning empty substring from emtpy Fragment", "", fragment.substring(0, 0));
    }

    @Test(timeout = 1000)
    public void deleteSubstring() {
        fragment.deleteSubstring(0, 0);
        assertEquals("Failed deleting nothing from an empty Fragment", "", fragment.toString());
    }

    @Test(timeout = 1000)
    public void toHTMLTest() {
        assertEquals("Failed generating HTML from empty Fragment", "", fragment.toHTML());
    }

    @Test(timeout = 1000)
    public void cursorHasLeftRightTest() {
        FragmentCursor cursor = new FragmentCursor(fragment, 0);
        assertFalse("Cursor is in empty Fragment but says hasLeft()", cursor.hasLeft());
        assertFalse("Cursor is in empty Fragment but says hasRight()", cursor.hasRight());

        cursor = new FragmentCursor(fragment, 0);
        assertFalse("Cursor is in empty Fragment but says hasLeft()", cursor.hasLeft());
        assertFalse("Cursor is in empty Fragment but says hasRight()", cursor.hasRight());

        cursor = new FragmentCursor(fragment, fragment.length());
        assertFalse("Cursor is in empty Fragment but says hasLeft()", cursor.hasLeft());
        assertFalse("Cursor is in empty Fragment but says hasRight()", cursor.hasRight());
    }

    @Test(timeout = 1000)
    public void cursorInsertLeftTest() {
        FragmentCursor cursor = new FragmentCursor(fragment, 0);
        cursor.insert("H");
        assertEquals("Cursor failed inserted into empty Fragment", "H", fragment.toString());
        assertEquals("Cursor failed positioning after inserting into empty Fragment", 1, cursor.getPosition());
    }

    public static void main(String[] args) {
        JUnitCore.main(EmptyFragmentTest.class.getName());
    }
}