package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.logic.parser.CliSyntax.*;

import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.task.Task;

/**
 * Adds a task to module book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to module book. \n"
            + "Parameters: "
            + PREFIX_TASK_NAME + "TASK NAME "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_WORKLOAD + "WORKLOAD "
            + "[" + PREFIX_RECURRENCE + "RECURRENCE]"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_NAME + "TP v1.2 "
            + PREFIX_DEADLINE + "2021-01-30 12:00 "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_DESCRIPTION + "Finish basic commands for TP "
            + PREFIX_WORKLOAD + "1 "
            + PREFIX_RECURRENCE + "monthly "
            + PREFIX_TAG + "highPriority ";

    public static final String MESSAGE_SUCCESS = "New task added successfully:\n%1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task with this name and module exists in the module book";
    private final Task toAdd;
    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(toAdd) && !toAdd.isRecurring()) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
