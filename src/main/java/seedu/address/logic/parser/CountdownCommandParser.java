package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CountdownCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CountdownCommand object
 */
public class CountdownCommandParser implements Parser<CountdownCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CountdownCommand
     * and returns a CountdownCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CountdownCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new CountdownCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CountdownCommand.MESSAGE_USAGE), pe);
        }
    }
}
