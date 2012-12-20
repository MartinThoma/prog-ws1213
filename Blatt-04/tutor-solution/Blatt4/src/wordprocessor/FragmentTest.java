package wordprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class FragmentTest {
    Fragment fragment;

    public FragmentTest() {
    }

    @Before
    public void initialize() {
        fragment = new Fragment(new Style(false, false), "Hello, World!");
    }

    @Test
    public void creationWithStringTest() {
        assertEquals("Newly create Fragment has wrong text", "Hello, World!", fragment.toString());
    }

    @Test(timeout = 1000)
    public void isEmptyTest() {
        assertFalse("Non-empty Fragment considered empty", fragment.isEmpty());
    }

    @Test(timeout = 1000)
    public void charAtTest() {
        assertEquals("Fragment returning wrong character", new Character('H'), fragment.charAt(0));
        assertEquals("Fragment returning wrong character", new Character('!'), fragment.charAt(12));
    }

    @Test(timeout = 1000)
    public void insertAtStartTest() {
        fragment = new Fragment(new Style(true, true));
        fragment.insert(0, "Hello");
        assertEquals("Failed prepending a String to the Fragment", "Hello", fragment.toString());
        assertEquals("Wrong length after insert", 5, fragment.length());
    }

    @Test(timeout = 1000)
    public void insertAtEndTest() {
        fragment = new Fragment(new Style(true, true), "Hello");
        fragment.insert(5, "World!");
        assertEquals("Failed prepending a String to the Fragment", "HelloWorld!", fragment.toString());
        assertEquals("Wrong length after insert", 11, fragment.length());
    }

    @Test(timeout = 1000)
    public void insertAtMiddleTest() {
        fragment = new Fragment(new Style(true, true), "HelloWorld!");
        fragment.insert(5, ", ");
        assertEquals("Failed prepending a String to the Fragment", "Hello, World!", fragment.toString());
        assertEquals("Wrong length after insert", 13, fragment.length());
    }

    @Test(timeout = 1000)
    public void deleteAtStartTest() {
        fragment.deleteCharAt(0);
        assertEquals("Failed deleting the first character from a Fragment", "ello, World!", fragment.toString());
        assertEquals("Wrong length after delete", 12, fragment.length());
    }

    @Test(timeout = 1000)
    public void deleteAtMiddleTest() {
        fragment.deleteCharAt(4);
        assertEquals("Failed deleting a character from a Fragment", "Hell, World!", fragment.toString());
        assertEquals("Wrong length after delete", 12, fragment.length());
    }

    @Test(timeout = 1000)
    public void deleteAtEndTest() {
        fragment.deleteCharAt(12);
        assertEquals("Failed deleting the last character from a Fragment", "Hello, World", fragment.toString());
        assertEquals("Wrong length after delete", 12, fragment.length());
    }

    @Test(timeout = 1000)
    public void emptySubstringTest() {
        assertEquals("Failed returning an empty substring from a Fragment", "", fragment.substring(2, 2));
    }

    @Test(timeout = 1000)
    public void totalSubstringTest() {
        assertEquals("Failed returning the whole string as a substring from Fragment", "Hello, World!", fragment.substring(0, fragment.length()));
    }

    @Test(timeout = 1000)
    public void someSubstringTest() {
        assertEquals("Failed returning a partial string as a substring from Fragment", "Hello", fragment.substring(0, 5));
    }

    @Test(timeout = 1000)
    public void deleteEmptySubstring() {
        fragment.deleteSubstring(0, 0);
        assertEquals("Failed deleting nothing from a Fragment", "Hello, World!", fragment.toString());
    }

    @Test(timeout = 1000)
    public void deleteSomeSubstring() {
        fragment.deleteSubstring(0, 7);
        assertEquals("Failed deleting something from a Fragment", "World!", fragment.toString());
    }

    @Test(timeout = 1000)
    public void deleteTotalSubstring() {
        fragment.deleteSubstring(0, 13);
        assertEquals("Failed deleting something from a Fragment", "", fragment.toString());
    }

    @Test(timeout = 1000)
    public void styleTest() {
        assertEquals("Wrong Formatted Output", "Hello, World!", fragment.toHTML());
    }

    @Test(timeout = 1000)
    public void styleBoldTest() {
        fragment.getStyle().apply(StyleChange.SET_BOLD);
        assertEquals("Wrong Formatted Output", "<b>Hello, World!</b>", fragment.toHTML());
    }

    @Test(timeout = 1000)
    public void styleItalicTest() {
        fragment.getStyle().apply(StyleChange.SET_ITALIC);
        assertEquals("Wrong Formatted Output", "<i>Hello, World!</i>", fragment.toHTML());
    }

    @Test(timeout = 1000)
    public void styleBoldItalicTest() {
        fragment.getStyle().apply(StyleChange.SET_BOLD);
        fragment.getStyle().apply(StyleChange.SET_ITALIC);
        assertEquals("Wrong Formatted Output", "<b><i>Hello, World!</i></b>", fragment.toHTML());
    }

    @Test(timeout = 1000)
    public void toStringTest() {
        fragment.getStyle().apply(StyleChange.SET_BOLD);
        fragment.getStyle().apply(StyleChange.SET_ITALIC);
        assertEquals("Failed generating unformatted Text", "Hello, World!", fragment.toString());
    }

    public static void main(String[] args) {
        JUnitCore.main(FragmentTest.class.getName());
    }
}