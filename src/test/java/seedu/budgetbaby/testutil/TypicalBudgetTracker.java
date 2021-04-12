package seedu.budgetbaby.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
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
    private static final Set<Category> categories = new HashSet<>(Arrays.asList(new Category("food")));

    private static final Description description2 = new Description("Lunch");
    private static final Amount amount2 = new Amount("6");
    private static final Set<Category> categories2 = new HashSet<>(Arrays.asList(new Category("food")));

    private static final Description description3 = new Description("Movie");
    private static final Amount amount3 = new Amount("8");
    private static final Set<Category> categories3 = new HashSet<>(Arrays.asList(new Category("entertainment")));

    private static final Description description4 = new Description("Button Shirt");
    private static final Amount amount4 = new Amount("18");
    private static final Set<Category> categories4 = new HashSet<>(Arrays.asList(new Category("apparel")));

    private static final Description description5 = new Description("Jeans");
    private static final Amount amount5 = new Amount("55");
    private static final Set<Category> categories5 = new HashSet<>(Arrays.asList(new Category("apparel")));

    private static final Description description6 = new Description("Water Bottle");
    private static final Amount amount6 = new Amount("12");
    private static final Set<Category> categories6 = new HashSet<>(Arrays.asList(new Category("essentials")));

    public static final FinancialRecord FR1 = new FinancialRecord(description, amount, categories);
    public static final FinancialRecord FR2 = new FinancialRecord(description2, amount2, categories2);
    public static final FinancialRecord FR3 = new FinancialRecord(description3, amount3, categories3);
    public static final FinancialRecord FR4 = new FinancialRecord(description4, amount4, categories4);
    public static final FinancialRecord FR5 = new FinancialRecord(description5, amount5, categories5);
    public static final FinancialRecord FR6 = new FinancialRecord(description6, amount6, categories6);

    /**
     * Returns a {@code BudgetTracker} with all the typical financial records.
     */
    public static BudgetTracker getTypicalBudgetTracker() {
        BudgetTracker bt = new BudgetTracker();
        try {
            for (FinancialRecord fr : getTypicalFinancialRecordsList()) {
                bt.addFinancialRecord(fr);
            }
        } catch (CommandException e) {
            System.out.println("error");
            return null;
        }
        return bt;
    }

    public static List<FinancialRecord> getTypicalFinancialRecordsList() {
        return new ArrayList<>(Arrays.asList(FR1, FR2, FR3, FR4, FR5, FR6));
    }
}
