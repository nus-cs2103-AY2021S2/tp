package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.ALICE;
import static seedu.address.testutil.TypicalItems.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.testutil.ItemBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getItemList());
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
    public void resetData_withDuplicateItems_throwsDuplicateItemException() {
        // Two items with the same identity fields
        Item editedAlice = new ItemBuilder(ALICE).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Item> newItems = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newItems);

        assertThrows(DuplicateItemException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasItem(ALICE));
    }

    @Test
    public void hasItem_itemInAddressBook_returnsTrue() {
        addressBook.addItem(ALICE);
        assertTrue(addressBook.hasItem(ALICE));
    }

    @Test
    public void hasItem_itemWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addItem(ALICE);
        Item editedAlice = new ItemBuilder(ALICE).withLocation(VALID_LOCATION_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        assertTrue(addressBook.hasItem(editedAlice));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getItemList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose items list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        AddressBookStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }
    }

}
