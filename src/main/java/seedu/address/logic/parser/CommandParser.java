package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public interface CommandParser {
    /**
     * Parses the {@code args} in the user input and returns the corresponding Parser.
     */
    Command parseCommand(String args) throws ParseException;
}
