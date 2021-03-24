package seedu.address.model.connection;

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
     * Returns true if a given person and a given meeting exist a connection.
     */
    private boolean existPersonMeetingConnection(Person person, Meeting meeting) {
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
     * Returns a UniqueMeetingList object with the person as the key.
     * Empty list will be returned if there is no value found in the hashMap.
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


}
