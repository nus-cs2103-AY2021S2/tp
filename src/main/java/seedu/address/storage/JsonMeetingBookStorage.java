package seedu.address.storage;

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
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;


/**
 * Represents a storage for {@link MeetingBook}.
 */

public class JsonMeetingBookStorage implements MeetingBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMeetingBookStorage.class);

    private Path filePath;

    public JsonMeetingBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMeetingBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException {
        return readMeetingBook(filePath);
    }

    /**
     * Similar to {@link #readMeetingBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMeetingBook> readMeetingBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        Optional<JsonSerializableMeetingBook> jsonMeetingBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableMeetingBook.class);
        if (!jsonMeetingBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMeetingBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found for Meeting Book in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException {
        saveMeetingBook(meetingBook, filePath);
    }

    /**
     * Similar to {@link #saveMeetingBook(ReadOnlyMeetingBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook, Path filePath) throws IOException {
        requireNonNull(meetingBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMeetingBook(meetingBook), filePath);
    }
}

