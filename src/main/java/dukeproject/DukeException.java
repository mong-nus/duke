package dukeproject;

public class DukeException extends Exception {

    /**
     * Handles custom exception throws by other classes or method
     */
    public DukeException(String message) {

        super("\u2369 OOPS!!! " + message);
    }
}
