package seedu.smartlib.logic.commands.exceptions;

import seedu.smartlib.logic.commands.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {

    /**
     * A constructor for the CommandException.
     *
     * @param message should contain relevant information on the failed constraint(s).
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     *
     * @param message a String containing the details of the error.
     * @param cause cause of the error.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

}
