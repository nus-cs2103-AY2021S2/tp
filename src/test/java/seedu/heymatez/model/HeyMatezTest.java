package seedu.heymatez.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.heymatez.testutil.Assert.assertThrows;
import static seedu.heymatez.testutil.TypicalPersons.ALICE;
import static seedu.heymatez.testutil.TypicalPersons.getTypicalHeyMatez;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.person.exceptions.DuplicatePersonException;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Description;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.model.task.Title;
import seedu.heymatez.testutil.PersonBuilder;
import seedu.heymatez.testutil.TaskBuilder;

public class HeyMatezTest {

    private final HeyMatez heyMatez = new HeyMatez();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), heyMatez.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> heyMatez.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHeyMatez_replacesData() {
        HeyMatez newData = getTypicalHeyMatez();
        heyMatez.resetData(newData);
        assertEquals(newData, heyMatez);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).build();
        Task dummyTask = new TaskBuilder(new Task(new Title("Task1"), new Description("Some Description"),
                new Deadline("2021-04-04"))).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Task> tasks = Arrays.asList(dummyTask);
        HeyMatezStub newData = new HeyMatezStub(newPersons, tasks);

        assertThrows(DuplicatePersonException.class, () -> heyMatez.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> heyMatez.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInHeyMatez_returnsFalse() {
        assertFalse(heyMatez.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInHeyMatez_returnsTrue() {
        heyMatez.addPerson(ALICE);
        assertTrue(heyMatez.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInHeyMatez_returnsTrue() {
        heyMatez.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).build();
        assertTrue(heyMatez.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> heyMatez.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class HeyMatezStub implements ReadOnlyHeyMatez {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        HeyMatezStub(Collection<Person> persons, Collection<Task> tasks) {
            this.tasks.setAll(tasks);
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }
}
