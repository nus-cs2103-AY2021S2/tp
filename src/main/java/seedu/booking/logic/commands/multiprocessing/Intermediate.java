package seedu.booking.logic.commands.multiprocessing;

import seedu.booking.logic.commands.Command;
import seedu.booking.logic.parser.exceptions.ParseException;

/**
 * Represents an intermediate that is able to store temporary user inputs during a
 * multi-step response execution
 */
public interface Intermediate<T extends Command> {

    /**
     * Parses stored user input into a command and returns it.
     * @throws ParseException if user input does not conform the expected format
     */
    T parse() throws ParseException;

}
