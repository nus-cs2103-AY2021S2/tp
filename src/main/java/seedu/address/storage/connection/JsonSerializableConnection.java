package seedu.address.storage.connection;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;

@JsonRootName(value = "connection")
public class JsonSerializableConnection {

    private final List<JsonAdaptedPersonMeetingConnection> connections = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableConnection} with the given list of jsonAdaptedPersonMeetingConnections.
     */
    @JsonCreator
    public JsonSerializableConnection(
            @JsonProperty("connections") List<JsonAdaptedPersonMeetingConnection> connection) {
        this.connections.addAll(connection);
    }

    /**
     * Converts a given {@code PersonMeetingConnection} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableConnection}.
     */
    public JsonSerializableConnection(PersonMeetingConnection source) {
        requireNonNull(source);
        HashMap<Person, UniqueMeetingList> map = source.getMeetingPersonMap();
        for (Map.Entry<Person, UniqueMeetingList> dict : map.entrySet()) {
            Person personKey = dict.getKey();
            for (Meeting meeting : dict.getValue()) {
                connections.add(new JsonAdaptedPersonMeetingConnection(personKey, meeting));
            }
        }
    }

    /**
     * Converts this JsonAdaptedPersonMeetingConnection into the model's {@code PersonMeetingConnection} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PersonMeetingConnection toModelType(ReadOnlyMeetingBook meetingBook,
                                               ReadOnlyAddressBook addressBook) throws IllegalValueException {
        PersonMeetingConnection connection = new PersonMeetingConnection();
        for (JsonAdaptedPersonMeetingConnection jsonAdaptedPersonMeetingConnection : connections) {
            connection = jsonAdaptedPersonMeetingConnection.toModelType(addressBook, meetingBook, connection);
        }
        return connection;
    }
}
