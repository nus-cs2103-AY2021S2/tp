package seedu.address.model.commandhistory;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the command history of the application.
 */
public class CommandHistory implements ReadOnlyCommandHistory {

    private final List<CommandHistoryEntry> entries;

    /**
     * Constructs a {@code CommandHistory} that is a copy of {@code other}.
     *
     * @param other The {@code CommandHistory} to copy.
     * @throws NullPointerException If other is null.
     */
    public CommandHistory(ReadOnlyCommandHistory other) {
        requireNonNull(other);
        entries = new ArrayList<>(other.size());
        for (int i = 0; i < other.size(); i++) {
            entries.add(other.get(i));
        }
    }

    /**
     * Constructs a {@code CommandHistory} with an initial list of entries.
     *
     * @param entries The initial list of entries.
     * @throws NullPointerException If entries is null.
     */
    public CommandHistory(List<CommandHistoryEntry> entries) {
        requireNonNull(entries);
        this.entries = new ArrayList<>(entries);
    }

    /**
     * Constructs a {@code CommandHistory} with an empty initial list of entries.
     */
    public CommandHistory() {
        this(new ArrayList<>());
    }

    /**
     * Appends the {@code CommandHistoryEntry} to the end of the command history.
     *
     * @param entry The entry to append.
     */
    public void appendEntry(CommandHistoryEntry entry) {
        assert entries != null;
        entries.add(entry);
    }

    /**
     * Two {@code CommandHistory} objects are equal when all entries are equal and in the same order.
     *
     * @param other Other object to compare with.
     * @return True if equal. False otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CommandHistory)) {
            return false;
        }
        CommandHistory that = (CommandHistory) other;
        return entries.equals(that.entries);
    }

    /**
     * Returns the command history entry at the given {@code index}.
     *
     * @param index The index of the entry to return.
     * @return The command history entry at the given {@code index}.
     * @throws IndexOutOfBoundsException If (index < 0 or index >= size()).
     */
    @Override
    public CommandHistoryEntry get(int index) {
        assert entries != null;
        return entries.get(index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entries);
    }

    /**
     * Returns the number of entries in this {@code CommandHistory}.
     *
     * @return The number of entries in this {@code CommandHistory}.
     */
    @Override
    public int size() {
        assert entries != null;
        return entries.size();
    }
}
