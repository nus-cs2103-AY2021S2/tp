package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.getTypicalContactsAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.entry.Entry;
import seedu.address.testutil.ContactBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalContactsAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasContact(null));
    }

    @Test
    public void hasContact_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasContact(ALICE));
    }

    @Test
    public void hasContact_personInAddressBook_returnsTrue() {
        addressBook.addContact(ALICE);
        assertTrue(addressBook.hasContact(ALICE));
    }

    @Test
    public void hasContact_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getContactList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose contacts list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<Entry> entries = FXCollections.observableArrayList();

        AddressBookStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Entry> getEntryList() {
            return entries;
        }

    }

}
