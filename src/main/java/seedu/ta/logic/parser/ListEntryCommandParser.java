package seedu.ta.logic.parser;

import static seedu.ta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.ta.logic.commands.ListEntryCommand;
import seedu.ta.logic.parser.exceptions.ParseException;
import seedu.ta.model.entry.ListEntryFormatPredicate;

/**
 * Parses input arguments and creates a new ListEntryCommand object.
 */
public class ListEntryCommandParser implements Parser<ListEntryCommand> {

    /**
     * Parses the give {@code String} of arguments in the context of the ListEntryCommand
     * and returns a ListEntryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ListEntryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.equals("day") || trimmedArgs.equals("week") || trimmedArgs.isEmpty()) {
            return new ListEntryCommand(new ListEntryFormatPredicate(trimmedArgs));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEntryCommand.MESSAGE_USAGE));
        }
    }
}
