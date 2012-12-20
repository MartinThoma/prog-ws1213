package wordprocessor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import wordprocessor.FragmentList.Cursor;


public class AdditionalTest {

    // part A

    @Test(timeout = 1000)
    public void styleMustBeEqual() {
        assertTrue(new Style(false, false).equals(new Style(false, false)));
    }

    @Test(timeout = 1000)
    public void styleMustNotBeEqual0() {
        assertFalse(new Style(true, false).equals(new Style(false, false)));
    }

    @Test(timeout = 1000)
    public void styleMustNotBeEqual1() {
        assertFalse(new Style(false, true).equals(new Style(false, false)));
    }

    @Test(timeout = 1000)
    public void styleMustNotBeEqual2() {
        assertFalse(new Style(false, true).equals(null));
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfNull0() {
        new Fragment(null);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfNull1() {
        new Fragment(null, "Hello, World!");
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfNull2() {
        new Fragment(new Style(false, false), null);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfCharAtNegative() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.charAt(-1);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfCharAtTooBig() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.charAt(13);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfInsertNegative() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.insert(-1, "");
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfInsertTooBig() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.insert(14, "");
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfInsertNull() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.insert(5, null);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfDeleteCharAtNegative() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.deleteCharAt(-1);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfDeleteCharAtTooBig() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.deleteCharAt(13);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfSubstringNegative0() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.substring(-1, 0);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfSubstringNegative1() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.substring(0, -1);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfSubstringTooBig0() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.substring(14, 0);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfSubstringTooBig1() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.substring(0, 14);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfSubstringEndGreaterThanStart() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.substring(7, 5);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfDeleteSubstringNegative0() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.deleteSubstring(-1, 0);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfDeleteSubstringNegative1() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.deleteSubstring(0, -1);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfDeleteSubstringTooBig0() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.deleteSubstring(14, 0);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfDeleteSubstringTooBig1() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.deleteSubstring(0, 14);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentIfDeleteSubstringEndGreaterThanStart() {
        Fragment fragment = new Fragment(new Style(false, false), "Hello, World!");
        fragment.deleteSubstring(7, 5);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfNull0() {
        new FragmentCursor(null, 0);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfNull1() {
        new FragmentCursor(new Fragment(new Style(false, false), "Hello, World!"), -1);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfNull2() {
        new FragmentCursor(new Fragment(new Style(false, false), "Hello, World!"), 14);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfMoveLeftHasNoLeft() {
        FragmentCursor f = new FragmentCursor(
                new Fragment(new Style(false, false), "Hello, World!"), 0);
        f.moveLeft();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfMoveRightHasNoRight() {
        FragmentCursor f = new FragmentCursor(
                new Fragment(new Style(false, false), "Hello, World!"), 13);
        f.moveRight();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfDeleteHasNoRight() {
        FragmentCursor f = new FragmentCursor(
                new Fragment(new Style(false, false), "Hello, World!"), 13);
        f.delete();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfInsertIsNull() {
        FragmentCursor f = new FragmentCursor(
                new Fragment(new Style(false, false), "Hello, World!"), 0);
        f.insert(null);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfCutOfIndexIsZero() {
        FragmentCursor f = new FragmentCursor(
                new Fragment(new Style(false, false), "Hello, World!"), 0);
        f.cutOff();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentCursorIfCutOfIndexIsMaxLen() {
        FragmentCursor f = new FragmentCursor(
                new Fragment(new Style(false, false), "Hello, World!"), 13);
        f.cutOff();
    }

    // part B
    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentListInCursor() {
        FragmentList f = new FragmentList();
        f.cursor();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentListCursorIfDeleteNextHasNoNext() {
        FragmentList f = new FragmentList();
        Fragment fragment = new Fragment(new Style(true, true), "a");
        f.add(fragment);
        Cursor cursor = f.cursor();
        cursor.deleteNext();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentListCursorIfDeletePreviousHasNoPrevious() {
        FragmentList f = new FragmentList();
        Fragment fragment = new Fragment(new Style(true, true), "a");
        f.add(fragment);
        Cursor cursor = f.cursor();
        cursor.deletePrevious();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentListCursorIfNextHasNoNext() {
        FragmentList f = new FragmentList();
        Fragment fragment = new Fragment(new Style(true, true), "a");
        f.add(fragment);
        Cursor cursor = f.cursor();
        cursor.next();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInFragmentListCursorIfPreviousHasNoPrevious() {
        FragmentList f = new FragmentList();
        Fragment fragment = new Fragment(new Style(true, true), "a");
        f.add(fragment);
        Cursor cursor = f.cursor();
        cursor.previous();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInDocumentCursorIfMoveLeftHasNoLeft() {
        DocumentCursor d = new DocumentCursor(new FragmentList(), 0);
        d.moveLeft();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInDocumentCursorIfMoveRightHasNoRight() {
        DocumentCursor d = new DocumentCursor(new FragmentList(), 0);
        d.moveRight();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInDocumentCursorIfDeleteOperatesOnEmptyList() {
        DocumentCursor d = new DocumentCursor(new FragmentList(), 0);
        d.delete();
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInDocumentCursorIfInsertIsNull() {
        DocumentCursor d = new DocumentCursor(new FragmentList(), 0);
        d.insert(null);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInDocumentIfNewCursorAtIsNegative0() {
        Document d = new Document();
        d.newCursorAt(-1);
    }

    @Test(expected = TestException.class, timeout = 1000)
    public void abortInDocumentIfNewCursorAtIsNegative1() {
        Document d = new Document();
        d.newCursorAt(1);
    }
}