package seedu.budgetbaby.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * A utility class containing a list of {@code Financial Record} objects to be used in tests.
 */
public class TypicalBudgetTracker {
    private static final Description description = new Description("Breakfast");
    private static final Amount amount = new Amount("5");
    private static final Set<Category> categories = new HashSet<>(Arrays.asList(new Category("Food")));

    private static final Description description2 = new Description("Lunch");
    private static final Amount amount2 = new Amount("6");
    private static final Set<Category> categories2 = new HashSet<>(Arrays.asList(new Category("Food")));

    /**
     * Returns a {@code BudgetTracker} with all the typical financial records.
     */
    public static BudgetTracker getTypicalBudgetTracker() {
        BudgetTracker bt = new BudgetTracker();
        for (FinancialRecord fr : getTypicalFinancialRecordsList()) {
            bt.addFinancialRecord(fr);
        }
        return bt;
    }

    public static List<FinancialRecord> getTypicalFinancialRecordsList() {
        FinancialRecord fr1 = new FinancialRecord(description, amount, categories);
        FinancialRecord fr2 = new FinancialRecord(description2, amount2, categories2);
        return new ArrayList<>(Arrays.asList(fr1, fr2));
    }
}
