package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import java.util.stream.Stream;

import seedu.booking.logic.commands.FindBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.BookingIdContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindBookingCommand object.
 */
public class FindBookingCommandParser implements Parser<FindBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBookingCommand
     * and returns a FindBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID);

        String bookingId;

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOKING_ID)
                || argMultimap.getValue(PREFIX_BOOKING_ID).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookingCommand.MESSAGE_USAGE));
        }

        bookingId = String.valueOf(ParserUtil
                .parseBookingId(argMultimap.getValue(PREFIX_BOOKING_ID).get()));
        return new FindBookingCommand(new BookingIdContainsKeywordsPredicate(bookingId));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
