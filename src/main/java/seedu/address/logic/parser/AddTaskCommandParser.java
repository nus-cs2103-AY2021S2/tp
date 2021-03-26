package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;
import seedu.address.model.task.Title;

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
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_STATUS, PREFIX_PRIORITY);

        try {
            assert arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DEADLINE)
                    : "You must input a task description and deadline!";
        } catch (AssertionError e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Title title;
        try {
            title = ParserUtil.parseTitle(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Title.MESSAGE_CONSTRAINTS), pe);
        }

        Description description;
        try {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Description.MESSAGE_CONSTRAINTS),
                    pe);
        }

        Deadline deadline;
        try {
            deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Deadline.MESSAGE_CONSTRAINTS), pe);
        }

        Task task = new Task(title, description, deadline);

        TaskStatus status = null;

        if (arePrefixesPresent(argMultimap, PREFIX_STATUS)) {
            try {
                status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskStatus.MESSAGE_CONSTRAINTS),
                        pe);
            }

            task = new Task(title, description, deadline, status);
        }

        Priority priority;

        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            try {
                priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, Priority.MESSAGE_CONSTRAINTS),
                        pe);
            }

            task = status != null
                    ? new Task(title, description, deadline, status, priority)
                    : new Task(title, description, deadline, priority);
        }
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
