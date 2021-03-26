package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddBookingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
import seedu.address.model.residence.ResidenceName;

/**
 * Parses input arguments and creates a new AddBookingCommand object
 */
public class AddBookingCommandParser implements Parser<AddBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddBookingCommand
     * and returns an AddBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_BOOKING_START_TIME, PREFIX_BOOKING_END_TIME);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_BOOKING_START_TIME, PREFIX_BOOKING_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE));
        }


        Name name = ParserUtil.parseVisitorName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Booking booking = ParserUtil.parseBooking(name, phone,
                argMultimap.getValue(PREFIX_BOOKING_START_TIME).get(), argMultimap.getValue(PREFIX_BOOKING_END_TIME).get());

        return new AddBookingCommand(index, booking);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
