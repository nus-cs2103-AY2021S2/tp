package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.booking.logic.commands.FilterBookingByDateCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.BookingWithinDatePredicate;

public class FilterBookingByDateCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterBookingByDateCommand
     * and returns a FilterBookingByDateCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FilterBookingByDateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE);
        if (!arePrefixesPresent(argMultimap, PREFIX_DATE)
                || argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterBookingByDateCommand.MESSAGE_USAGE));
        }

        String dateString = argMultimap.getValue(PREFIX_DATE).get();
        LocalDate date;

        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterBookingByDateCommand.MESSAGE_USAGE));
        }
        assert date == null : "date should not be null";

        return new FilterBookingByDateCommand(new BookingWithinDatePredicate(date));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
