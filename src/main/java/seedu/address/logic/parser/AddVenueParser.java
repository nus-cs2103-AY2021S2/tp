package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CAPACITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddVenue;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Venue;

/**
 * Parses input arguments and creates a new AddVenue object
 */
public class AddVenueParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddVenue
     * and returns an AddVenue object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddVenue parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CAPACITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CAPACITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddVenue.MESSAGE_USAGE));
        }

        String name = ParserUtil.parseVenueName(argMultimap.getValue(PREFIX_NAME).get());
        int capacity = ParserUtil.parseCapacity(argMultimap.getValue(PREFIX_CAPACITY).get());

        Venue venue = new Venue(name, capacity);

        return new AddVenue(venue);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
