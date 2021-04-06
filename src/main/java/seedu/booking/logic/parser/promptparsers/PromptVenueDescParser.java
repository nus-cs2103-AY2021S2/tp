package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.PromptVenueDescCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;

public class PromptVenueDescParser implements Parser<PromptVenueDescCommand> {

    public static final String DEFAULT_VENUE_DESCRIPTION = "No description provided.";

    /**
     * Parses the given {@code String} of arguments for description in the context of adding a venue.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptVenueDescCommand parse(String args) throws ParseException {
        String description;
        if (args.equals("")) {
            description = DEFAULT_VENUE_DESCRIPTION;
        } else {
            description = ParserUtil.parseDescription(args);
        }

        return new PromptVenueDescCommand(description);
    }
}
