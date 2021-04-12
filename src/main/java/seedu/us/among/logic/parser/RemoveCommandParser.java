package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;

import java.util.logging.Logger;

import seedu.us.among.commons.core.LogsCenter;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.commons.util.StringUtil;
import seedu.us.among.logic.commands.RemoveCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {
    private static final Logger logger = LogsCenter.getLogger(RemoveCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns a RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args, RemoveCommand.MESSAGE_USAGE);
            return new RemoveCommand(index);
        } catch (ParseException pe) {
            logger.warning(StringUtil.getDetails(pe));
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_ERROR, pe.getMessage(),
                            RemoveCommand.MESSAGE_USAGE), pe);
        }
    }

}
