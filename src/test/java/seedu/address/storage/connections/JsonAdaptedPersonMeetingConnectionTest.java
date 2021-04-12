package seedu.address.storage.connections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.connection.JsonAdaptedPersonMeetingConnection;
import seedu.address.testutil.TypicalMeetings;
import seedu.address.testutil.TypicalPersons;

public class JsonAdaptedPersonMeetingConnectionTest {
    private static MeetingBook meetingBook = TypicalMeetings.getTypicalMeetingBook();
    private static AddressBook addressBook = TypicalPersons.getTypicalAddressBook();
    private static Meeting meetingOne = meetingBook.getMeetingList().get(0);
    private static Person personTwo = addressBook.getPersonList().get(1);
    private static Person personNotInTypicalPersons = TypicalPersons.HOON;
    private static Meeting meetingNotInTypicalMeetings = TypicalMeetings.MEETING6;

    @Test
    public void toModelType_isValidPersonMeetingConnection_success() throws IllegalValueException {
        JsonAdaptedPersonMeetingConnection jsonAdaptedPersonMeetingConnection =
                new JsonAdaptedPersonMeetingConnection(personTwo, meetingOne);
        PersonMeetingConnection personMeetingConnection = new PersonMeetingConnection();
        personMeetingConnection.addPersonMeetingConnection(personTwo, meetingOne);
        PersonMeetingConnection thisConnection = jsonAdaptedPersonMeetingConnection.toModelType(addressBook,
                meetingBook, new PersonMeetingConnection());
        assertEquals(personMeetingConnection, thisConnection);
    }

    @Test
    public void toModelType_personNotInAddressBook_illegalValueException() throws IllegalValueException {
        JsonAdaptedPersonMeetingConnection jsonAdaptedPersonMeetingConnection =
                new JsonAdaptedPersonMeetingConnection(personNotInTypicalPersons, meetingOne);
        String expectedMessage = JsonAdaptedPersonMeetingConnection.PERSON_NOT_FOUND_ERROR_MESSAGE;

        assertThrows(IllegalValueException.class, expectedMessage, () -> {
            jsonAdaptedPersonMeetingConnection.toModelType(addressBook, meetingBook, new PersonMeetingConnection());
        });
    }

    @Test
    public void toModelType_meetingNotInMeetingBook_illegalValueException() throws IllegalValueException {
        JsonAdaptedPersonMeetingConnection jsonAdaptedPersonMeetingConnection =
                new JsonAdaptedPersonMeetingConnection(personTwo, meetingNotInTypicalMeetings);
        String expectedMessage = JsonAdaptedPersonMeetingConnection.MEETING_NOT_FOUND_ERROR_MESSAGE;

        assertThrows(IllegalValueException.class, expectedMessage, () -> {
            jsonAdaptedPersonMeetingConnection.toModelType(addressBook, meetingBook, new PersonMeetingConnection());
        });
    }

    @Test
    public void toModelType_duplicatePersonMeetingConnectionDetected_illegalValueException()
            throws IllegalValueException {
        JsonAdaptedPersonMeetingConnection jsonAdaptedPersonMeetingConnection =
                new JsonAdaptedPersonMeetingConnection(personTwo, meetingOne);
        PersonMeetingConnection personMeetingConnection = new PersonMeetingConnection();
        personMeetingConnection.addPersonMeetingConnection(personTwo, meetingOne);
        String expectedMessage = JsonAdaptedPersonMeetingConnection.MESSAGE_DUPLICATE_CONNECTION;
        assertThrows(IllegalValueException.class, expectedMessage, () -> {
            jsonAdaptedPersonMeetingConnection.toModelType(addressBook, meetingBook, personMeetingConnection);
        });
    }
}
