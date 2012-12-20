package wordprocessor;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class FunctionalTest {
	private WordProcessor wp;

	public FunctionalTest() {
	}

	@Before
	public void initialize() {
		wp = new WordProcessor();
		wp.insert("Hello, World!");
	}

	@Test
	public void insertTest() {
		assertEquals("Hello, World!", wp.toString());
	}

	@Test(timeout = 1000)
	public void moveTest() {
		assertEquals(13, wp.getCursorPosition());
		wp.moveRight();
		assertEquals(13, wp.getCursorPosition());

		wp.moveLeft();
		wp.moveLeft();
		assertEquals(11, wp.getCursorPosition());

		wp.moveLeft();
	}

	@Test(timeout = 1000)
	public void backspaceTest() {
		wp.backspace();
		assertEquals("Hello, World", wp.toString());

		wp.backspace(12);
		assertEquals("", wp.toString());

		wp.backspace();
		assertEquals("", wp.toString());
	}

	@Test(timeout = 1000)
	public void deleteTest() {
		wp.delete();
		assertEquals("Hello, World!", wp.toString());

		wp.moveLeft(13);

		wp.delete();
		assertEquals("ello, World!", wp.toString());

		wp.delete(12);
		assertEquals("", wp.toString());

		wp.delete();
		assertEquals("", wp.toString());
	}

	@Test(timeout = 1000)
	public void styleTest() {
		wp.pressShift();
		wp.moveLeft(6);
		wp.setBold();
		wp.releaseShift();
	}

	public static void main(String[] args) {
		JUnitCore.main(FunctionalTest.class.getName());
	}
}
