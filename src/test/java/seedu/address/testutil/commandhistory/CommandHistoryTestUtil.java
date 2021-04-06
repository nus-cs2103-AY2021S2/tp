package seedu.address.testutil.commandhistory;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;

/**
 * A utility class for testing command history.
 */
public class CommandHistoryTestUtil {

    /**
     * Prevent instantiation.
     */
    private CommandHistoryTestUtil() {
    }

    /**
     * Creates a list of entries from the given history, with the same order.
     *
     * @param history The command history to get entries from.
     * @return The list of entries in the command history, in the same order.
     */
    public static List<CommandHistoryEntry> getEntries(CommandHistory history) {
        List<CommandHistoryEntry> entries = new ArrayList<>();
        for (int i = 0; i < history.size(); i++) {
            entries.add(history.get(i));
        }
        return entries;
    }
}
