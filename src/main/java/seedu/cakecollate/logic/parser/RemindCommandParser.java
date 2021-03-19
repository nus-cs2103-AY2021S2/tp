package seedu.cakecollate.logic.parser;

import static seedu.cakecollate.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cakecollate.commons.util.StringUtil;
import seedu.cakecollate.logic.commands.RemindCommand;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.ReminderDatePredicate;

/**
 * Parses input argument and creates a new RemindCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemindCommand
     * and returns a RemindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public RemindCommand parse(String args) throws ParseException {
        String trimmedArg = args.trim();
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
        }
        if (!StringUtil.isUnsignedInteger(trimmedArg)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
        }
        return new RemindCommand(new ReminderDatePredicate(Integer.parseInt(trimmedArg)));
    }
}
