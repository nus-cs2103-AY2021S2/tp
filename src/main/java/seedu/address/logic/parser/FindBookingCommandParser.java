package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING;


import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteBookingCommand;
import seedu.address.logic.commands.FindBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.BookingIdContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOKING);

        int bookingId;
        try {
            bookingId = ParserUtil.parseBookingId(argMultimap.getValue(PREFIX_BOOKING).get());
            return new FindBookingCommand(new BookingIdContainsKeywordsPredicate(bookingId));
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindBookingCommand.MESSAGE_USAGE), ive);
        }

    }
}