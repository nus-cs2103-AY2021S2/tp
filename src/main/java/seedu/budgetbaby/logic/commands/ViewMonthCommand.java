package seedu.budgetbaby.logic.commands;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.month.Month;

import java.time.YearMonth;

import static java.util.Objects.requireNonNull;

/**
 * Changes the current display month of the budget tracker.
 */
public class ViewMonthCommand extends BudgetBabyCommand {

    public static final String COMMAND_WORD = "view-month";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a month. "
        + "Parameters: "
        + "mm-yyyy\n"
        + "Example: " + COMMAND_WORD + " "
        + "01-2021";

    public static final String MESSAGE_SUCCESS = "Displaying month: %1$s";

    private final YearMonth toView;

    /**
     * Creates an ViewMonthCommand to view the specified {@code month}
     */
    public ViewMonthCommand(YearMonth month) {
        requireNonNull(month);
        toView = month;
    }

    @Override
    public CommandResult execute(BudgetBabyModel model) throws CommandException {
        requireNonNull(model);

        model.setCurrentDisplayMonth(toView);
        return new CommandResult(String.format(MESSAGE_SUCCESS, new Month(toView)), true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ViewMonthCommand // instanceof handles nulls
            && toView.equals(((ViewMonthCommand) other).toView));
    }
}