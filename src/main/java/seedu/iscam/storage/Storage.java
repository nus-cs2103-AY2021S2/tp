package seedu.iscam.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.iscam.commons.exceptions.DataConversionException;
import seedu.iscam.model.user.ReadOnlyUserPrefs;
import seedu.iscam.model.user.UserPrefs;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;
import seedu.iscam.storage.client.ClientBookStorage;
import seedu.iscam.storage.meeting.MeetingBookStorage;
import seedu.iscam.storage.user.UserPrefsStorage;

/**
 * API of the Storage component
 */
public interface Storage extends ClientBookStorage, MeetingBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClientBookFilePath();

    @Override
    Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException, IOException;

    @Override
    void saveClientBook(ReadOnlyClientBook clientBook) throws IOException;

    @Override
    Path getMeetingBookFilePath();

    @Override
    Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException;

    @Override
    void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException;

}
