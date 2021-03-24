package seedu.address.storage.commandhistory;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.commandhistory.CommandHistory;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;

/**
 * A class to access CommandHistory data stored as a plain text file on the hard disk.
 */
public class PlainTextCommandHistoryStorage implements CommandHistoryStorage {

    private static final Logger logger = LogsCenter.getLogger(PlainTextCommandHistoryStorage.class);

    private final Path filePath;

    /**
     * Constructs a {@code PlainTextCommandHistoryStorage} that saves to/loads from
     * the given file path.
     *
     * @param filePath The file path to save to/load from. Must be non-null.
     * @throws NullPointerException If {@code filePath} is null.
     */
    public PlainTextCommandHistoryStorage(Path filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public Path getCommandHistoryFilePath() {
        return filePath;
    }

    /**
     * Attempts to read and deserialize command history from disk.
     *
     * @return An {@code Optional} of the serialized command history, if successful.
     * {@code Optional.empty()} if file does not exist.
     * @throws IOException If file exists but cannot be read.
     */
    @Override
    public Optional<ReadOnlyCommandHistory> readCommandHistory() throws IOException {
        if (!FileUtil.isFileExists(filePath)) {
            logger.info(String.format("Command history file %s not found", filePath.toString()));
            return Optional.empty();
        }

        String content;

        try {
            content = FileUtil.readFromFile(filePath);
        } catch (IOException e) {
            logger.warning(String.format("Error reading from command history file %s: %s",
                    filePath.toString(), e.getMessage()));
            throw new IOException(e);
        }

        return Optional.of(deserializeCommandHistory(content));
    }

    /**
     * Writes a serialized version of command history to disk.
     *
     * @param commandHistory Cannot be null.
     * @throws IOException          If writing to disk fails.
     * @throws NullPointerException If {@code commandHistory} is null.
     */
    @Override
    public void saveCommandHistory(ReadOnlyCommandHistory commandHistory) throws IOException {
        requireNonNull(commandHistory);
        String content = serializeCommandHistory(commandHistory);

        try {
            FileUtil.writeToFile(filePath, content);
        } catch (IOException e) {
            logger.warning(String.format("Error writing to command history file %s: %s",
                    filePath.toString(), e.getMessage()));
            throw new IOException(e);
        }
    }

    /**
     * Deserializes command history from its serialized form.
     *
     * @param serialized Must be non-null.
     * @throws NullPointerException If {@code serialized} is null.
     */
    private CommandHistory deserializeCommandHistory(String serialized) {
        requireNonNull(serialized);
        if (serialized.isBlank()) {
            return new CommandHistory();
        }

        CommandHistory history = new CommandHistory();
        for (String entryText : serialized.split(System.lineSeparator())) {
            if (entryText.isBlank()) {
                continue;
            }
            history.appendEntry(new CommandHistoryEntry(entryText));
        }
        return history;
    }

    /**
     * Returns a serialized version of command history.
     *
     * @param commandHistory Must not be null.
     * @throws NullPointerException If {@code commandHistory} is null.
     */
    private String serializeCommandHistory(ReadOnlyCommandHistory commandHistory) {
        requireNonNull(commandHistory);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < commandHistory.size(); i++) {
            assert commandHistory.get(i) != null && commandHistory.get(i).value != null;
            content.append(commandHistory.get(i).value).append(System.lineSeparator());
        }
        return content.toString();
    }
}
