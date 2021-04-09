package seedu.address.model.commandhistory;

/**
 * Unmodifiable view of a command history.
 */
public interface ReadOnlyCommandHistory {
    /**
     * Returns the {@code CommandHistoryEntry} at the given index.
     *
     * @param index The index of the entry to return.
     * @return The {@code CommandHistoryEntry} at the given index.
     */
    CommandHistoryEntry get(int index);

    /**
     * Returns the number of entries in the command history.
     *
     * @return The number of entries in the command history.
     */
    int size();
}
