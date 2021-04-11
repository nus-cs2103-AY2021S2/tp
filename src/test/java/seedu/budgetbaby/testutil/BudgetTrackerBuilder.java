package seedu.budgetbaby.testutil;

import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.month.Month;

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

    public BudgetTracker build() {
        return budgetTracker;
    }
}

