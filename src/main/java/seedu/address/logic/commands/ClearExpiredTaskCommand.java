package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the Address Book.
 */
public class ClearExpiredTaskCommand extends Command {

    public static final String COMMAND_WORD = "clear_expired_task";

    public static final String MESSAGE_SUCCESS = "Expired tasks (if any) have been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearExpiredTasks();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
