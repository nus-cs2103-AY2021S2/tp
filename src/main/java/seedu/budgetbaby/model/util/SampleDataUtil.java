package seedu.budgetbaby.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.budgetbaby.commons.exceptions.DataConversionException;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Category> getCategorySet(String... strings) {
        return Arrays.stream(strings)
            .map(Category::new)
            .collect(Collectors.toSet());
    }

    public static FinancialRecord[] getSampleFinancialRecords() {
        return new FinancialRecord[]{
            new FinancialRecord(new Description("[Sample Financial Record] Lunch"),
                new Amount("10"), getCategorySet("Food")),
            new FinancialRecord(new Description("[Sample Financial Record] Dinner"),
                new Amount("10.50"), getCategorySet("Food")),
        };
    }

    public static ReadOnlyBudgetTracker getSampleBudgetTracker() throws DataConversionException {
        BudgetTracker sample = new BudgetTracker();
        try {
            for (FinancialRecord sampleRecord : getSampleFinancialRecords()) {
                sample.addFinancialRecord(sampleRecord);
            }
        } catch (Exception e) {
            throw new DataConversionException(e);
        }

        return sample;
    }
}
