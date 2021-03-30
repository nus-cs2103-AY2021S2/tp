package seedu.budgetbaby.model;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import seedu.budgetbaby.model.month.Month;

/**
 * Unmodifiable view of a budget tracker
 */
public interface ReadOnlyBudgetTracker extends Observable {

    /**
     * Returns an unmodifiable view of the month list.
     */
    ObservableList<Month> getMonthList();

    /**
     *  Returns the current display month.
     */
    Month getCurrentDisplayMonth();

    /**
     * Notifies all observers
     */
    void notifyObservers();

    /**
     * Returns a read-only, deep copy of BudgetTracker.
     */
    ReadOnlyBudgetTracker getDeepClone();
}
