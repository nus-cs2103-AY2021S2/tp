package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.booking.commons.core.index.Index;
import seedu.booking.logic.commands.DeleteBookingCommand;
import seedu.booking.logic.parser.exceptions.ParseException;

public class DeleteBookingCommandParser implements Parser<DeleteBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBookingCommand
     * and returns a DeleteBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index index;
        if (args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));
        }

        index = ParserUtil.parseIndex(args);
        return new DeleteBookingCommand(index);
    }
}
