package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.VenueNamePromptCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.VenueName;

public class VenueNamePromptParser implements Parser<VenueNamePromptCommand> {

    public VenueNamePromptCommand parse(String args) throws ParseException {
        return new VenueNamePromptCommand(new VenueName(args));
    }
}
