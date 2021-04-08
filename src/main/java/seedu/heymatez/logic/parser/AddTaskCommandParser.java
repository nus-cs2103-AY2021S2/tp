package seedu.heymatez.logic.parser;

import static seedu.heymatez.commons.core.Messages.*;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_ASSIGNEE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Set;
import java.util.stream.Stream;

import seedu.heymatez.logic.commands.AddTaskCommand;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.assignee.Assignee;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Description;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.model.task.TaskStatus;
import seedu.heymatez.model.task.Title;

/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_STATUS,
                        PREFIX_PRIORITY, PREFIX_ASSIGNEE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DEADLINE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getPreamble());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        TaskStatus status = TaskStatus.UNCOMPLETED;

        if (arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
            try {
                status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
            } catch (ParseException pe) {
                throw new ParseException(MESSAGE_INVALID_TASK_STATUS + TaskStatus.MESSAGE_CONSTRAINTS,
                        pe);
            }
        }

        Priority priority = Priority.UNASSIGNED;

        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            try {
                priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            } catch (ParseException pe) {
                throw new ParseException(MESSAGE_INVALID_TASK_PRIORITY + Priority.MESSAGE_CONSTRAINTS,
                        pe);
            }
        }

        Set<Assignee> assigneeList = ParserUtil.parseAssignees(argMultimap.getAllValues(PREFIX_ASSIGNEE));

        Task task = new Task(title, description, deadline, status, priority, assigneeList);

        return new AddTaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
