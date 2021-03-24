package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.budget.Budget;

public interface ReadOnlyBudgetBook {

    /**
     * Returns an unmodifiable view of the appointment list.
     * This list will not contain any duplicate appointments.
     */
    ObservableList<Budget> getBudgetList();

}
