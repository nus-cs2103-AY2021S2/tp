package seedu.address.logic.uicommands.exceptions;

import seedu.address.logic.uicommands.UiCommand;

/**
 * Represents an error which occurs during execution of a {@link UiCommand}.
 */
public class UiCommandException extends Exception {
    /**
     * Constructs a new {@code UiCommandException} with the specified detail {@code message}.
     */
    public UiCommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UiCommandException} with the specified detail {@code message} and {@code cause}.
     */
    public UiCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
