package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.PromptBookingVenueCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.VenueName;

public class VenueNamePromptParser implements Parser<PromptBookingVenueCommand> {

    public PromptBookingVenueCommand parse(String args) throws ParseException {
        return new PromptBookingVenueCommand(new VenueName(args));
    }
}
