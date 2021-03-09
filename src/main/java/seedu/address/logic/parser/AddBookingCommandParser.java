package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKINGEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKINGSTART;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Venue;

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
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKER, PREFIX_VENUE,
                        PREFIX_DESCRIPTION, PREFIX_BOOKINGSTART, PREFIX_BOOKINGEND);

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOKER, PREFIX_VENUE,
                PREFIX_DESCRIPTION, PREFIX_BOOKINGSTART, PREFIX_BOOKINGEND)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddBookingCommand.MESSAGE_USAGE));
        }


        String booker = ParserUtil.parseBooker(argMultimap.getValue(PREFIX_BOOKER).get());
        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
        String description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        LocalDateTime bookingStart = ParserUtil.parseBookingStart(argMultimap.getValue(PREFIX_BOOKINGSTART).get());
        LocalDateTime bookingEnd = ParserUtil.parseBookingEnd(argMultimap.getValue(PREFIX_BOOKINGEND).get());

        Booking booking = new Booking(booker, venue, description,
                bookingStart, bookingEnd, 1);


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
