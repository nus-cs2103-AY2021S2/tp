package seedu.weeblingo.storage.exceptions;

/**
 * A subclass of RuntimeException which represents errors reading from built-in databases.
 * If this Exception is thrown, it means that the data file that is within the Jar file is
 * corrupted. Users are recommend to re-download and re-install the software.
 */
public class DataInitializationException extends RuntimeException {
    public DataInitializationException() {
        super("Cannot read from built-in data files. Please re-install the application.");
    }
}
