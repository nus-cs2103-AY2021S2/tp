package dog.pawbook.model;

import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalOwners.ALICE;
import static dog.pawbook.testutil.TypicalOwners.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getEntityList());
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
    public void hasOwner_nullOwner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEntity(null));
    }

    @Test
    public void hasOwner_ownerNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEntity(ALICE));
    }

    @Test
    public void hasOwner_ownerInAddressBook_returnsTrue() {
        addressBook.addEntity(ALICE);
        assertTrue(addressBook.hasEntity(ALICE));
    }

    @Test
    public void getOwnerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getEntityList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose entities list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Pair<Integer, Entity>> entitiesWithId = FXCollections.observableArrayList();

        AddressBookStub(Collection<Pair<Integer, Entity>> owners) {
            this.entitiesWithId.setAll(owners);
        }

        @Override
        public ObservableList<Pair<Integer, Entity>> getEntityList() {
            return entitiesWithId;
        }
    }

}
