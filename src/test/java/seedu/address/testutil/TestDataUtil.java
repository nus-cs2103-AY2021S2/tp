package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

public class TestDataUtil {
    public static Meeting[] getTypicalMeetings() {
        return new Meeting[] {
            TypicalMeetings.MEETING_1, TypicalMeetings.MEETING_2, TypicalMeetings.MEETING_3, TypicalMeetings.MEETING_4
        };
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }

        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(
                TypicalPersons.ALICE, TypicalPersons.BENSON, TypicalPersons.CARL, TypicalPersons.DANIEL,
                TypicalPersons.ELLE, TypicalPersons.FIONA, TypicalPersons.GEORGE));
    }
}
