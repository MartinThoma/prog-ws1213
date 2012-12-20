package wordprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class DocumentCursorTest {
    Document helloDocument;

    @Before
    public void initialize() {
        helloDocument = new Document();
        DocumentCursor documentCursor = helloDocument.newCursorAt(0);
        documentCursor.insert("Hello, World!");
    }

    @Test
    public void hasLeftRightAtBeginTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(0);
        assertFalse("Cursor is at beginning but hasLeft()", cursor.hasLeft());
        assertTrue("Cursor ist at beginning but !hasRight()", cursor.hasRight());
        assertEquals("Wrong Position", 0, cursor.getPosition());
    }

    @Test
    public void hasLeftRightAtMiddleTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(5);
        assertTrue("Cursor is in the middle but !hasLeft()", cursor.hasLeft());
        assertTrue("Cursor ist in the middle but !hasRight()", cursor.hasRight());
        assertEquals("Wrong Position", 5, cursor.getPosition());
    }

    @Test
    public void hasLeftRightAtEndTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(13);
        assertTrue("Cursor is at the end but !hasLeft()", cursor.hasLeft());
        assertFalse("Cursor ist at the end but hasRight()", cursor.hasRight());
        assertEquals("Wrong Position", 13, cursor.getPosition());
    }

    @Test
    public void moveLeftRightAtBeginTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(0);
        cursor.moveRight();
        assertEquals("Wrong Position", 1, cursor.getPosition());
        cursor.moveLeft();
        assertEquals("Wrong Position", 0, cursor.getPosition());
    }

    @Test
    public void moveLeftRightAtMiddleTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(5);
        cursor.moveLeft();
        assertEquals("Wrong Position", 4, cursor.getPosition());
        cursor.moveRight();
        assertEquals("Wrong Position", 5, cursor.getPosition());
    }

    @Test
    public void moveLeftRightAtEndTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(13);
        cursor.moveLeft();
        assertEquals("Wrong Position", 12, cursor.getPosition());
        cursor.moveRight();
        assertEquals("Wrong Position", 13, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void deleteAtStartPositionTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(0);
        cursor.delete();
        assertEquals("Cursor didn't delete properly", "ello, World!", helloDocument.toString());
        assertFalse("Cursor should not say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 0, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void deleteAtMiddlePositionTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(3);
        cursor.delete();
        assertEquals("Cursor didn't delete properly", "Helo, World!", helloDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 3, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void deleteAtEndPositionTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(13);
        cursor.moveLeft();
        cursor.delete();
        assertEquals("Cursor didn't delete properly", "Hello, World", helloDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertFalse("Cursor should not say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 12, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void insertAtStartPositionTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(0);
        cursor.insert("i");
        assertEquals("Cursor didn't insert properly", "iHello, World!", helloDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 1, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void insertAtMiddlePositionTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(3);
        cursor.insert("i");
        assertEquals("Cursor didn't insert properly", "Helilo, World!", helloDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 4, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void insertAtEndPositionTest() {
        DocumentCursor cursor = helloDocument.newCursorAt(13);
        cursor.insert("i");
        assertEquals("Cursor didn't insert properly", "Hello, World!i", helloDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertFalse("Cursor should not say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 14, cursor.getPosition());
    }

    public static void main(String[] args) {
        JUnitCore.main(DocumentCursorTest.class.getName());
    }
}