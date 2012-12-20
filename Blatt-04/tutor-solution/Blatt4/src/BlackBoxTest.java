import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import wordprocessor.WordProcessor;

public class BlackBoxTest {
    private WordProcessor wp;

    public BlackBoxTest() {
    }

    @Before
    public void initialize() {
        wp = new WordProcessor();
        wp.insert("Hello, World!");
    }

    @Test(timeout = 1000)
    public void insertTest() {
        assertEquals("Hello, World!", wp.toString());
    }

    @Test(timeout = 1000)
    public void backspaceTest() {
        wp.backspace();
        assertEquals("Hello, World", wp.toString());

        wp.backspace(12);
        assertEquals("", wp.toString());

        wp.backspace();
        assertEquals("", wp.toString());

        wp.insert("i");
        assertEquals("i", wp.toString());

        wp.moveLeft();
        wp.delete();
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
    public void modificationTest() {
        wp.moveLeft(8);
        wp.insert("Good Bye");

        assertEquals("HelloGood Bye, World!", wp.toString());

        wp.moveLeft(8);
        wp.pressShift();
        wp.moveLeft(5);
        wp.delete();
        wp.releaseShift();

        assertEquals("Good Bye, World!", wp.toString());

        wp.moveRight(100);
        wp.backspace();

        assertEquals("Good Bye, World", wp.toString());

        wp.moveLeft(100);
        wp.insert("ä");
        wp.backspace();

        assertEquals("Good Bye, World", wp.toString());
    }

    @Test(timeout = 1000)
    public void styleTest() {
        wp.moveLeft(8);
        wp.insert("Good Bye");
        wp.moveLeft(8);
        wp.pressShift();
        wp.moveLeft(5);
        wp.delete();
        wp.releaseShift();
        wp.moveRight(100);

        assertEquals("Good Bye, World!", wp.toString());

        wp.pressShift();
        wp.moveLeft(6);
        wp.setBold();
        wp.releaseShift();
        wp.moveRight(100);

        assertEquals("Good Bye, <b>World!</b>", wp.toHTML());

        wp.pressShift();
        wp.moveLeft();
        wp.unsetBold();
        wp.releaseShift();

        assertEquals("Good Bye, <b>World</b>!", wp.toHTML());

        wp.moveRight();
        wp.insert("i");
        assertEquals("Good Bye, <b>World</b>!i", wp.toHTML());

        wp.backspace();
        assertEquals("Good Bye, <b>World</b>!", wp.toHTML());

        wp.moveLeft();
        wp.pressShift();
        wp.moveRight();
        wp.setBold();
        wp.releaseShift();

        assertEquals("Good Bye, <b>World</b><b>!</b>", wp.toHTML());

        wp.moveLeft(100);
        wp.pressShift();
        wp.moveRight(8);
        wp.setItalic();
        wp.releaseShift();

        assertEquals("<i>Good Bye</i>, <b>World</b><b>!</b>", wp.toHTML());

        wp.moveRight();
        wp.moveLeft();
        wp.pressShift();
        wp.moveLeft(8);
        wp.unsetItalic();
        wp.releaseShift();

        assertEquals("Good Bye, <b>World</b><b>!</b>", wp.toHTML());

        wp.moveRight();
        wp.moveLeft();
    }

    @Test(timeout = 1000)
    public void fragmentationTest() {
        styleTest();
        assertEquals("Good Bye, <b>World</b><b>!</b>", wp.toHTML());
        wp.insert("i");
        assertEquals("iGood Bye, <b>World</b><b>!</b>", wp.toHTML());
        wp.backspace();

        wp.moveRight(5);
        wp.pressShift();
        wp.moveRight(10);
        wp.setItalic();
        wp.releaseShift();
        assertEquals("Good <i>Bye</i><i>, </i><b><i>World</i></b><b>!</b>", wp.toHTML());

        wp.moveLeft(5);
        wp.backspace(5);
        assertEquals("Good <b><i>World</i></b><b>!</b>", wp.toHTML());

        wp.moveRight(2);
        wp.delete(3);
        wp.insert("y");
        wp.moveLeft(2);
        wp.backspace();
        wp.insert("B");
        assertEquals("Good <b><i>Boy</i></b><b>!</b>", wp.toHTML());

        wp.moveRight(2);
        wp.pressShift();
        wp.moveLeft(3);
        wp.unsetItalic();
        wp.unsetBold();
        wp.releaseShift();
        assertEquals("Good Boy<b>!</b>", wp.toHTML());

        wp.moveLeft();
        wp.moveLeft();

        wp.moveRight(5);
        wp.pressShift();
        wp.moveRight();
        wp.unsetBold();
        wp.releaseShift();
        assertEquals("Good Boy!", wp.toHTML());
    }

    public static void main(String[] args) {
        JUnitCore.main(BlackBoxTest.class.getName());
    }
}