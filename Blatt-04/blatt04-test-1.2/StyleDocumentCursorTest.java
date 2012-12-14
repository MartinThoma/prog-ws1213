package wordprocessor;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

public class StyleDocumentCursorTest {
	Document document;

	@Before
	public void initialize() {
		document = new Document();
		DocumentCursor documentCursor = document.newCursorAt(0);
		documentCursor.insert("Hello, World!");
	}

	@Test
	public void styleTest() {
		assertEquals("Style not handled properly", "Hello, World!", document.toHTML());
	}

	@Test(timeout = 1000)
	public void styleAllBoldTest() {
		DocumentCursor selectionStart = document.newCursorAt(0);
		selectionStart.split();
		DocumentCursor selectionEnd = document.newCursorAt(13);
		selectionEnd.split();
		DocumentCursor.applyStyleChange(StyleChange.SET_BOLD, selectionStart, selectionEnd);
		assertEquals("Style not handled properly", "<b>Hello, World!</b>", document.toHTML());
	}

	@Test(timeout = 1000)
	public void styleAllItalicTest() {
		DocumentCursor selectionStart = document.newCursorAt(0);
		selectionStart.split();
		DocumentCursor selectionEnd = document.newCursorAt(13);
		selectionEnd.split();
		DocumentCursor.applyStyleChange(StyleChange.SET_ITALIC, selectionStart, selectionEnd);
		assertEquals("Style not handled properly", "<i>Hello, World!</i>", document.toHTML());
	}
	
	@Test(timeout = 1000)
	public void stylePartBoldTest() {
		DocumentCursor selectionStart = document.newCursorAt(7);
		selectionStart.split();
		DocumentCursor selectionEnd = document.newCursorAt(12);
		selectionEnd.split();
		DocumentCursor.applyStyleChange(StyleChange.SET_BOLD, selectionStart, selectionEnd);
		assertEquals("Style not handled properly", "Hello, <b>World</b>!", document.toHTML());
	}
	
	@Test(timeout = 1000)
	public void restylePartNotBoldTest() {
		DocumentCursor selectionStart = document.newCursorAt(7);
		selectionStart.split();
		DocumentCursor selectionEnd = document.newCursorAt(13);
		selectionEnd.split();
		DocumentCursor.applyStyleChange(StyleChange.SET_BOLD, selectionStart, selectionEnd);
		selectionStart = document.newCursorAt(12);
		selectionStart.split();
		selectionEnd = document.newCursorAt(13);
		selectionEnd.split();
		DocumentCursor.applyStyleChange(StyleChange.UNSET_BOLD, selectionStart, selectionEnd);
		assertEquals("Style not handled properly", "Hello, <b>World</b>!", document.toHTML());
	}
	
	@Test(timeout = 1000)
	public void restylePartItalicTest() {
		DocumentCursor selectionStart = document.newCursorAt(0);
		selectionStart.split();
		DocumentCursor selectionEnd = document.newCursorAt(13);
		selectionEnd.split();
		DocumentCursor.applyStyleChange(StyleChange.SET_BOLD, selectionStart, selectionEnd);
		selectionStart = document.newCursorAt(7);
		selectionStart.split();
		selectionEnd = document.newCursorAt(12);
		selectionEnd.split();
		DocumentCursor.applyStyleChange(StyleChange.SET_ITALIC, selectionStart, selectionEnd);
		assertEquals("Style not handled properly", "<b>Hello, </b><b><i>World</i></b><b>!</b>", document.toHTML());
	}
	
	public static void main(String[] args) {
		JUnitCore.main(StyleDocumentCursorTest.class.getName());
	}
}
