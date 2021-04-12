package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;

/**
 * A utility class containing a list of {@code CommandHistoryEntry} objects to be used in tests.
 */
public class TypicalCommandHistoryEntries {

    public static final int DEFAULT_MAX_RANDOM_ENTRY_LENGTH = 25;

    public static final CommandHistoryEntry HELP = new CommandHistoryEntry(HelpCommand.COMMAND_WORD);
    public static final CommandHistoryEntry HISTORY_ALL = new CommandHistoryEntry(ViewHistoryCommand.COMMAND_WORD);
    public static final CommandHistoryEntry HISTORY_FIVE =
            new CommandHistoryEntry(ViewHistoryCommand.COMMAND_WORD + " 5");

    /**
     * Prevent instantiation.
     */
    private TypicalCommandHistoryEntries() {
    }

    /**
     * Generates and returns a {@code CommandHistoryEntry} containing a random alphanumeric string of
     * length between 1 and {@code DEFAULT_MAX_RANDOM_ENTRY_LENGTH}, inclusive.
     *
     * @return The generated {@code CommandHistoryEntry}.
     */
    public static CommandHistoryEntry getRandomEntry() {
        return getRandomEntry(DEFAULT_MAX_RANDOM_ENTRY_LENGTH);
    }

    /**
     * Generates and returns a {@code CommandHistoryEntry} containing a random alphanumeric string of
     * length between 1 and {@code maxStringLength}, inclusive.
     *
     * @param maxStringLength The inclusive upper bound on string length.
     * @return The generated {@code CommandHistoryEntry}.
     */
    public static CommandHistoryEntry getRandomEntry(int maxStringLength) {
        // Solution below adapted from https://www.baeldung.com/java-random-string
        final int leftLimit = 48; // numeral '0'
        final int rightLimit = 122; // letter 'z'

        final Random random = new Random();
        final int targetStringLength = random.nextInt(maxStringLength) + 1;

        final String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return new CommandHistoryEntry(generatedString);
    }

    /**
     * Returns a {@code CommandHistory} with some typical command history entries.
     * Guarantees at least 3 entries.
     *
     * @return A {@code CommandHistory} with at least 3 typical command history entries.
     */
    public static CommandHistory getTypicalCommandHistory() {
        CommandHistory ch = new CommandHistory();
        for (CommandHistoryEntry entry : getTypicalEntries()) {
            ch.appendEntry(entry);
        }
        return ch;
    }

    /**
     * Returns a list of some typical command history entries.
     * Guarantees at least 3 entries.
     *
     * @return A list of at least 3 typical command history entries.
     */
    public static List<CommandHistoryEntry> getTypicalEntries() {
        return new ArrayList<>(Arrays.asList(HELP, HISTORY_ALL, HELP, HISTORY_FIVE, HISTORY_FIVE));
    }
}
