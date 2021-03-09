package seedu.address.model.commandhistory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the command history of the application.
 */
public class CommandHistory implements ReadOnlyCommandHistory {

    private final List<CommandHistoryEntry> entries;

    /**
     * Constructs a {@code CommandHistory} with an initial list of entries.
     *
     * @param entries The initial list of entries.
     */
    public CommandHistory(List<CommandHistoryEntry> entries) {
        this.entries = entries;
    }

    /**
     * Constructs a {@code CommandHistory} with an empty initial list of entries.
     */
    public CommandHistory() {
        this(new ArrayList<>());
    }

    /**
     * Appends the {@code CommandHistoryEntry} to the end of the command history.
     * @param entry The entry to append.
     */
    public void appendEntry(CommandHistoryEntry entry) {
        entries.add(entry);
    }

    /**
     * Returns the number of entries in this {@code CommandHistory}.
     *
     * @return The number of entries in this {@code CommandHistory}.
     */
    @Override
    public int size() {
        return entries.size();
    }

    /**
     * Returns the command history entry at the given {@code index}.
     *
     * @param index The index of the entry to return.
     * @return The command history entry at the given {@code index}.
     */
    @Override
    public CommandHistoryEntry get(int index) {
        return entries.get(index);
    }
}
