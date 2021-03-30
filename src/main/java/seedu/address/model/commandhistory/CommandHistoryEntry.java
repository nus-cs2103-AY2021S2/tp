package seedu.address.model.commandhistory;

import static java.util.Objects.requireNonNull;

/**
 * Represents a command's text previously entered.
 * Guarantees: immutable.
 */
public class CommandHistoryEntry {

    public final String value;

    /**
     * Constructs a {@code CommandHistoryEntry}
     *
     * @param text The command's text. Must be non-null.
     * @throws NullPointerException If {@code text} is null.
     */
    public CommandHistoryEntry(String text) {
        requireNonNull(text);
        this.value = text;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandHistoryEntry // instanceof handles nulls
                && value.equals(((CommandHistoryEntry) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
