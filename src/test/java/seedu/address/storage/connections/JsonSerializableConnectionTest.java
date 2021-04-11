package seedu.address.storage.connections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;
import seedu.address.storage.connection.ConnectionStorage;
import seedu.address.storage.connection.JsonConnectionStorage;
import seedu.address.storage.connection.JsonSerializableConnection;
import seedu.address.testutil.TypicalMeetings;
import seedu.address.testutil.TypicalPersons;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSerializableConnectionTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableConnectionTest");

    private static MeetingBook meetingBook = TypicalMeetings.getTypicalMeetingBook();
    private static AddressBook addressBook = TypicalPersons.getTypicalAddressBook();
    private static Meeting meetingOne = meetingBook.getMeetingList().get(0);
    private static Person personTwo = addressBook.getPersonList().get(1);
    private static PersonMeetingConnection personMeetingConnection;


    @BeforeEach
    public void setUp() {
        personMeetingConnection = new PersonMeetingConnection();
        personMeetingConnection.addPersonMeetingConnection(personTwo, meetingOne);
    }


    @Test
    public void toModelType_isValidConnection() throws Exception {
        JsonSerializableConnection jsonSerializableConnection =
                JsonUtil.readJsonFile(TEST_DATA_FOLDER.resolve("validConnections.json"),
                        JsonSerializableConnection.class).get();
        assertEquals(jsonSerializableConnection.toModelType(meetingBook, addressBook), personMeetingConnection);
    }

}
