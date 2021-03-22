package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.booking.logic.commands.FilterBookingByBookerCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.BookingContainsBookerPredicate;

/**
 * Parses input arguments and creates a new FilterBookingByBookerCommand object.
 */
public class FilterBookingByBookerCommandParser {

    public FilterBookingByBookerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterBookingByBookerCommand.MESSAGE_USAGE));
        }

        String bookerName = argMultimap.getValue(PREFIX_NAME).get();

        return new FilterBookingByBookerCommand(new BookingContainsBookerPredicate(bookerName));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}