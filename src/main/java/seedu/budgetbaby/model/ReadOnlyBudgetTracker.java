package seedu.budgetbaby.model;

import javafx.collections.ObservableList;
import seedu.budgetbaby.model.month.Month;

/**
 * Unmodifiable view of a budget tracker
 */
public interface ReadOnlyBudgetTracker {

    /**
     * Returns an unmodifiable view of the month list.
     */
    ObservableList<Month> getMonthList();

}
