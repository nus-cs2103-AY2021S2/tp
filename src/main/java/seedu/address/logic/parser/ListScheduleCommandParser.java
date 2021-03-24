package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.schedule.ListScheduleFormatPredicate;

/**
 * Parses input arguments and creates a new ListScheduleCommand object
 */
public class ListScheduleCommandParser implements Parser<ListScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListScheduleCommand
     * and returns a ListScheduleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListScheduleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || (!trimmedArgs.equals("day") && !trimmedArgs.equals("week"))) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListScheduleCommand.MESSAGE_USAGE));
        }

        return new ListScheduleCommand(new ListScheduleFormatPredicate(trimmedArgs));
    }
}
