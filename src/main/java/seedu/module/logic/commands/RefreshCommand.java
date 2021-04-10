package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.module.model.Model;

/**
 * Refresh the deadline of the module book.
 */
public class RefreshCommand extends Command {

    public static final String COMMAND_WORD = "refresh";

    public static final String MESSAGE_SUCCESS = "All existing tasks are up to date!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.refreshTasks();

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
