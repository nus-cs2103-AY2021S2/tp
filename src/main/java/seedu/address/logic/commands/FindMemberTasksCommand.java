package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.task.TaskContainsAssigneePredicate;

/**
 * Finds and lists all tasks in HEY MATEz who contains the assignee with the given name
 */
public class FindMemberTasksCommand extends Command {

    public static final String COMMAND_WORD = "findTasksFor";

    public static final String MESSAGE_SUCCESS = "Listed all Tasks belonging to specified name!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the tasks assigned to a Member.\n"
            + "Parameters: " + "NAME \n"
            + "Example: " + COMMAND_WORD + " Rachel Tan";

    private final TaskContainsAssigneePredicate predicate;

    public FindMemberTasksCommand(TaskContainsAssigneePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMemberTasksCommand // instanceof handles nulls
                && predicate.equals(((FindMemberTasksCommand) other).predicate)); // state check
    }
}

