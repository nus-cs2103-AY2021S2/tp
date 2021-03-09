package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTaskTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class TaskTrackerTest {

    private final TaskTracker taskTracker = new TaskTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskTracker.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskTracker_replacesData() {
        TaskTracker newData = getTypicalTaskTracker();
        taskTracker.resetData(newData);
        assertEquals(newData, taskTracker);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        TaskTrackerStub newData = new TaskTrackerStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> taskTracker.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskTracker.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInTaskTracker_returnsFalse() {
        assertFalse(taskTracker.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInTaskTracker_returnsTrue() {
        taskTracker.addPerson(ALICE);
        assertTrue(taskTracker.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInTaskTracker_returnsTrue() {
        taskTracker.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(taskTracker.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskTracker.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskTracker whose persons list can violate interface constraints.
     */
    private static class TaskTrackerStub implements ReadOnlyTaskTracker {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        TaskTrackerStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
