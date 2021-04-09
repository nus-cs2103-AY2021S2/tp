package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteBookingCommandParser implements Parser<DeleteBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBookingCommand
     * and returns a DeleteBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBookingCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RESIDENCE, PREFIX_BOOKING);

        if (!arePrefixesPresent(argMultimap, PREFIX_RESIDENCE, PREFIX_BOOKING)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_RESIDENCE).get().isEmpty()
                || argMultimap.getValue(PREFIX_BOOKING).get().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));
        }

        Index residenceIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_RESIDENCE).get());
        Index bookingIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_BOOKING).get());
        return new DeleteBookingCommand(residenceIndex, bookingIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
