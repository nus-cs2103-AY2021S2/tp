package seedu.cakecollate.logic.parser;

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
        int days;
        try {
            days = ParserUtil.parseDays(args);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage());
        }
        return new RemindCommand(new ReminderDatePredicate(days));
    }
}
