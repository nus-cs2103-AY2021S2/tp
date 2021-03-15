package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

import static java.util.Objects.requireNonNull;

/**
 * Adds a task to Teaching Assistant.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "tadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " etc";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand
     * @param task task object
     */
    public AddTaskCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddTaskCommand
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
