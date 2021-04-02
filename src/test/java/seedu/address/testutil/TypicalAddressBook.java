package seedu.address.testutil;

import static seedu.address.testutil.issue.TypicalIssues.getTypicalIssues;
import static seedu.address.testutil.resident.TypicalResidents.getTypicalResidents;
import static seedu.address.testutil.room.TypicalRooms.getTypicalRooms;

import seedu.address.model.AddressBook;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Resident;
import seedu.address.model.room.Room;

public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical residents, rooms, and issues.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Resident resident : getTypicalResidents()) {
            ab.addResident(resident);
        }

        for (Room room : getTypicalRooms()) {
            ab.addRoom(room);
        }

        for (Issue issue : getTypicalIssues()) {
            ab.addIssue(issue);
        }

        return ab;
    }
}
