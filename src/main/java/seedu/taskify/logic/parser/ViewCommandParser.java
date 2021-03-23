package seedu.taskify.logic.parser;

import seedu.taskify.logic.commands.ViewCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.predicates.TaskHasSameDatePredicate;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        Date inputDate = new Date(trimmedArgs);

        return new ViewCommand(new TaskHasSameDatePredicate(inputDate));
    }
}
