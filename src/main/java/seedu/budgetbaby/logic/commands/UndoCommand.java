package seedu.budgetbaby.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;

/**
 * Reverts the {@code model}'s budget tracker to its previous state.
 */
public class UndoCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo most recent action. "
            + "No Parameters\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Successfully undid last action.";

    public static final String MESSAGE_FAILURE = "There isn't any recent action to undo.";

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoBudgetTracker()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoBudgetTracker();
        return new CommandResult(MESSAGE_SUCCESS, false, false);
    }
}
