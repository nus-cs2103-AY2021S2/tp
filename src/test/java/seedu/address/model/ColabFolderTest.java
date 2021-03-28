package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateProjectException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProjectBuilder;

public class ColabFolderTest {
    private static final Project TEST_PROJECT = new ProjectBuilder().build();
    private final ColabFolder colabFolder = new ColabFolder();

    @Test
    public void constructor_listsAreEmpty_success() {
        assertEquals(Collections.emptyList(), colabFolder.getPersonList());
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
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ColabFolderStub newData = new ColabFolderStub(newPersons, new ArrayList<>());

        assertThrows(DuplicatePersonException.class, () -> colabFolder.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateProjects_throwsDuplicateProjectException() {
        List<Project> newProjects = Arrays.asList(TEST_PROJECT, TEST_PROJECT);
        ColabFolderStub newData = new ColabFolderStub(new ArrayList<>(), newProjects);

        assertThrows(DuplicateProjectException.class, () -> colabFolder.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> colabFolder.hasPerson(null));
    }

    @Test
    public void hasProject_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> colabFolder.hasProject(null));
    }

    @Test
    public void hasPerson_personNotInColabFolder_returnsFalse() {
        assertFalse(colabFolder.hasPerson(ALICE));
    }

    @Test
    public void hasProject_projectNotInColabFolder_returnsFalse() {
        assertFalse(colabFolder.hasProject(TEST_PROJECT));
    }

    @Test
    public void hasPerson_personInColabFolder_returnsTrue() {
        colabFolder.addPerson(ALICE);
        assertTrue(colabFolder.hasPerson(ALICE));
    }

    @Test
    public void hasProject_projectInColabFolder_returnsTrue() {
        colabFolder.addProject(TEST_PROJECT);
        assertTrue(colabFolder.hasProject(TEST_PROJECT));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInColabFolder_returnsTrue() {
        colabFolder.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(colabFolder.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> colabFolder.getPersonList().remove(0));
    }

    @Test
    public void getProjectList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> colabFolder.getProjectsList().remove(0));
    }

    /**
     * A stub ReadOnlyColabFolder whose lists can violate interface constraints.
     */
    private static class ColabFolderStub implements ReadOnlyColabFolder {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        ColabFolderStub(Collection<Person> persons, Collection<Project> projects) {
            this.persons.setAll(persons);
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
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

        colabFolder1.removePerson(ALICE);
        int hashcode3 = colabFolder1.hashCode();

        // objects are unequal according to equals(): _should_ be distinct
        assertNotEquals(hashcode1, hashcode3);
    }

}
