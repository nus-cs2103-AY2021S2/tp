package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.booking.commons.core.Messages;
import seedu.booking.logic.commands.FilterBookingByDateCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.BookingWithinDatePredicate;

public class FilterBookingByDateCommandParser {

    public FilterBookingByDateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE);
        if (!arePrefixesPresent(argMultimap, PREFIX_DATE)
                || argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterBookingByDateCommand.MESSAGE_USAGE));
        }

        String dateString = argMultimap.getValue(PREFIX_DATE).get();
        LocalDate date;

        try {
            date = LocalDate.parse(dateString);
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterBookingByDateCommand.MESSAGE_USAGE));
        }

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
