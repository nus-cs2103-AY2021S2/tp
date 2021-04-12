package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BlacklistCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code BlacklistCommand} object
 */
public class BlacklistCommandParser implements Parser<BlacklistCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code BlacklistCommand}
     * and returns a {@code BlacklistCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BlacklistCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new BlacklistCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, BlacklistCommand.MESSAGE_USAGE), pe);
        }
    }
}
