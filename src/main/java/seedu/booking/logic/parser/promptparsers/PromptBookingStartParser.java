package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;

import seedu.booking.logic.commands.PromptBookingStartCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.StartTime;

public class PromptBookingStartParser implements Parser<PromptBookingStartCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the starting date
     * and returns a StartTime object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptBookingStartCommand parse(String args) throws ParseException {
        StartTime dateTime;

        try {
            dateTime = ParserUtil.parseBookingStart(args);
        } catch (Exception ex) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT + PROMPT_MESSAGE_TRY_AGAIN);
        }

        return new PromptBookingStartCommand(dateTime);
    }
}
