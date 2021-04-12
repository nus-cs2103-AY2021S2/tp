package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears all overdue entries in Teaching Assistant.
 */
public class ClearOverdueEntryCommand extends Command {

    public static final String COMMAND_WORD = "eclear";

    public static final String MESSAGE_SUCCESS = "All overdue entries have been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearOverdueEntries();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
