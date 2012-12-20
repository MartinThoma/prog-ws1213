package wordprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class FragmentListTest {
    FragmentList list;

    public FragmentListTest() {
    }

    @Before
    public void initialize() {
        list = new FragmentList();
    }

    @Test
    public void emptyListTest() {
        assertEquals("Empty list size is incorrect", 0, list.size());
    }

    @Test(timeout = 1000)
    public void addTest() {
        Fragment fragment = new Fragment(new Style(true, true), "a");
        list.add(fragment);
        assertEquals("List size is incorrect", 1, list.size());
        assertEquals("List start is incorrect", fragment, list.getFirstElement());

        assertEquals("Internal list link is incorrect", list.getFirstElement(), list.getLastElement());
        assertEquals("Internal list link is incorrect", list.getLastElement(), list.getFirstElement());

        Fragment fragment2 = new Fragment(new Style(true, true), "b");
        list.add(fragment2);
        assertEquals("List size is incorrect", 2, list.size());
        assertEquals("List start is incorrect", fragment, list.getFirstElement());
        assertEquals("List end is incorrect", fragment2, list.getLastElement());
    }

    @Test
    public void cursorTest() {
        Fragment fragment = new Fragment(new Style(true, true), "a");
        list.add(fragment);
        FragmentList.Cursor cursor = list.cursor();
        assertEquals("Cursor points wrong", cursor.getFragment(), fragment);
        assertFalse("Cursor should not say hasPrevious", cursor.hasPrevious());
        assertFalse("Cursor should not say hasNext", cursor.hasNext());

    }

    @Test(timeout = 1000)
    public void cursorInsertAfterTest() {
        Fragment fragment = new Fragment(new Style(true, true), "a");
        list.add(fragment);
        FragmentList.Cursor cursor = list.cursor();

        Fragment fragment2 = new Fragment(new Style(true, true), "b");
        cursor.insertAfter(fragment2);

        assertEquals("Cursor points wrong", cursor.getFragment(), fragment);
        assertFalse("Cursor should not say hasPrevious", cursor.hasPrevious());
        assertTrue("Cursor should say hasNext", cursor.hasNext());
    }


    @Test(timeout = 1000)
    public void cursorNextTest() {
        Fragment fragment = new Fragment(new Style(true, true), "a");
        list.add(fragment);
        FragmentList.Cursor cursor = list.cursor();

        Fragment fragment2 = new Fragment(new Style(true, true), "b");
        cursor.insertAfter(fragment2);
        cursor.next();

        assertEquals("Cursor points wrong", cursor.getFragment(), fragment2);
        assertTrue("Cursor should say hasPrevious", cursor.hasPrevious());
        assertFalse("Cursor should not say hasNext", cursor.hasNext());
    }


    @Test(timeout = 1000)
    public void cursorInsertBeforeTest() {
        Fragment fragment = new Fragment(new Style(true, true), "a");
        list.add(fragment);
        FragmentList.Cursor cursor = list.cursor();

        Fragment fragment2 = new Fragment(new Style(true, true), "b");
        cursor.insertBefore(fragment2);

        assertEquals("Cursor points wrong", cursor.getFragment(), fragment);
        assertTrue("Cursor should say hasPrevious", cursor.hasPrevious());
        assertFalse("Cursor should not say hasNext", cursor.hasNext());
    }


    @Test(timeout = 1000)
    public void cursorPreviousTest() {
        Fragment fragment = new Fragment(new Style(true, true), "a");
        list.add(fragment);
        FragmentList.Cursor cursor = list.cursor();

        Fragment fragment2 = new Fragment(new Style(true, true), "b");
        cursor.insertBefore(fragment2);
        cursor.previous();

        assertEquals("Cursor points wrong", cursor.getFragment(), fragment2);
        assertFalse("Cursor should not say hasPrevious", cursor.hasPrevious());
        assertTrue("Cursor should say hasNext", cursor.hasNext());
    }

    public static void main(String[] args) {
        JUnitCore.main(FragmentListTest.class.getName());
    }
}
