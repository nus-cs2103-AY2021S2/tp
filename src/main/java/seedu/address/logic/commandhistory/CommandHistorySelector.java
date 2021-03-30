package seedu.address.logic.commandhistory;

import java.util.Optional;

/**
 * Represents the logic of navigating command history.
 */
public interface CommandHistorySelector {
    /**
     * Sets the currently 'selected' entry to one past the most recent entry.
     * A subsequent call to {@code selectPrevious} will select the last entry.
     */
    void navigateToOnePastLast();

    /**
     * Selects the next command text and returns it, if any. If the last entry is selected before
     * this method is called, then {@code Optional.empty()} will be returned. The one-past-last 'element'
     * will be the current entry after this call.
     *
     * @return The next command text, if any.
     */
    Optional<String> selectNextUntilOnePastLast();

    /**
     * Selects the previous command text and returns it, if any. If the first entry is selected before this
     * method is called, then {@code Optional.of} the first entry will be returned, and remain selected
     * after this call.
     *
     * @return The previous command text, if any.
     */
    Optional<String> selectPreviousUntilFirst();
}
