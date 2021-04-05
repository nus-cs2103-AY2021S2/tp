package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_VENUE_NAME;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;

import seedu.booking.logic.commands.PromptBookingVenueCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.VenueName;

public class VenueNamePromptParser implements Parser<PromptBookingVenueCommand> {

    public PromptBookingVenueCommand parse(String args) throws ParseException {

        VenueName venueName;

        //try {
        venueName = ParserUtil.parseVenueName(args);
        /*} catch (Exception ex) {
            throw new ParseException(MESSAGE_INVALID_VENUE_NAME + VenueName.MESSAGE_CONSTRAINTS
                    + PROMPT_MESSAGE_TRY_AGAIN);
        }*/
        return new PromptBookingVenueCommand(venueName);
    }
}
