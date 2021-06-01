package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalContacts.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.exceptions.DuplicateContactException;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.ProjectBuilder;

public class ColabFolderTest {
    private static final Project TEST_PROJECT = new ProjectBuilder().build();
    private final ColabFolder colabFolder = new ColabFolder();

    @Test
    public void constructor_listsAreEmpty_success() {
        assertEquals(Collections.emptyList(), colabFolder.getContactList());
        assertEquals(Collections.emptyList(), colabFolder.getProjectsList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> colabFolder.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyColabFolder_replacesData() {
        ColabFolder newData = getTypicalColabFolder();
        colabFolder.resetData(newData);
        assertEquals(newData, colabFolder);
    }

    @Test
    public void resetData_withDuplicateContact_throwsDuplicateContactException() {
        // Two contacts with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newContacts = Arrays.asList(ALICE, editedAlice);
        ColabFolderStub newData = new ColabFolderStub(newContacts, new ArrayList<>());

        assertThrows(DuplicateContactException.class, () -> colabFolder.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> newProjects = Arrays.asList(TEST_PROJECT, TEST_PROJECT);
        ColabFolderStub newData = new ColabFolderStub(new ArrayList<>(), newProjects);

        assertThrows(DuplicateProjectException.class, () -> colabFolder.resetData(newData));
    }

    @Test
    public void hasContact_nullContact_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> colabFolder.hasContact(null));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> colabFolder.hasProject(null));
    }

    @Test
    public void hasContact_contactNotInColabFolder_returnsFalse() {
        assertFalse(colabFolder.hasContact(ALICE));
    }

    @Test
    public void hasProject_projectNotInColabFolder_returnsFalse() {
        assertFalse(colabFolder.hasProject(TEST_PROJECT));
    }

    @Test
    public void hasContact_contactInColabFolder_returnsTrue() {
        colabFolder.addContact(ALICE);
        assertTrue(colabFolder.hasContact(ALICE));
    }

    @Test
    public void hasProject_projectInColabFolder_returnsTrue() {
        colabFolder.addProject(TEST_PROJECT);
        assertTrue(colabFolder.hasProject(TEST_PROJECT));
    }

    @Test
    public void hasContact_contactWithSameIdentityFieldsInColabFolder_returnsTrue() {
        colabFolder.addContact(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(colabFolder.hasContact(editedAlice));
    }

    @Test
    public void getContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> colabFolder.getContactList().remove(0));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> colabFolder.getProjectsList().remove(0));
    }

    /**
     * A stub ReadOnlyColabFolder whose lists can violate interface constraints.
     */
    private static class ColabFolderStub implements ReadOnlyColabFolder {
        private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        ColabFolderStub(Collection<Contact> contacts, Collection<Project> projects) {
            this.contacts.setAll(contacts);
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return contacts;
        }

        @Override
        public ObservableList<Project> getProjectsList() {
            return projects;
        }
    }

    @Test
    public void hashCode_success() {
        ColabFolder colabFolder1 = getTypicalColabFolder();
        int hashcode1 = colabFolder1.hashCode();

        // invoked on the same object: _must_ be equal
        assertEquals(hashcode1, colabFolder1.hashCode());

        ColabFolder colabFolder2 = getTypicalColabFolder();

        // objects are equal according to equals(): _must_ be equal
        assertEquals(hashcode1, colabFolder2.hashCode());

        colabFolder1.removeContact(ALICE);
        int hashcode3 = colabFolder1.hashCode();

        // objects are unequal according to equals(): _should_ be distinct
        assertNotEquals(hashcode1, hashcode3);
    }

}
