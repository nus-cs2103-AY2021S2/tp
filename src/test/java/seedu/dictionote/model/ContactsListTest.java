package seedu.dictionote.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.dictionote.testutil.Assert.assertThrows;
import static seedu.dictionote.testutil.TypicalContacts.ALICE;
import static seedu.dictionote.testutil.TypicalContacts.getTypicalContactsList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.contact.exceptions.DuplicateContactException;
import seedu.dictionote.testutil.ContactBuilder;

public class ContactsListTest {

    private final ContactsList contactsList = new ContactsList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), contactsList.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactsList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyContactsList_replacesData() {
        ContactsList newData = getTypicalContactsList();
        contactsList.resetData(newData);
        assertEquals(newData, contactsList);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two persons with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        ContactsListStub newData = new ContactsListStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> contactsList.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> contactsList.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInContactsList_returnsFalse() {
        assertFalse(contactsList.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInContactsList_returnsTrue() {
        contactsList.addContact(ALICE);
        assertTrue(contactsList.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInContactsList_returnsTrue() {
        contactsList.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(contactsList.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> contactsList.getContactList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ContactsListStub implements ReadOnlyContactsList {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

        ContactsListStub(Collection<Contact> contacts) {
            this.contacts.setAll(contacts);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

    }

}
