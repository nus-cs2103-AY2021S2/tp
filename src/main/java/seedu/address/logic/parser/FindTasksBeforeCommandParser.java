package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTasksBeforeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DeadlineBeforeDatePredicate;

/**
 * Parses input arguments and creates a new FindTasksBeforeCommand object
 */
public class FindTasksBeforeCommandParser implements Parser<FindTasksBeforeCommand> {
    /**
     * Parses the given {@code String} deadline in the context of the FindTasksBeforeCommand
     * and returns a FindTasksBefore Command object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindTasksBeforeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTasksBeforeCommand.MESSAGE_USAGE));
        }
        if (!Deadline.isValidDeadline(trimmedArgs)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Deadline.MESSAGE_CONSTRAINTS));
        }

        return new FindTasksBeforeCommand(new DeadlineBeforeDatePredicate(trimmedArgs));
    }
}
