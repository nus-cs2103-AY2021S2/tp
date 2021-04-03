package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.booking.logic.commands.AddBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.VenueName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddBookingCommandParser implements Parser<AddBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddBookingCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EMAIL, PREFIX_VENUE,
                        PREFIX_DESCRIPTION, PREFIX_BOOKING_START, PREFIX_BOOKING_END, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL, PREFIX_VENUE,
                PREFIX_DESCRIPTION, PREFIX_BOOKING_START, PREFIX_BOOKING_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddBookingCommand.MESSAGE_USAGE));
        }

        Email bookerEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        VenueName venueName = ParserUtil.parseVenueName(argMultimap.getValue(PREFIX_VENUE).get());
        Description description = ParserUtil.parseBookingDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        StartTime bookingStart = ParserUtil.parseBookingStart(argMultimap.getValue(PREFIX_BOOKING_START).get());
        EndTime bookingEnd = ParserUtil.parseBookingEnd(argMultimap.getValue(PREFIX_BOOKING_END).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));


        Booking booking = new Booking(bookerEmail, venueName, description,
                bookingStart, bookingEnd, tagList);

        return new AddBookingCommand(booking);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
