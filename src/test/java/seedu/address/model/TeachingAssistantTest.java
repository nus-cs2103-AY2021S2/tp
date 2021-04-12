package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.getTypicalContactsTeachingAssistant;

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

public class TeachingAssistantTest {

    private final TeachingAssistant teachingAssistant = new TeachingAssistant();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), teachingAssistant.getContactList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachingAssistant.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTeachingAssistant_replacesData() {
        TeachingAssistant newData = getTypicalContactsTeachingAssistant();
        teachingAssistant.resetData(newData);
        assertEquals(newData, teachingAssistant);
    }

    @Test
    public void resetData_withDuplicateContacts_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        TeachingAssistantStub newData = new TeachingAssistantStub(newContacts);

        assertThrows(DuplicateContactException.class, () -> teachingAssistant.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> teachingAssistant.hasContact(null));
    }

    @Test
    public void hasContact_contactNotInTeachingAssistant_returnsFalse() {
        assertFalse(teachingAssistant.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactInTeachingAssistant_returnsTrue() {
        teachingAssistant.addContact(ALICE);
        assertTrue(teachingAssistant.hasContact(ALICE));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInTeachingAssistant_returnsTrue() {
        teachingAssistant.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(teachingAssistant.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> teachingAssistant.getContactList().remove(0));
    }

    /**
     * A stub ReadOnlyTeachingAssistant whose contacts list can violate interface constraints.
     */
    private static class TeachingAssistantStub implements ReadOnlyTeachingAssistant {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<Entry> entries = FXCollections.observableArrayList();

        TeachingAssistantStub(Collection<Contact> contacts) {
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
