package seedu.address.logic.commandhistory;

import java.util.Optional;

/**
 * Represents the logic of navigating command history.
 */
public interface CommandHistorySelector {
    /**
     * Sets the currently 'selected' command text to one past the most recent entry.
     * A subsequent call to {@code selectPrevious} will select the last entry. This is stateful.
     */
    void navigateToOnePastLast();

    /**
     * Selects the next command text and returns it, if any. This is stateful.
     *
     * @return The next command text, if any.
     */
    Optional<String> selectNext();

    /**
     * Selects the previous command text and returns it, if any. This is stateful.
     *
     * @return The previous command text, if any.
     */
    Optional<String> selectPrevious();
}
