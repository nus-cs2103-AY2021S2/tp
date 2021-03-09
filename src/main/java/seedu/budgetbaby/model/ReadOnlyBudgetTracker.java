package seedu.budgetbaby.model;

import javafx.collections.ObservableList;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Unmodifiable view of a budget tracker
 */
public interface ReadOnlyBudgetTracker {

    /**
     * Returns an unmodifiable view of the financial record list.
     */
    ObservableList<FinancialRecord> getFinancialRecordList();

}
