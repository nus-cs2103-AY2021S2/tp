package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.resident.TypicalResidents.ALICE;
import static seedu.address.testutil.residentroom.TypicalResidentRooms.ALICE_ROOM_NUMBER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.exceptions.DuplicateResidentException;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.Room;
import seedu.address.testutil.resident.ResidentBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getResidentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateResident_throwsDuplicateResidentException() {
        // Two residents with the same identity fields
        Resident editedAlice = new ResidentBuilder(ALICE)
                .build();
        List<Resident> newResidents = Arrays.asList(ALICE, editedAlice);
        List<Room> newRooms = new ArrayList<>();
        List<Issue> newIssues = new ArrayList<>();
        AddressBookStub newData = new AddressBookStub(newResidents, newRooms, newIssues);

        assertThrows(DuplicateResidentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasResident_nullResident_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasResident((Resident) null));
    }

    @Test
    public void hasResident_residentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasResident(ALICE));
    }

    @Test
    public void hasResident_residentInAddressBook_returnsTrue() {
        addressBook.addResident(ALICE);
        assertTrue(addressBook.hasResident(ALICE));
    }

    @Test
    public void hasResident_residentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addResident(ALICE);
        Resident editedAlice = new ResidentBuilder(ALICE)
                .build();
        assertTrue(addressBook.hasResident(editedAlice));
    }

    @Test
    public void hasResident_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasResident((Name) null));
    }

    @Test
    public void hasResident_nameNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasResident(ALICE.getName()));
    }

    @Test
    public void hasResident_nameInAddressBook_returnsTrue() {
        addressBook.addResident(ALICE);
        assertTrue(addressBook.hasResident(ALICE.getName()));
    }

    @Test
    public void hasBothResidentRoom_residentRoomInAddressBook_returnsTrue() {
        addressBook.addResidentRoom(ALICE_ROOM_NUMBER);
        assertTrue(addressBook.hasBothResidentRoom(ALICE_ROOM_NUMBER));
    }

    @Test
    public void removeResidentRoom_residentRoomInAddressBook_returnsEqual() {
        addressBook.addResidentRoom(ALICE_ROOM_NUMBER);
        addressBook.removeResidentRoom(ALICE_ROOM_NUMBER);
        AddressBook removedAddressBook = new AddressBook();
        assertEquals(addressBook, removedAddressBook);
    }

    @Test
    public void getResidentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getResidentList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose residents list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Resident> residents = FXCollections.observableArrayList();
        private final ObservableList<Room> rooms = FXCollections.observableArrayList();
        private final ObservableList<Issue> issues = FXCollections.observableArrayList();
        private final ObservableList<ResidentRoom> residentRooms = FXCollections.observableArrayList();


        AddressBookStub(Collection<Resident> residents, Collection<Room> rooms, Collection<Issue> issues) {
            this.residents.setAll(residents);
            this.rooms.setAll(rooms);
            this.issues.setAll(issues);
        }

        @Override
        public ObservableList<Resident> getResidentList() {
            return residents;
        }

        @Override
        public ObservableList<Room> getRoomList() {
            return rooms;
        }

        @Override
        public ObservableList<Issue> getIssueList() {
            return issues;
        }

        @Override
        public ObservableList<ResidentRoom> getResidentRoomList() {
            return residentRooms;
        }

        @Override
        public boolean isRecursiveKeyword(String commandWord) {
            return false;
        }

        @Override
        public boolean isReservedKeyword(String aliasName) {
            return false;
        }

        @Override
        public AliasMapping getAliasMapping() {
            return new AliasMapping();
        }

        @Override
        public Alias getAlias(String aliasName) {
            return new Alias("test", "test");
        }

        @Override
        public boolean containsAlias(String aliasName) {
            return false;
        }
    }

}
