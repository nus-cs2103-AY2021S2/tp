package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.booking.commons.core.Messages.MESSAGE_PROMPT_TRYAGAIN;

import seedu.booking.logic.commands.BookingStartPromptCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.StartTime;

public class BookingStartPromptParser implements Parser<BookingStartPromptCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the starting date
     * and returns a StartTime object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BookingStartPromptCommand parse(String args) throws ParseException {
        StartTime dateTime;

        try {
            dateTime = ParserUtil.parseBookingStart(args);
        } catch (Exception ex) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT + MESSAGE_PROMPT_TRYAGAIN);
        }

        return new BookingStartPromptCommand(dateTime);
    }
}
