package seedu.heymatez.logic.parser;

import java.util.Arrays;

import seedu.heymatez.commons.core.Messages;
import seedu.heymatez.logic.commands.FindTasksCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.task.TaskContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindTasksCommand object
 */
public class FindTasksCommandParser implements Parser<FindTasksCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindTasksCommand
     * and returns a FindTasksCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTasksCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindTasksCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        return new FindTasksCommand(new TaskContainsKeywordPredicate(Arrays.asList(keywords)));
    }
}
