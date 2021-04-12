package seedu.budgetbaby.testutil;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * A utility class to help with building BudgetTracker objects.
 */
public class BudgetTrackerBuilder {

    private BudgetTracker budgetTracker;

    public BudgetTrackerBuilder() {
        budgetTracker = new BudgetTracker();
    }

    public BudgetTrackerBuilder(BudgetTracker budgetTracker) {
        this.budgetTracker = budgetTracker;
    }

    /**
     * Adds a new {@code Month} to the {@code BudgetTracker} that we are building.
     */
    public BudgetTrackerBuilder withMonth(Month month) {
        budgetTracker.addMonth(month);
        return this;
    }

    /**
     * Adds a new {@code FinancialRecord} to the {@code BudgetTracker} that we are building.
     */
    public BudgetTrackerBuilder withFinancialRecord(FinancialRecord financialRecord) {
        try {
            budgetTracker.addFinancialRecord(financialRecord);
        } catch (CommandException e) {
            return null;
        }
        return this;
    }

    public BudgetTracker build() {
        return budgetTracker;
    }
}
