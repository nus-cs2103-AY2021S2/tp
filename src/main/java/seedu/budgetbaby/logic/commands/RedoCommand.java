package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;

/**
 * Reverts the {@code model}'s budget tracker to its previously undone state.
 */
public class RedoCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo most recent action. "
            + "No Parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Successfully redid last action.";

    public static final String MESSAGE_FAILURE = "There isn't any recent action to redo.";

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoBudgetTracker()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoBudgetTracker();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
