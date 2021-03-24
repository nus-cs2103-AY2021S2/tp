package seedu.address.logic.commandhistory;

import java.util.Optional;

/**
 * Represents the logic of navigating command history.
 */
public interface CommandHistorySelector {
    /**
     * Selects the previous command text and returns it, if any. This is stateful.
     *
     * @return The previous command text, if any.
     */
    Optional<String> selectPrevious();

    /**
     * Selects the next command text and returns it, if any. This is stateful.
     *
     * @return The next command text, if any.
     */
    Optional<String> selectNext();

    /**
     * Selects the most recent command text and returns it, if any. This is stateful.
     *
     * @return The most recent command text, if any.
     */
    Optional<String> selectLast();
}
