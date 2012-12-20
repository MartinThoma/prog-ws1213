package wordprocessor;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FragmentCursorTest {
	Fragment fragment;

	public FragmentCursorTest() {
	}

	@Before
	public void initialize() {
		fragment = new Fragment(new Style(true, true), "Hello, World!");
	}

	@Test
	public void newCursorAtStartPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 0);
		assertEquals("New cursor not at start of Fragment", 0, cursor.getPosition());
	}

	@Test(timeout = 1000)
	public void newCursorAtMiddlePositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 5);
		assertEquals("New cursor not at position", 5, cursor.getPosition());
	}

	@Test(timeout = 1000)
	public void newCursorAtEndPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, fragment.length());
		assertEquals("New cursor not at end of Fragment", 13, cursor.getPosition());
	}
	
	@Test(timeout = 1000)
	public void hasLeftRightAtStartPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 0);
		assertFalse("Cursor is at beginning but says hasLeft()", cursor.hasLeft());
		assertTrue("Cursor is at beginning but doesn't say hasRight()", cursor.hasRight());
	}

	@Test(timeout = 1000)
	public void hasLeftRightAtMiddlePositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 5);
		assertTrue("Cursor is in the middle but doesn't say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor is at beginning but doesn't say hasRight()", cursor.hasRight());
	}

	@Test(timeout = 1000)
	public void hasLeftRightAtEndPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, fragment.length());
		assertTrue("Cursor is at beginning but doesn't say hasLeft()", cursor.hasLeft());
		assertFalse("Cursor is at beginning but says hasRight()", cursor.hasRight());
	}
	
	@Test(timeout = 1000)
	public void moveRightTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 0);
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor should say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 5, cursor.getPosition());
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		cursor.moveRight();
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertFalse("Cursor should not say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 13, cursor.getPosition());
	}

	@Test(timeout = 1000)
	public void moveLeftTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 13);
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor should say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 5, cursor.getPosition());
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		cursor.moveLeft();
		assertFalse("Cursor should not say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor should say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 0, cursor.getPosition());
	}

	@Test(timeout = 1000)
	public void deleteAtStartPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 0);
		cursor.delete();
		assertEquals("Cursor didn't delete properly", "ello, World!", fragment.toString());
		assertFalse("Cursor should not say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor should say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 0, cursor.getPosition());
	}

	@Test(timeout = 1000)
	public void deleteAtMiddlePositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 3);
		cursor.delete();
		assertEquals("Cursor didn't delete properly", "Helo, World!", fragment.toString());
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor should say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 3, cursor.getPosition());
	}
	
	@Test(timeout = 1000)
	public void deleteAtEndPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 13);
		cursor.moveLeft();
		cursor.delete();
		assertEquals("Cursor didn't delete properly", "Hello, World", fragment.toString());
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertFalse("Cursor should not say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 12, cursor.getPosition());
	}

	@Test(timeout = 1000)
	public void insertAtStartPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 0);
		cursor.insert("i");
		assertEquals("Cursor didn't insert properly", "iHello, World!", fragment.toString());
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor should say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 1, cursor.getPosition());
	}

	@Test(timeout = 1000)
	public void insertAtMiddlePositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 3);
		cursor.insert("i");
		assertEquals("Cursor didn't insert properly", "Helilo, World!", fragment.toString());
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertTrue("Cursor should say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 4, cursor.getPosition());
	}
	
	@Test(timeout = 1000)
	public void insertAtEndPositionTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 13);
		cursor.insert("i");
		assertEquals("Cursor didn't insert properly", "Hello, World!i", fragment.toString());
		assertTrue("Cursor should say hasLeft()", cursor.hasLeft());
		assertFalse("Cursor should not say hasRight()", cursor.hasRight());
		assertEquals("Cursor at wrong position", 14, cursor.getPosition());
	}
	
	@Test(timeout = 1000)
	public void cutOffTest() {
		FragmentCursor cursor = new FragmentCursor(fragment, 5);
		Fragment cutOffFragment = cursor.cutOff();
		assertEquals("Cursor didn't cut properly", "Hello", fragment.toString());
		assertEquals("Cursor didn't cut properly", ", World!", cutOffFragment.toString());
	}

	public static void main(String[] args) {
		JUnitCore.main(FragmentCursorTest.class.getName());
	}
}
