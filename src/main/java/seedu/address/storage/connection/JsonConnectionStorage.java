package seedu.address.storage.connection;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.person.ReadOnlyAddressBook;

public class JsonConnectionStorage implements ConnectionStorage {
    private static final Logger logger = LogsCenter.getLogger(JsonConnectionStorage.class);

    private Path filePath;

    public JsonConnectionStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getConnectionFilePath() {
        return filePath;
    }

    public Optional<PersonMeetingConnection> readConnection(ReadOnlyMeetingBook meetingBook,
                                                            ReadOnlyAddressBook addressBook)
            throws DataConversionException {
        return readConnection(filePath, meetingBook, addressBook);
    }

    /**
     * Similar to {@link #readConnection(ReadOnlyMeetingBook, ReadOnlyAddressBook)}}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<PersonMeetingConnection> readConnection(
            Path filePath, ReadOnlyMeetingBook meetingBook,
            ReadOnlyAddressBook addressBook) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableConnection> jsonConnection = JsonUtil.readJsonFile(
                filePath, JsonSerializableConnection.class);
        if (!jsonConnection.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonConnection.get().toModelType(meetingBook, addressBook));
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    public void saveConnection(PersonMeetingConnection connection) throws IOException {
        saveConnection(connection, filePath);
    }

    /**
     * Similar to {@link #saveConnection(PersonMeetingConnection)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveConnection(PersonMeetingConnection connection, Path filePath) throws IOException {
        requireNonNull(connection);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableConnection(connection), filePath);
    }
}
