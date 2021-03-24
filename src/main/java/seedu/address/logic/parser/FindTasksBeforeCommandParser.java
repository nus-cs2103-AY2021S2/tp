package seedu.address.logic.parser;

import seedu.address.logic.commands.FindTasksBeforeCommand;
import seedu.address.logic.commands.FindTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.DeadlineBeforeDatePredicate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class FindTasksBeforeCommandParser implements Parser<FindTasksBeforeCommand> {
    /**
     * Parses the given {@code String} deadine in the context of the FindTasksBeforeCommand
     * and returns a FindTasksBefore Command object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTasksBeforeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTasksCommand.MESSAGE_USAGE));
        }
        if(!Deadline.isValidDeadline(trimmedArgs)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }

        return new FindTasksBeforeCommand(new DeadlineBeforeDatePredicate(trimmedArgs));
    }
}
