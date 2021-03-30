package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE;

import seedu.address.logic.commands.FindFreeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.common.Date;

/**
 * Parses input arguments and creates a new FindFreeTimeCommand object
 */
public class FindFreeTimeCommandParser implements Parser<FindFreeTimeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindFreeTimeCommand
     * and returns a FindFreeTimeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public FindFreeTimeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFreeTimeCommand.MESSAGE_USAGE));
        }

        String dateStr = trimmedArgs.split(" ")[0];
        if (Date.isValidDate(dateStr)) {
            Date date = new Date(dateStr);
            return new FindFreeTimeCommand(date);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_DATE));
        }

    }
}
