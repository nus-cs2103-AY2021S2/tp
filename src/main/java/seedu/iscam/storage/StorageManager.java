package seedu.iscam.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.iscam.commons.core.LogsCenter;
import seedu.iscam.commons.exceptions.DataConversionException;
import seedu.iscam.model.user.ReadOnlyUserPrefs;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;
import seedu.iscam.storage.client.ClientBookStorage;
import seedu.iscam.storage.meeting.MeetingBookStorage;
import seedu.iscam.storage.user.UserPrefsStorage;

/**
 * Manages storage of ClientBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClientBookStorage clientBookStorage;
    private MeetingBookStorage meetingBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given
     * {@code ClientBookStorage}, {@code MeetingBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClientBookStorage clientBookStorage,
                          MeetingBookStorage meetingBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.clientBookStorage = clientBookStorage;
        this.meetingBookStorage = meetingBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ ClientBook methods ==============================

    @Override
    public Path getClientBookFilePath() {
        return clientBookStorage.getClientBookFilePath();
    }

    @Override
    public Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException, IOException {
        return readClientBook(clientBookStorage.getClientBookFilePath());
    }

    @Override
    public Optional<ReadOnlyClientBook> readClientBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read client data from file: " + filePath);
        return clientBookStorage.readClientBook(filePath);
    }

    @Override
    public void saveClientBook(ReadOnlyClientBook clientBook) throws IOException {
        saveClientBook(clientBook, clientBookStorage.getClientBookFilePath());
    }

    @Override
    public void saveClientBook(ReadOnlyClientBook clientBook, Path filePath) throws IOException {
        logger.fine("Attempting to write client to data file: " + filePath);
        clientBookStorage.saveClientBook(clientBook, filePath);
    }

    // ================ MeetingBook methods ==============================

    @Override
    public Path getMeetingBookFilePath() {
        return meetingBookStorage.getMeetingBookFilePath();
    }

    @Override
    public Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException {
        return readMeetingBook(meetingBookStorage.getMeetingBookFilePath());
    }

    @Override
    public Optional<ReadOnlyMeetingBook> readMeetingBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read meeting data from file: " + filePath);
        return meetingBookStorage.readMeetingBook(filePath);
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException {
        saveMeetingBook(meetingBook, meetingBookStorage.getMeetingBookFilePath());
    }

    @Override
    public void saveMeetingBook(ReadOnlyMeetingBook meetingBook, Path filePath) throws IOException {
        logger.fine("Attempting to write meetings to data file: " + filePath);
        meetingBookStorage.saveMeetingBook(meetingBook, filePath);
    }

}
