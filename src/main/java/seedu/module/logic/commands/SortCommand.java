package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.module.model.Model;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all tasks by deadline";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortTasks();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
