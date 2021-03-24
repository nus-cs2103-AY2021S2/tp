package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKER;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.stream.Stream;

import seedu.booking.logic.commands.CreateBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CreateBookingCommandParser implements Parser<CreateBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddBookingCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKER, PREFIX_VENUE,
                        PREFIX_DESCRIPTION, PREFIX_BOOKING_START, PREFIX_BOOKING_END);

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOKER, PREFIX_VENUE,
                PREFIX_DESCRIPTION, PREFIX_BOOKING_START, PREFIX_BOOKING_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateBookingCommand.MESSAGE_USAGE));
        }

        Email bookerEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_BOOKER).get());
        VenueName venueName = ParserUtil.parseVenueName(argMultimap.getValue(PREFIX_VENUE).get());
        Description description = ParserUtil.parseBookingDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        StartTime bookingStart = ParserUtil.parseBookingStart(argMultimap.getValue(PREFIX_BOOKING_START).get());
        EndTime bookingEnd = ParserUtil.parseBookingEnd(argMultimap.getValue(PREFIX_BOOKING_END).get());

        Booking booking = new Booking(bookerEmail, venueName, description,
                bookingStart, bookingEnd);

        return new CreateBookingCommand(booking);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
