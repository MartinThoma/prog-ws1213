package wordprocessor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

public class SplitDocumentCursorTest {
    Document splitDocument;

    @Before
    public void initialize() {
        splitDocument = new Document();
        DocumentCursor documentCursor = splitDocument.newCursorAt(0);
        documentCursor.insert("Hello, World!");
        documentCursor = splitDocument.newCursorAt(5);
        documentCursor.split();
    }

    @Test
    public void splitTest() {
        DocumentCursor cursor = splitDocument.newCursorAt(5);
        assertEquals("Cursor doesn't handle fragments properly", "Hello, World!", splitDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 5, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void splitMoveLeftTest() {
        DocumentCursor cursor = splitDocument.newCursorAt(5);
        cursor.moveLeft();
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 4, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void splitMoveRightTest() {
        DocumentCursor cursor = splitDocument.newCursorAt(5);
        cursor.moveRight();
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 6, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void splitInsertTest() {
        DocumentCursor cursor = splitDocument.newCursorAt(5);
        cursor.insert("i");
        assertEquals("No proper insert at fragment border", "Helloi, World!", splitDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 6, cursor.getPosition());
    }

    @Test(timeout = 1000)
    public void splitDeleteTest() {
        DocumentCursor cursor = splitDocument.newCursorAt(5);
        cursor.delete();
        assertEquals("No proper insert at fragment border", "Hello World!", splitDocument.toString());
        assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
        assertTrue("Cursor should say hasRight()", cursor.hasRight());
        assertEquals("Cursor at wrong position", 5, cursor.getPosition());
    }

    public static void main(String[] args) {
        JUnitCore.main(SplitDocumentCursorTest.class.getName());
    }
}