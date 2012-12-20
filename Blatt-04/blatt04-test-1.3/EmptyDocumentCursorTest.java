package wordprocessor;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmptyDocumentCursorTest {
	Document emptyDocument;

	@Before
	public void initialize() {
		emptyDocument = new Document();
	}

	@Test
	public void emptyDocumentHasLeftRightTest() {
		DocumentCursor cursor = emptyDocument.newCursorAt(0);
		assertFalse("Cursor should not say hasRight()", cursor.hasRight());
		assertFalse("Cursor should not say hasLeft()", cursor.hasLeft());
		assertEquals("Wrong Position", 0, cursor.getPosition());
	}
	
	public static void main(String[] args) {
		JUnitCore.main(EmptyDocumentCursorTest.class.getName());
	}
}
