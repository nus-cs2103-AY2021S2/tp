package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.CountdownCommand;
import seedu.address.logic.commands.DeleteFieldCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CountdownCommand object
 */
public class CountdownCommandParser implements Parser<CountdownCommand> {

    public static final String MESSAGE_INDEX_OVERFLOW = "Index should be positive and within the range of the list.\n";

    /**
     * Parses the given {@code String} of arguments in the context of the CountdownCommand
     * and returns a CountdownCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CountdownCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.split(" ");
            if (splitArgs.length > 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_TOO_MANY_ARGUMENTS));
            } else if (splitArgs.length < 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, Messages.MESSAGE_TOO_LITTLE_ARGUMENTS));
            }
            assert splitArgs.length == 2;
            String indexToParse = splitArgs[1];
            if (!StringUtil.isNonZeroUnsignedInteger(indexToParse)) {
                throw new ParseException(MESSAGE_INDEX_OVERFLOW);
            }
            Index index = ParserUtil.parseIndex(indexToParse);
            return new CountdownCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CountdownCommand.MESSAGE_USAGE), pe);
        }
    }
}
