package seedu.address.logic.commands.budgetcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class DeleteBudgetCommand extends Command {

    public static final String COMMAND_WORD = "delete_budget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Deletes the current "
            + "budget stored in the user system. "
            + "Example: delete_budget ";

    public static final String MESSAGE_SUCCESS = "Budget of %d is successfully "
            + "deleted.";
    public static final String MESSAGE_MISSING_BUDGET = "Budget does not already exist."
            + " Please use the add_budget function instead.";


    /**
     * Primary constructor
     */
    public DeleteBudgetCommand() {

    }

    /**
     * Main execute method that deletes a {@code Budget} that already exists in the user
     * system.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult indicating success or failure of add operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBudget()) {
            int budgetValue = model.getBudgetBook().getBudget().getValue();
            model.deleteBudget();
            return new CommandResult(String.format(MESSAGE_SUCCESS, budgetValue));
        } else {
            return new CommandResult(MESSAGE_MISSING_BUDGET);
        }
    }

}
