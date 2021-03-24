package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ENTITY_DISPLAYED_ID;

import dog.pawbook.logic.commands.ViewCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns a ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        // Check if argument is empty
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        // Check if string is an int
        int id;
        try {
            id = Integer.parseInt(trimmedArgs);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_ENTITY_DISPLAYED_ID);
        }
        return new ViewCommand(id);
    }
}
