package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKER;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_END;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_ORIGINAL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_BOOKING_START;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_VENUE;

import java.util.stream.Stream;

import seedu.booking.logic.commands.EditBookingCommand;
import seedu.booking.logic.commands.EditBookingCommand.EditBookingDescriptor;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.Id;

public class EditBookingCommandParser implements Parser<EditBookingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditBookingCommand
     * and returns an EditBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public EditBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ORIGINAL, PREFIX_BOOKER, PREFIX_VENUE,
                        PREFIX_DESCRIPTION, PREFIX_BOOKING_START, PREFIX_BOOKING_END);

        Id id;

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOKING_ORIGINAL)
                || argMultimap.getValue(PREFIX_BOOKING_ORIGINAL).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE));
        }

        id = ParserUtil.parseBookingId(argMultimap.getValue(PREFIX_BOOKING_ORIGINAL).get());

        EditBookingDescriptor editBookingDescriptor = new EditBookingDescriptor();
        if (argMultimap.getValue(PREFIX_BOOKING_ID).isPresent()) {
            editBookingDescriptor.setId(ParserUtil.parseBookingId(
                    argMultimap.getValue(PREFIX_BOOKING_ID).get()));
        }

        if (argMultimap.getValue(PREFIX_BOOKER).isPresent()) {
            editBookingDescriptor.setBookerEmail(ParserUtil.parseEmail(
                    argMultimap.getValue(PREFIX_BOOKER).get()));
        }

        if (argMultimap.getValue(PREFIX_VENUE).isPresent()) {
            editBookingDescriptor.setVenueName(ParserUtil.parseVenueName(
                    argMultimap.getValue(PREFIX_VENUE).get()));
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editBookingDescriptor.setDescription(ParserUtil.parseBookingDescription(
                    argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }

        if (argMultimap.getValue(PREFIX_BOOKING_START).isPresent()) {
            editBookingDescriptor.setBookingStart(ParserUtil.parseBookingStart(
                    argMultimap.getValue(PREFIX_BOOKING_START).get()));
        }

        if (argMultimap.getValue(PREFIX_BOOKING_END).isPresent()) {
            editBookingDescriptor.setBookingEnd(ParserUtil.parseBookingEnd(
                    argMultimap.getValue(PREFIX_BOOKING_END).get()));
        }

        if (!editBookingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBookingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBookingCommand(id, editBookingDescriptor);
    }



    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
