package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBookingCommand;
import seedu.address.logic.commands.EditBookingCommand.EditBookingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditBookingCommandParser implements Parser<EditBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditBookingCommand
     * and returns an EditBookingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESIDENCE, PREFIX_BOOKING,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_BOOKING_START_DATE, PREFIX_BOOKING_END_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESIDENCE, PREFIX_BOOKING)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_RESIDENCE).get().isEmpty()
                || argMultimap.getValue(PREFIX_BOOKING).get().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE));
        }

        Index residenceIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RESIDENCE).get());
        Index bookingIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_BOOKING).get());

        EditBookingDescriptor editBookingDescriptor = new EditBookingDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editBookingDescriptor.setName(
                    ParserUtil.parseTenantName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editBookingDescriptor.setPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_BOOKING_START_DATE).isPresent()) {
            editBookingDescriptor.setStartDate(
                    ParserUtil.parseDate(argMultimap.getValue(PREFIX_BOOKING_START_DATE).get())
            );
        }
        if (argMultimap.getValue(PREFIX_BOOKING_END_DATE).isPresent()) {
            editBookingDescriptor.setEndDate(
                    ParserUtil.parseDate(argMultimap.getValue(PREFIX_BOOKING_END_DATE).get())
            );
        }
        if (!editBookingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBookingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBookingCommand(residenceIndex, bookingIndex, editBookingDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
