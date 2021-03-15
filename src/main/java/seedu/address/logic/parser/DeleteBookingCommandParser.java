package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKINGID;

import java.util.stream.Stream;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteBookingCommandParser implements Parser<DeleteBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBookingCommand
     * and returns a DeleteBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_BOOKINGID);

        int bookingId;

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOKINGID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteBookingCommand.MESSAGE_USAGE));
        }

        try {
            bookingId = ParserUtil.parseBookingId(argMultimap.getValue(PREFIX_BOOKINGID).get());
        } catch (ParseException pe) {
            System.out.println("Exception throwing\n");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteBookingCommand.MESSAGE_USAGE), pe);
        }
        return new DeleteBookingCommand(bookingId);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
