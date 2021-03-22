package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.DeleteMultipleCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteMultipleCommand object
 */
public class DeleteMultipleCommandParser implements Parser<DeleteMultipleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMultipleCommand
     * and returns a DeleteMultipleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteMultipleCommand parse(String args) throws ParseException {
        List<Index> indexes = ParserUtil.parseMultipleIndex(args);
        return new DeleteMultipleCommand(indexes);
    }
}
