package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

public class SortByDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "sortDeadlines";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts Tasks by deadline, with the nearest deadline"
            + " being the first \n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortTasksByDeadline();
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));

    }
}
