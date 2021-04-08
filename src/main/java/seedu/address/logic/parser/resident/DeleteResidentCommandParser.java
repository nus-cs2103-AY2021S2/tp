package seedu.address.logic.parser.resident;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteResidentCommand object
 */
public class DeleteResidentCommandParser implements Parser<DeleteResidentCommand> {
    private final Logger logger = LogsCenter.getLogger(DeleteResidentCommandParser.class);
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteResidentCommand
     * and returns a DeleteResidentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteResidentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteResidentCommand(index);
        } catch (ParseException pe) {
            logger.warning("Failed to parse index for rdel command");
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteResidentCommand.MESSAGE_USAGE), pe);
        }
    }

}
