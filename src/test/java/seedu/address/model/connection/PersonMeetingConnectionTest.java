package seedu.address.model.connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalMeetings.MEETING1;
import static seedu.address.testutil.TypicalMeetings.MEETING2;
import static seedu.address.testutil.TypicalMeetings.MEETING3;
import static seedu.address.testutil.TypicalMeetings.MEETING4;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.HOON;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.UniquePersonList;


class PersonMeetingConnectionTest {
    private PersonMeetingConnection connection = new PersonMeetingConnection();
    private PersonMeetingConnection connectionCopy;
    private PersonMeetingConnectionTest() {
        connection.addPersonMeetingConnection(AMY, MEETING1);
        connection.addPersonMeetingConnection(AMY, MEETING2);
        connection.addPersonMeetingConnection(AMY, MEETING3);

        connection.addPersonMeetingConnection(BOB, MEETING1);
        connection.addPersonMeetingConnection(BOB, MEETING2);
        connection.addPersonMeetingConnection(BOB, MEETING3);

        connectionCopy = new PersonMeetingConnection(connection);

    }

    @Test
    void resetData() {
        PersonMeetingConnection reset = new PersonMeetingConnection();
        reset.resetData(connection);
        UniqueMeetingList expected = new UniqueMeetingList();
        expected.add(MEETING1);
        expected.add(MEETING2);
        expected.add(MEETING3);
        assertEquals(connection.getMeetingsByPerson(AMY), expected);
        assertEquals(connection.getMeetingsByPerson(BOB), expected);
        assertNotEquals(connection.getMeetingsByPerson(CARL), expected);
        assertNotEquals(connection.getMeetingsByPerson(HOON), null);
    }

    @Test
    void getMeetingsByPerson() {
        UniqueMeetingList expected = new UniqueMeetingList();
        expected.add(MEETING1);
        expected.add(MEETING2);
        expected.add(MEETING3);
        assertEquals(connection.getMeetingsByPerson(AMY), expected);
        assertEquals(connection.getMeetingsByPerson(BOB), expected);
        assertNotEquals(connection.getMeetingsByPerson(CARL), expected);
        assertNotEquals(connection.getMeetingsByPerson(HOON), null);

        assertEquals(connectionCopy.getMeetingsByPerson(AMY), expected);
        assertEquals(connectionCopy.getMeetingsByPerson(BOB), expected);
        assertNotEquals(connectionCopy.getMeetingsByPerson(CARL), expected);
        assertNotEquals(connectionCopy.getMeetingsByPerson(HOON), null);

    }

    @Test
    void getPersonsByMeeting() {
        UniquePersonList expected = new UniquePersonList();
        expected.add(AMY);
        expected.add(BOB);
        assertEquals(connection.getPersonsByMeeting(MEETING1), expected);
        assertEquals(connection.getPersonsByMeeting(MEETING2), expected);
        assertEquals(connection.getPersonsByMeeting(MEETING3), expected);
        assertNotEquals(connection.getPersonsByMeeting(MEETING4), expected);
        assertNotEquals(connection.getPersonsByMeeting(MEETING4), null);

        assertEquals(connectionCopy.getPersonsByMeeting(MEETING1), expected);
        assertEquals(connectionCopy.getPersonsByMeeting(MEETING2), expected);
        assertEquals(connectionCopy.getPersonsByMeeting(MEETING3), expected);
        assertNotEquals(connectionCopy.getPersonsByMeeting(MEETING4), expected);
        assertNotEquals(connectionCopy.getPersonsByMeeting(MEETING4), null);
    }

    @Test
    void addPersonMeetingConnection() {
        connection.addPersonMeetingConnection(AMY, MEETING4);
        connection.addPersonMeetingConnection(BOB, MEETING4);


        UniqueMeetingList expectedMeetings = new UniqueMeetingList();
        expectedMeetings.add(MEETING1);
        expectedMeetings.add(MEETING2);
        expectedMeetings.add(MEETING3);
        expectedMeetings.add(MEETING4);

        assertEquals(connection.getMeetingsByPerson(AMY), expectedMeetings);
        assertEquals(connection.getMeetingsByPerson(BOB), expectedMeetings);
        assertNotEquals(connection.getMeetingsByPerson(CARL), expectedMeetings);
        assertNotEquals(connection.getMeetingsByPerson(HOON), null);

        UniquePersonList expectedPersons = new UniquePersonList();
        expectedPersons.add(AMY);
        expectedPersons.add(BOB);
        assertEquals(connection.getPersonsByMeeting(MEETING1), expectedPersons);
        assertEquals(connection.getPersonsByMeeting(MEETING2), expectedPersons);
        assertEquals(connection.getPersonsByMeeting(MEETING3), expectedPersons);
        assertEquals(connection.getPersonsByMeeting(MEETING4), expectedPersons);


    }

    @Test
    void deleteSinglePersonMeetingConnection() {
        connection.deleteSinglePersonMeetingConnection(BOB, MEETING3);
        connection.deleteSinglePersonMeetingConnection(AMY, MEETING3);


        UniqueMeetingList expectedMeetings = new UniqueMeetingList();
        expectedMeetings.add(MEETING1);
        expectedMeetings.add(MEETING2);
        assertEquals(connection.getMeetingsByPerson(AMY), expectedMeetings);
        assertEquals(connection.getMeetingsByPerson(BOB), expectedMeetings);
        assertNotEquals(connection.getMeetingsByPerson(CARL), expectedMeetings);
        assertNotEquals(connection.getMeetingsByPerson(HOON), null);

        UniquePersonList expectedPersons = new UniquePersonList();
        expectedPersons.add(AMY);
        expectedPersons.add(BOB);
        assertEquals(connection.getPersonsByMeeting(MEETING1), expectedPersons);
        assertEquals(connection.getPersonsByMeeting(MEETING2), expectedPersons);
        assertNotEquals(connection.getPersonsByMeeting(MEETING4), expectedPersons);
        assertNotEquals(connection.getPersonsByMeeting(MEETING4), null);

    }

    @Test
    void deleteAllPersonMeetingConnectionByPerson() {
        connection.deleteAllPersonMeetingConnectionByPerson(AMY);
        connection.deleteAllPersonMeetingConnectionByPerson(CARL);

        UniqueMeetingList expectedMeetings = new UniqueMeetingList();
        expectedMeetings.add(MEETING1);
        expectedMeetings.add(MEETING2);
        expectedMeetings.add(MEETING3);
        assertEquals(connection.getMeetingsByPerson(AMY), new UniqueMeetingList());
        assertEquals(connection.getMeetingsByPerson(BOB), expectedMeetings);
        assertEquals(connection.getMeetingsByPerson(CARL), new UniqueMeetingList());


    }

    @Test
    void deleteAllPersonMeetingConnectionByMeeting() {
        connection.deleteAllPersonMeetingConnectionByMeeting(MEETING1);
        connection.deleteAllPersonMeetingConnectionByMeeting(MEETING4);

        UniquePersonList expectedPersons = new UniquePersonList();
        expectedPersons.add(AMY);
        expectedPersons.add(BOB);

        assertEquals(connection.getPersonsByMeeting(MEETING1), new UniquePersonList());
        assertEquals(connection.getPersonsByMeeting(MEETING2), expectedPersons);
        assertEquals(connection.getPersonsByMeeting(MEETING3), expectedPersons);
        assertEquals(connection.getPersonsByMeeting(MEETING4), new UniquePersonList());
    }
}