package seedu.address.model.connection;

import static java.util.Objects.requireNonNull;
import java.util.HashMap;
import seedu.address.model.connection.exceptions.ConnectionNoFoundException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Represents a connection between persons and meetings.
 * Uses two hashmaps to store the connections, use both meeting and person as a connection.
 * In default, if the connection is not stated, the connection class object will not record the connections.
 * i.e: those meeting and person will not appear in the hashmap, both key and values(in the UniqueXXXList).
 */
public class PersonMeetingConnection {
    private HashMap<Meeting, UniquePersonList> personsInMeeting;
    private HashMap<Person, UniqueMeetingList> meetingsInPerson;

    /**
     * Constructs a {@code PersonMeetingConnection}.
     */
    public PersonMeetingConnection() {
        personsInMeeting = new HashMap<>();
        meetingsInPerson = new HashMap<>();
    }
    /**
     * Constructs a {@code PersonMeetingConnection} from a existing connection.
     */
    public PersonMeetingConnection(PersonMeetingConnection connection) {
        personsInMeeting = connection.personsInMeeting;
        meetingsInPerson = connection.meetingsInPerson;
    }
    /**
     * Resets a {@code PersonMeetingConnection} from a existing connection.
     */
    public void resetData(PersonMeetingConnection connection) {
        requireNonNull(connection);
        this.personsInMeeting = connection.personsInMeeting;
        this.meetingsInPerson = connection.meetingsInPerson;
    }
    /**
     * Returns true if a given person and a given meeting exist a connection.
     */
    public boolean existPersonMeetingConnection(Person person, Meeting meeting) {
        UniquePersonList personList = personsInMeeting.get(meeting);
        UniqueMeetingList meetingList = meetingsInPerson.get(person);
        if (personList == null || meetingList == null) {
            return false;
        }
        return personList.contains(person) && meetingList.contains(meeting);
    }
    /**
     * Returns a UniqueMeetingList object with the person as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    public UniqueMeetingList getMeetingsByPerson(Person person) {
        return meetingsInPerson.getOrDefault(person, new UniqueMeetingList());
    }

    /**
     * Returns a UniquePersonList object with the meeting as the key.
     * Empty list will be returned if there is no value found in the hashMap.
     */
    public UniquePersonList getPersonsByMeeting(Meeting meeting) {
        return personsInMeeting.getOrDefault(meeting, new UniquePersonList());
    }
    /**
     * Adds a connection between a person and a meeting.
     */
    public void addPersonMeetingConnection(Person person, Meeting meeting) {
        UniquePersonList personList = personsInMeeting.getOrDefault(meeting, new UniquePersonList());
        UniqueMeetingList meetingList = meetingsInPerson.getOrDefault(person, new UniqueMeetingList());
        personList.add(person);
        meetingList.add(meeting);
        personsInMeeting.put(meeting, personList);
        meetingsInPerson.put(person, meetingList);
    }
    /**
     * This method delete a single connection between a meeting and a person.
     */
    public void deleteSinglePersonMeetingConnection(Person person, Meeting meeting) {
        if (!existPersonMeetingConnection(person, meeting)) {
            throw new ConnectionNoFoundException();
        }
        UniquePersonList personList = personsInMeeting.get(meeting);
        UniqueMeetingList meetingList = meetingsInPerson.get(person);
        personList.remove(person);
        meetingList.remove(meeting);
        personsInMeeting.put(meeting, personList);
        meetingsInPerson.put(person, meetingList);
    }
    /**
     * This method delete a all connections related to a given person.
     */
    public void deleteAllPersonMeetingConnectionByPerson(Person person) {
        if (meetingsInPerson.get(person) != null) {
            UniqueMeetingList meetings = meetingsInPerson.get(person);
            for (Meeting meeting : meetings) {
                UniquePersonList persons = personsInMeeting.get(meeting);
                persons.remove(person);
                personsInMeeting.put(meeting, persons);
            }
            meetingsInPerson.remove(person);
        }
    }
    /**
     * This method delete a all connections related to a given meeting.
     */
    public void deleteAllPersonMeetingConnectionByMeeting(Meeting meeting) {
        if (personsInMeeting.get(meeting) != null) {
            UniquePersonList persons = personsInMeeting.get(meeting);
            for (Person person : persons) {
                UniqueMeetingList meetings = meetingsInPerson.get(person);
                meetings.remove(meeting);
                meetingsInPerson.put(person, meetings);
            }
            personsInMeeting.remove(meeting);
        }
    }

    //Util methods
    @Override
    public String toString() {
        return meetingsInPerson.keySet().toString() + " have connection with meetings\n"
            + personsInMeeting.keySet().toString() + "have connection with persons.";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PersonMeetingConnection // instanceof handles nulls
            && personsInMeeting.equals(((PersonMeetingConnection) other).personsInMeeting)
            && meetingsInPerson.equals(((PersonMeetingConnection) other).meetingsInPerson));
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }


}
