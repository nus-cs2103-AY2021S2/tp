package seedu.address.logic.commands.budgetcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.budget.Budget;


public class EditBudgetCommand extends Command {

    public static final String COMMAND_WORD = "edit_budget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Edits the current "
            + "budget stored in the user system. "
            + PREFIX_BUDGET + "BUDGET "
            + "Example: edit_budget "
            + PREFIX_BUDGET + "500";

    public static final String MESSAGE_SUCCESS = "Budget of %d is successfully "
            + "updated.";
    public static final String MESSAGE_MISSING_BUDGET = "Budget does not already exist."
            + "Please use the add_budget function instead.";

    private Budget toEdit;

    /**
     * Primary constructor.
     * @param budget Budget to edit to.
     */
    public EditBudgetCommand(Budget budget) {
        requireNonNull(budget);
        this.toEdit = budget;
    }

    /**
     * Main execute method that edits a {@code Budget} that already exists in the user
     * system.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult indicating success or failure of add operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBudget(toEdit)) {
            model.editBudget(toEdit);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit.getValue()));
        } else {
            return new CommandResult(MESSAGE_MISSING_BUDGET);
        }
    }

}
