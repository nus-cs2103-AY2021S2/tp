package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DoneCommand object
 */
public class DoneCommandParser implements Parser<DoneCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DoneCommand
     * and returns a DoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE), pe);
        }
    }
}
