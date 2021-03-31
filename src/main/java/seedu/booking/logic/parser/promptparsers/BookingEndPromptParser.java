package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;

import seedu.booking.logic.commands.PromptBookingEndCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.EndTime;

public class BookingEndPromptParser implements Parser<PromptBookingEndCommand> {

    /**
     * Adds a booking venue to the system.
     */
    public PromptBookingEndCommand parse(String args) throws ParseException {
        EndTime endTime;

        try {
            endTime = ParserUtil.parseBookingEnd(args);
        } catch (Exception ex) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT + PROMPT_MESSAGE_TRY_AGAIN);
        }

        return new PromptBookingEndCommand(endTime);
    }
}
