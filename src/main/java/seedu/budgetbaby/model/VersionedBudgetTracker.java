package seedu.budgetbaby.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code BudgetTracker} that keeps track of its own history.
 */
public class VersionedBudgetTracker extends BudgetTracker {

    private final List<ReadOnlyBudgetTracker> budgetTrackerStateList;
    private int currentStatePointer;

    public VersionedBudgetTracker(ReadOnlyBudgetTracker initialState) {
        super(initialState);

        BudgetTracker firstState = new BudgetTracker(initialState);
        firstState.setCurrentDisplayMonth(YearMonth.now());

        budgetTrackerStateList = new ArrayList<>();
        budgetTrackerStateList.add(firstState.getDeepClone());
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code BudgetTracker} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        budgetTrackerStateList.subList(currentStatePointer + 1, budgetTrackerStateList.size()).clear();
        budgetTrackerStateList.add(this.getDeepClone());
        currentStatePointer++;
    }

    /**
     * Restores the budget tracker to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(budgetTrackerStateList.get(currentStatePointer).getDeepClone());
    }

    /**
     * Returns true if {@code undo()} has budget tracker states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedBudgetTracker)) {
            return false;
        }

        VersionedBudgetTracker otherVersionedBudgetTracker = (VersionedBudgetTracker) other;

        // state check
        return super.equals(otherVersionedBudgetTracker)
                && budgetTrackerStateList.equals(otherVersionedBudgetTracker.budgetTrackerStateList)
                && currentStatePointer == otherVersionedBudgetTracker.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of budgetTrackerState list, unable to undo.");
        }
    }
}
