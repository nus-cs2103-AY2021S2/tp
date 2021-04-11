package seedu.address.testutil;

import seedu.address.model.connection.PersonMeetingConnection;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;


import java.util.List;

import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class TypicalConnections {
    private static AddressBook typicalAddressBook = getTypicalAddressBook();
    private static MeetingBook typicalMeetingBook = getTypicalMeetingBook();

    public static PersonMeetingConnection getTypicalPersonMeetingConnection() {
        PersonMeetingConnection typicalPersonMeetingConnection = new PersonMeetingConnection();
        List<Person> personList = typicalAddressBook.getPersonList();
        List<Meeting> meetingList = typicalMeetingBook.getMeetingList();

        /*
        * Add all (person,meeting) grouped by person, i,e
        * do not all all the person connection involving person(0) s should be added together in on
        * group.
        */
        typicalPersonMeetingConnection.addPersonMeetingConnection(
                personList.get(0),
                meetingList.get(1)
        );
        typicalPersonMeetingConnection.addPersonMeetingConnection(
                personList.get(0),
                meetingList.get(2)
        );

        typicalPersonMeetingConnection.addPersonMeetingConnection(
                personList.get(1),
                meetingList.get(0)
        );

        typicalPersonMeetingConnection.addPersonMeetingConnection(
                personList.get(1),
                meetingList.get(2)
        );
        return typicalPersonMeetingConnection;
    }
}
