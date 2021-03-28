package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindMemberTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.TaskContainsAssigneePredicate;

/**
 * Parses input arguments and creates a new FindMemberTasksCommand object
 */
public class FindMemberTasksCommandParser implements Parser<FindMemberTasksCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMemberTasksCommand
     * and returns a FindMemberTasksCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMemberTasksCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindMemberTasksCommand.MESSAGE_USAGE));
        }

        // Check if name is valid
        ParserUtil.parseAssignee(trimmedArgs);

        return new FindMemberTasksCommand(new TaskContainsAssigneePredicate(trimmedArgs));
    }

}
