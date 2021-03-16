package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.ListTaskFormatPredicate;

/**
 * Parses input arguments and creates a new ListTaskCommand object
 */
public class ListTaskCommandParser implements Parser<ListTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListTaskCommand
     * and returns a ListTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        ListTaskCommand listTaskCommand = new ListTaskCommand(new ListTaskFormatPredicate(trimmedArgs));
        if (trimmedArgs.equals("day") || trimmedArgs.equals("week") || trimmedArgs.isEmpty()) {
            return listTaskCommand;
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTaskCommand.MESSAGE_USAGE));
        }
    }
}
