package seedu.taskify.logic.parser;

import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.isDeletingTasksByStatus;
import static seedu.taskify.logic.parser.ParserUtil.parseInputToStatus;

import java.util.List;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.DeleteMultipleCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.task.Status;

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
        if (isDeletingTasksByStatus(args)) {
            Status status = parseInputToStatus(args);
            return new DeleteMultipleCommand(status);
        }
        List<Index> indexes = ParserUtil.parseMultipleIndex(args);
        return new DeleteMultipleCommand(indexes);
    }
}
