package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DEADLINE;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DEADLINE_FORMAT;

import seedu.heymatez.logic.commands.FindTasksBeforeCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.DeadlineBeforeDatePredicate;

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
        if (!Deadline.isValidFormat(trimmedArgs)) {
            throw new ParseException(MESSAGE_INVALID_TASK_DEADLINE_FORMAT + Deadline.MESSAGE_CONSTRAINTS);
        }
        if (!Deadline.isValidDeadline(trimmedArgs)) {
            throw new ParseException(MESSAGE_INVALID_TASK_DEADLINE + Deadline.MESSAGE_CONSTRAINTS);
        }
        return new FindTasksBeforeCommand(new DeadlineBeforeDatePredicate(trimmedArgs));
    }
}
