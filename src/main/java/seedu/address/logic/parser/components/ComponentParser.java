package seedu.address.logic.parser.components;

import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input without component.
 */
public interface ComponentParser {

    /**
     * Used for initial separation of component word and args.
     */
    static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");


    /**
     * Parses user input into command for execution.
     *
     * @param args user input string without components
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String args) throws ParseException;
}
