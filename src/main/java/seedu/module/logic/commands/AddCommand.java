package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.task.Task;

/**
 * Adds a task to the module book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the module book. "
            + "Parameters: "
            + PREFIX_NAME + "TASK NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "v1.2 "
            + PREFIX_DEADLINE + "2021-01-30 12:00 "
            + PREFIX_MODULE + "CS2103T "
            + PREFIX_DESCRIPTION + "Finish basic commands for TP "
            + PREFIX_TAG + "highPriority ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the module book";
    public static final String MESSAGE_NOT_IMPLEMENTED = "Add feature is not yet implemented yet!";

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
        if (model.hasTask(toAdd)) {
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
