package seedu.iscam.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.commons.exceptions.DataConversionException;
import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.commons.util.FileUtil;
import seedu.iscam.commons.util.JsonUtil;
import seedu.iscam.model.ReadOnlyMeetingBook;

/**
 * A class to access MeetingBook data stored as a json file on the hard disk.
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
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook clientBook) throws IOException {
        saveMeetingBook(clientBook, filePath);
    }

    /**
     * Similar to {@link #saveMeetingBook(ReadOnlyMeetingBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMeetingBook(ReadOnlyMeetingBook clientBook, Path filePath) throws IOException {
        requireNonNull(clientBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMeetingBook(clientBook), filePath);
    }


}
