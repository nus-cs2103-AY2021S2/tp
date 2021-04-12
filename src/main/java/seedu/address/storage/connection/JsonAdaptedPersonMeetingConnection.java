package seedu.address.storage.connection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.*;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.ReadOnlyAddressBook;

public class JsonAdaptedPersonMeetingConnection {
    public static final String CONNECTION_FIELD_MESSAGE_FORMAT = "Connection's %s field is missing!";
    public static final String PERSON_NOT_FOUND_ERROR_MESSAGE = "Person Not found in the AddressBook, but found in a "
            + "connection";
    public static final String MEETING_NOT_FOUND_ERROR_MESSAGE = "Meeting not found in the MeetingBook, but found in "
            + "a connection";
    public static final String MESSAGE_DUPLICATE_CONNECTION = "Connections List contains duplicate connection(s).";
    public static final String MESSAGE_MSSING_FIELDS = "The connection has at least one field value missing";

    private final String personName;
    private final String startDateTime;
    private final String meetingName;

    /**
     * Constructs a {@code JsonAdoptedPersonMeetingConnection} with the given associated person and meeting
     */

    @JsonCreator
    public JsonAdaptedPersonMeetingConnection(@JsonProperty("personName") String personName,
                              @JsonProperty("startDateTime") String startDateTime,
                              @JsonProperty("meetingName") String meetingName) {
        this.personName = personName;
        this.startDateTime = startDateTime;
        this.meetingName = meetingName;
        }


    /**
     * Constructs a json serializable association class for the meeting to person.
     */
    public JsonAdaptedPersonMeetingConnection(Person personSource, Meeting meetingSource) {
        assert personSource != null && meetingSource != null;
        personName = personSource.getName().fullName;
        startDateTime = DateTimeUtil.formatDateTime(meetingSource.getStart().value);
        meetingName = meetingSource.getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */

    public PersonMeetingConnection toModelType(ReadOnlyAddressBook addressBook, ReadOnlyMeetingBook meetingBook,
                                               PersonMeetingConnection connection) throws IllegalValueException {
        assert addressBook != null && meetingBook != null && connection != null;
        if (personName == null|| meetingName == null || startDateTime == null) {
            throw new IllegalValueException(MESSAGE_MSSING_FIELDS);
        }
        Person person = addressBook.getPersonByName(new PersonName(personName));
        Meeting meeting = meetingBook.getMeetingByNameAndStartTime(new MeetingName(meetingName), new DateTime(startDateTime));
        if (person == null) {
            throw new IllegalValueException(PERSON_NOT_FOUND_ERROR_MESSAGE);
        }
        if (meeting == null) {
            throw new IllegalValueException(MEETING_NOT_FOUND_ERROR_MESSAGE);
        }
        if (connection.existPersonMeetingConnection(person, meeting)) {
            throw new IllegalValueException(MESSAGE_DUPLICATE_CONNECTION);
        }
        connection.addPersonMeetingConnection(person, meeting);
        return connection;
    }
}
