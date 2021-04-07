package seedu.address.logic.commands.budgetcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.budget.Budget;

//TODO Improve on ViewBudget UI if possible
public class ViewBudgetCommand extends Command {

    public static final String COMMAND_WORD = "view_budget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " Views an already "
            + "existing budget stored in the user system. "
            + "Example: view_budget ";

    public static final String MESSAGE_SUCCESS = "Here is your budget.\n%s.";
    public static final String MESSAGE_MISSING_BUDGET = "Budget does not already exist."
            + " Please ensure there is a budget. You can use the add_budget function "
            + "to add a budget.";


    /**
     * Default constructor.
     */
    public ViewBudgetCommand() {

    }

    /**
     * Main execute method that displays a {@code Budget} that already exists in the
     * user system.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult indicating success or failure of add operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.hasBudget()) {
            Budget budget = model.getBudgetBook().getBudget();
            return new CommandResult(String.format(MESSAGE_SUCCESS, budget), TabName.BUDGET);
        } else {
            return new CommandResult(MESSAGE_MISSING_BUDGET);
        }
    }

}
