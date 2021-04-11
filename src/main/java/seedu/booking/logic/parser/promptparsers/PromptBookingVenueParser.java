package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.PromptBookingVenueCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.VenueName;


public class PromptBookingVenueParser implements Parser<PromptBookingVenueCommand> {

    /**
     * Parses user input for venue name in booking
     */
    public PromptBookingVenueCommand parse(String args) throws ParseException {

        VenueName venueName;
        venueName = ParserUtil.parseVenueName(args);

        return new PromptBookingVenueCommand(venueName);
    }

}
