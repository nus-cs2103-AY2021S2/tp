package seedu.booking.logic.parser.promptparsers;

import seedu.booking.logic.commands.PromptVenueCapacityCommand;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.venue.Capacity;

public class PromptVenueCapacityParser implements Parser<PromptVenueCapacityCommand> {
    public static final int DEFAULT_CAPACITY = 10;

    /**
     * Parses the given {@code String} of arguments for capacity in the context of adding a venue.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptVenueCapacityCommand parse(String args) throws ParseException {
        Capacity capacity;
        if (args.equals("")) {
            capacity = new Capacity(DEFAULT_CAPACITY);
        } else {
            capacity = ParserUtil.parseCapacity(args);
        }

        return new PromptVenueCapacityCommand(capacity);
    }
}
