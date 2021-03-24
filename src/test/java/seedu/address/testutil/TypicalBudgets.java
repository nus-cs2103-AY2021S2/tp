package seedu.address.testutil;

import seedu.address.model.BudgetBook;
import seedu.address.model.budget.Budget;

public class TypicalBudgets {

    public static final Budget BUDGET_ONE = new Budget("500");

    /**
     * @return Typical budget book.
     */
    public static BudgetBook getTypicalBudgetBook() {
        return new BudgetBook(BUDGET_ONE);
    }


}
