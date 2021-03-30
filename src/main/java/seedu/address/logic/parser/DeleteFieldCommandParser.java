//@@author mesyeux
package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteFieldCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteFieldCommand object
 */
public class DeleteFieldCommandParser implements Parser<DeleteFieldCommand> {

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteFieldCommand
     * and returns a DeleteFieldCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteFieldCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.split(" ");
            if (splitArgs.length > 3) {
                logger.info("Too many arguments detected: " + args);
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, " Only one field can be deleted"
                                + " at any one time."));
            } else if (splitArgs.length < 3) {
                logger.info("Not enough arguments detected: " + args);
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, " Please specify a field to delete."));
            }
            assert splitArgs.length == 3;
            String indexToParse = splitArgs[1];
            String field = splitArgs[2];
            Index index = ParserUtil.parseIndex(indexToParse);
            return new DeleteFieldCommand(index, field);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFieldCommand.MESSAGE_USAGE), pe);
        }
    }
}
