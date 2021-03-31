package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;

import java.util.stream.Stream;

import seedu.booking.logic.commands.FilterBookingByBookerCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.BookingContainsBookerPredicate;
import seedu.booking.model.person.Email;

/**
 * Parses input arguments and creates a new FilterBookingByBookerCommand object.
 */
public class FilterBookingByBookerCommandParser implements Parser<FilterBookingByBookerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterBookingByBookerCommand
     * and returns a FilterBookingByBookerCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FilterBookingByBookerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL);
        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL)
                || argMultimap.getValue(PREFIX_EMAIL).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterBookingByBookerCommand.MESSAGE_USAGE));
        }

        Email email = new Email(argMultimap.getValue(PREFIX_EMAIL).get());

        return new FilterBookingByBookerCommand(new BookingContainsBookerPredicate(email));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
