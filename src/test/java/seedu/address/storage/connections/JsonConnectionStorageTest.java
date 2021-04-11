package seedu.address.storage.connections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.connection.JsonConnectionStorage;
import seedu.address.testutil.TypicalMeetings;
import seedu.address.testutil.TypicalPersons;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;


public class JsonConnectionStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonConnectionStorageTest");
    private static MeetingBook meetingBook = TypicalMeetings.getTypicalMeetingBook();
    private static AddressBook addressBook = TypicalPersons.getTypicalAddressBook();
    private static Meeting meetingOne = meetingBook.getMeetingList().get(0);
    private static Person personTwo = addressBook.getPersonList().get(1);
    private static Meeting meetingTwo = meetingBook.getMeetingList().get(2);
    private static Person personThree = addressBook.getPersonList().get(3);

    @TempDir
    public Path testFolder;
    //helper methods to read different json files.
    private java.util.Optional<PersonMeetingConnection> readConnection(String filePath) throws Exception {
        return new JsonConnectionStorage(Paths.get(filePath))
                .readConnection(addToTestDataPathIfNotNull(filePath), meetingBook, addressBook);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void readConnection_missingFile_returnsEmptyOptional() throws Exception {
        assertEquals(readConnection("NonExistentFile.json"), Optional.empty());
    }

    @Test
    public void readConnection_invalidFileFormat_throwsDateConversionException() throws DataConversionException {
        assertThrows(DataConversionException.class, () -> readConnection("invalidFormat.json"));
    }

    @Test
    public void readAndSaveConnection_inAllOrder_success() throws DataConversionException, IOException {
        Path filePath = testFolder.resolve("TempConnections.json");
        PersonMeetingConnection original = new PersonMeetingConnection();
        original.addPersonMeetingConnection(personTwo, meetingOne);
        JsonConnectionStorage jsonConnectionStorage = new JsonConnectionStorage(filePath);

        // Save in new file and read back
        jsonConnectionStorage.saveConnection(original, filePath);
        PersonMeetingConnection readBack =
                jsonConnectionStorage.readConnection(filePath, meetingBook, addressBook).get();
        assertEquals(original, readBack);


        // Modify data, overwrite exiting file, and read back
        original.addPersonMeetingConnection(personThree, meetingTwo);
        original.deleteSinglePersonMeetingConnection(personTwo, meetingOne);
        jsonConnectionStorage.saveConnection(original, filePath);
        readBack = jsonConnectionStorage.readConnection(filePath, meetingBook, addressBook).get();
        assertEquals(original, readBack);


        //check if default no filepath specified works.
        jsonConnectionStorage.saveConnection(original); // file path not specified
        readBack = jsonConnectionStorage.readConnection(meetingBook, addressBook).get(); // file path not specified
        assertEquals(original, readBack);
    }

}
