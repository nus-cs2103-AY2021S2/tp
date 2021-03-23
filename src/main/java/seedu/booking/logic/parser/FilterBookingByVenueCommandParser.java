package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.stream.Stream;

import seedu.booking.logic.commands.FilterBookingByVenueCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.BookingContainsVenuePredicate;

/**
 * Parses input arguments and creates a new FilterBookingByVenueCommand object.
 */
public class FilterBookingByVenueCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterBookingByVenueCommandParser
     * and returns a FilterBookingByVenueCommandParser object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FilterBookingByVenueCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VENUE);
        if (!arePrefixesPresent(argMultimap, PREFIX_VENUE)
                || argMultimap.getValue(PREFIX_VENUE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterBookingByVenueCommand.MESSAGE_USAGE));
        }

        String venueName = argMultimap.getValue(PREFIX_VENUE).get();
        assert venueName == "" : "venueName should not be empty";

        return new FilterBookingByVenueCommand(new BookingContainsVenuePredicate(venueName));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
