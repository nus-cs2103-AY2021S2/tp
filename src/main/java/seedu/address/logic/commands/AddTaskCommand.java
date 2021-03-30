package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignee.Assignee;
import seedu.address.model.task.Task;

/**
 * Adds a task to HeyMatez.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "addTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new task to HeyMatez dashboard.\n"
            + "Parameters: "
            + "TITLE "
            + PREFIX_DESCRIPTION + " DESCRIPTION "
            + PREFIX_DEADLINE + " DEADLINE "
            + PREFIX_STATUS + " TASK STATUS "
            + PREFIX_PRIORITY + " PRIORITY "
            + "[" + PREFIX_ASSIGNEE + "ASSIGNEE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Plan board meeting "
            + PREFIX_DESCRIPTION + " Draft meeting agenda and proposal for board meeting "
            + PREFIX_DEADLINE + "2021-05-02 "
            + PREFIX_STATUS + " completed "
            + PREFIX_PRIORITY + " high "
            + PREFIX_ASSIGNEE + " Rachel";

    public static final String MESSAGE_SUCCESS = "New Task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "A task with the same name already exists in the HeyMatez "
            + "task board! Pick another name!";

    private final Task toAddTask;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAddTask = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAddTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (!model.checkAssignees(toAddTask)) {
            throw new CommandException(Assignee.MESSAGE_CONSTRAINTS);
        }

        model.addTask(toAddTask);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAddTask.equals(((AddTaskCommand) other).toAddTask));
    }
}
