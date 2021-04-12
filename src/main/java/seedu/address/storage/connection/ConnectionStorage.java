package seedu.address.storage.connection;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.person.ReadOnlyAddressBook;

public interface ConnectionStorage {

    /**
     * Gets the path to the data file.
     * @return
     */

    public Path getConnectionFilePath();

    /**
     * Returns data of person and meeting connections in the form of a PersonMeetingConnection class.
     * Needs the meeting book data and addressBook data to form the connection.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    public Optional<PersonMeetingConnection> readConnection(ReadOnlyMeetingBook meetingBook,
                                                            ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    /**
     * Returns the person and meeting connection from a specified file path.
     * See {@link #readConnection(ReadOnlyMeetingBook,ReadOnlyAddressBook)}
     * @param filePath path to the data file, cannot be null
     * @throws DataConversionException if the file is not of the correct format.
     */
    public Optional<PersonMeetingConnection> readConnection(Path filePath, ReadOnlyMeetingBook meetingBook,
                                                            ReadOnlyAddressBook addressBook)
            throws DataConversionException, IOException;

    /**
     * Saves the PersonMeeting connection into storage.
     * @param connection the Person-Meeting connection
     * @throws IOException if there is a problem writing to file.
     */
    public void saveConnection(PersonMeetingConnection connection) throws IOException;

    /**
     * Saves the Person Meeting Connection to a specified file path.
     * See {@link #saveConnection(PersonMeetingConnection)}
     * @param connection the person meeting connection.
     * @param filePath path to data file to save the connection, cannot be null
     * @throws IOException
     */
    public void saveConnection(PersonMeetingConnection connection, Path filePath) throws IOException;



}
