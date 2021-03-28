package seedu.address.testutil;

import static seedu.address.testutil.TypicalResidents.getTypicalResidents;
import static seedu.address.testutil.room.TypicalRooms.getTypicalRooms;

import seedu.address.model.AddressBook;
import seedu.address.model.resident.Resident;
import seedu.address.model.room.Room;

public class TypicalAddressBook {
    /**
     * Returns an {@code AddressBook} with all the typical residents and rooms.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Resident resident : getTypicalResidents()) {
            ab.addResident(resident);
        }

        for (Room room : getTypicalRooms()) {
            ab.addRoom(room);
        }
        return ab;
    }
}
