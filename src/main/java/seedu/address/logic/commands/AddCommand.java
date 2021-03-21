package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGSCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Adds a task to the planner.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the planner. \n\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_STARTTIME + "STARTTIME "
            + PREFIX_RECURRINGSCHEDULE + "RECURRINGSCHEDULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TAG + "TAG]...\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "This is a task "
            + PREFIX_DEADLINE + "12 Oct 2012 "
            + PREFIX_STARTTIME + "15:30"
            + PREFIX_RECURRINGSCHEDULE + "[10 Mar 2019][Mon][weekly] "
            + PREFIX_DESCRIPTION + "This is the task's description "
            + PREFIX_TAG + "tag1 "
            + PREFIX_TAG + "tag2";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_TITLE + "TITLE "
            + "[" + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_STARTTIME + "STARTTIME "
            + PREFIX_RECURRINGSCHEDULE + "RECURRINGSCHEDULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_TAG + "TAG]\n";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the planner";

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

        boolean isDuplicateTask = model.hasTask(toAdd);
        if (isDuplicateTask) {
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
