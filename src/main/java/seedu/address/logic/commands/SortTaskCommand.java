package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class SortTaskCommand extends Command {
    public static final String COMMAND_WORD = "sort_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts tasks based on given parameter.\n"
            + "Supported parameters: \"name\", \"deadline\", \"priority\", \"completion\" \n"
            + "Example: " + COMMAND_WORD + " by/name";

    public static final String MESSAGE_SORT_TASK_SUCCESS = "Sorted Tasks";

    private final String comparingVar;


    public SortTaskCommand(String comparingVar) {
        this.comparingVar = comparingVar;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortTasks(comparingVar);
        return new CommandResult(MESSAGE_SORT_TASK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTaskCommand // instanceof handles nulls
                && comparingVar.equals(((SortTaskCommand) other).comparingVar)); // state check
    }
}
