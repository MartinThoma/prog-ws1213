package wordprocessor;

/**
 * Class for helping abort a program on errors.
 *
 * @author Markus Iser, Florian Merz
 * @version 1.0
 */
class Helper {
    /**
     * Aborts the program if the condition is met.
     *
     * @param condition The condition that aborts the program.
     * @param message The message to print out on abortion.
     */
    static void abortIf(boolean condition, String message) {
        if (condition) {
            throw new TestException(message);
        }
    }
}


class TestException extends RuntimeException {

    public TestException() {
        super();
    }

    public TestException(String message) {
        super(message);
    }
}