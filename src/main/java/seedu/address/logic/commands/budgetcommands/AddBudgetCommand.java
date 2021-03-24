package seedu.address.logic.commands.budgetcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.budget.Budget;

public class AddBudgetCommand extends Command {

    public static final String COMMAND_WORD = "add_budget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "Adds a budget to the "
            + "user system. "
            + PREFIX_BUDGET + "BUDGET "
            + "Example: add_budget "
            + PREFIX_BUDGET + "500";

    public static final String MESSAGE_SUCCESS = "Budget of %d is successfully added.";
    public static final String MESSAGE_DUPLICATE_BUDGET = "Budget already exists. "
            + "Please use the edit_budget function instead.";

    private final Budget toAdd;

    /**
     * Primary constructor.
     */
    public AddBudgetCommand(Budget budget) {
        requireNonNull(budget);
        this.toAdd = budget;
    }

    /**
     * Main execute method that adds a budget to the user system.
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult indicating success or failure of add operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBudget(toAdd)) {
            return new CommandResult(MESSAGE_DUPLICATE_BUDGET);
        } else {
            model.addBudget(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getValue()));
        }
    }


}
